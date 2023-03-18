package starter.user.StepDef;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;
import starter.utils.Constant;
import starter.utils.Payload;

import java.io.File;

public class UpdateUserStepDef {
    @Steps
    UserAPI userAPI;

    @Given("update user with valid ID and request body")
    public void updateUserWithValidIDAndRequestBody() {
        Response response = SerenityRest.given().get(UserAPI.GET_USER);
        JsonPath jsonPathEvaluator = response.jsonPath();
        Gson gson = new Gson();
        String json = gson.toJson(jsonPathEvaluator.getList("").get(0));
        userAPI.patchUsers(json, jsonPathEvaluator.get("[0].id").toString());
    }

    @When("send request patch user")
    public void sendRequestPatchUser() {
        SerenityRest.when().patch(UserAPI.PATCH_USER);
    }

    @And("validate json schema update user")
    public void validateJsonSchemaUpdateUser() {
        File jsonSchema = new File(Constant.JSON_SCHEMA + "/ResponseUpdateUserSchemaValid.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));

    }

    @Given("update user with {string} as ID and {string} request body")
    public void updateUserWithAsIDAndRequestBody(String id, String description) {
        File json = new File(Constant.JSON_REQUEST + "/ValidRequestUpdateUser.json");
        if(description.equals("invalid")){
            json = new File(Constant.JSON_REQUEST + "/InvalidRequestPatchUser.json");
        }
        userAPI.patchUsers(json, id);
    }

    @Given("update user without token")
    public void updateUserWithoutToken() {
        Response response = SerenityRest.given().get(UserAPI.GET_USER);
        JsonPath jsonPathEvaluator = response.jsonPath();
        Gson gson = new Gson();
        String json = gson.toJson(jsonPathEvaluator.getList("").get(0));
        userAPI.patchUsersWithoutToken(json, jsonPathEvaluator.get("[0].id").toString());
    }
}
