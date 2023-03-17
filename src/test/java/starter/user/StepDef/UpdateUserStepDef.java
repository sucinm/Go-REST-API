package starter.user.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;
import starter.utils.Payload;

import java.io.File;

public class UpdateUserStepDef {
    @Steps
    UserAPI userAPI;

    Payload payload = new Payload();
    @Given("update user with valid ID and request body")
    public void updateUserWithValidIDAndRequestBody() {
        Response response = SerenityRest.given().get(UserAPI.GET_USER);
        JsonPath jsonPathEvaluator = response.jsonPath();
        System.out.println("Kentut" + jsonPathEvaluator.get("[0]").toString());
        userAPI.patchUsers(jsonPathEvaluator.get("[0]").toString(), jsonPathEvaluator.get("[0].id").toString());
    }

    @When("send request patch user")
    public void sendRequestPatchUser() {
        SerenityRest.when().patch(UserAPI.PATCH_USER);
    }

    @And("validate json schema update user")
    public void validateJsonSchemaUpdateUser() {
    }

    @Given("update user with {string} as ID and {string} request body")
    public void updateUserWithAsIDAndRequestBody(String id, String description) {
        File json = new File(starter.utils.Constant.JSON_SCHEMA + "/ValidRequestUpdateUser.json");
        if(description.equals("invalid")){
            json = new File(starter.utils.Constant.JSON_SCHEMA + "/InvalidRequestPatchUser.json");
        }
        userAPI.patchUsers(json, id);
    }

    @Given("update user without token")
    public void updateUserWithoutToken() {
        Response response = SerenityRest.given().get(UserAPI.GET_USER);
        JsonPath jsonPathEvaluator = response.jsonPath();
        userAPI.patchUsersWithoutToken(jsonPathEvaluator.get("[0]").toString(), jsonPathEvaluator.get("[0].id").toString());
    }
}
