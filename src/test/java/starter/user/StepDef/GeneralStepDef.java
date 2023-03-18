package starter.user.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;
import starter.utils.Constant;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class GeneralStepDef {
    @Steps
    UserAPI userAPI;
    @Then("should return status code {int}")
    public void shouldReturnStatusCode(int statusCode) {
        SerenityRest.then().assertThat().statusCode(statusCode);
    }

    @And("response body message should be {string}")
    public void responseBodyPageShouldBe(String message) {
        SerenityRest.then()
                .body("message", equalTo(message));
    }

    @And("validate json schema response error")
    public void validateJsonSchemaResponseError() {
        File jsonSchema = new File(Constant.JSON_SCHEMA + "/ResponseError.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }
}
