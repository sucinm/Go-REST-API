package starter.user.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.equalTo;

public class GeneralStepDef {
    @Steps
    UserAPI userAPI;
    @Then("should return status code {int}")
    public void shouldReturnStatusCode(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }

    @And("response body message should be {string}")
    public void responseBodyPageShouldBe(String message) {
        SerenityRest.then()
                .body("message", hasItem(message));
    }
}
