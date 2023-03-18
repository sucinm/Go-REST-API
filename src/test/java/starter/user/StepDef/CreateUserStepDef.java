package starter.user.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;
import starter.utils.Constant;
import starter.utils.Payload;

import java.io.File;

public class CreateUserStepDef {
    @Steps
    UserAPI userAPI;

    Payload payload = new Payload();
    @Given("create user with valid request body")
    public void createUserWithValidRequestBody() {
        userAPI.postUsers(payload.randomBodyRequestPostUser());
    }

    @When("send request post user")
    public void sendRequestPostUser() {
        SerenityRest.when().post(UserAPI.POST_USER);
    }

    @And("validate json schema create user")
    public void validateJsonSchemaCreateUser() {
        File jsonSchema = new File(Constant.JSON_SCHEMA + "/ResponseCreateUserSchemaValid.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Given("create user with invalid request body")
    public void createUserWithInvalidRequestBody() {
        File json = new File(Constant.JSON_SCHEMA + "/ResponseCreateUserSchemaValid.json");
        userAPI.postUsers(json);
    }

    @Given("create user without request body")
    public void createUserWithoutRequestBody() {
        userAPI.postUsers("");
    }

    @Given("create user without name on request body")
    public void createUserWithoutNameOnRequestBody() {
        File json = new File(Constant.JSON_REQUEST + "/InvalidRequestCreateUserWithoutName.json");
        userAPI.postUsers(json);
    }

    @Given("create user without token")
    public void createUserWithoutToken() {
        SerenityRest.given().body(payload.randomBodyRequestPostUser());
    }
}
