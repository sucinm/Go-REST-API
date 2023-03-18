package starter.user.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;
import java.io.File;

import static org.hamcrest.Matchers.*;


public class GetUserStepDef {
    @Steps
    UserAPI userAPI;

    @Given("the user without parameter")
    public void theUserWithoutParameter() {
        SerenityRest.given();
    }

    @When("sent request user")
    public void sentRequestUser() {
        SerenityRest.when().get(UserAPI.GET_USER);
    }

    @And("validate json schema list user")
    public void validateJsonSchemaListUser() {
        File jsonSchema = new File(starter.utils.Constant.JSON_SCHEMA + "/ResponseUserSchemaValid.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Given("the user with gender {string}")
    public void theUserWithoutTokenWithParameter(String gender) {
        userAPI.getUsers("", "", gender, "");
    }

    @And("validate data user gender should be {string}")
    public void validateFirstDataUserGenderShouldBeGender(String parameter) {
        SerenityRest.then()
                .assertThat()
                .body("gender", everyItem(hasToString(parameter)));
    }

    @Given("the user with name {string}")
    public void theUserWithName(String name) {
        userAPI.getUsers(name, "", "", "");
    }

    @And("validate data user name should be contain {string}")
    public void validateFirstDataUserNameShouldBeContain(String name) {
        SerenityRest.then()
                .assertThat()
                .body("name", everyItem(containsStringIgnoringCase(name)));
    }

    @Given("the user with status {string}")
    public void theUserWithStatus(String status) {
        userAPI.getUsers("", "", "", status);
    }

    @And("validate data user status should be {string}")
    public void validateFirstDataUserStatusShouldBe(String status) {
        SerenityRest.then()
                .assertThat()
                .body("status", everyItem(hasToString(status)));
    }

    @Given("the user with email {string}")
    public void theUserWithEmail(String email) {
        userAPI.getUsers("", email, "", "");
    }

    @And("validate data user email should be contain {string}")
    public void validateFirstDataUserEmailShouldBe(String email) {
        SerenityRest.then()
                .assertThat()
                .body("email", everyItem(containsStringIgnoringCase(email)));
    }

    @When("sent request user with {string} as parameter")
    public void sentRequestUserWithAsParameter(String parameter) {
        SerenityRest.given().when().get(UserAPI.GET_USER + "?" + parameter);
    }
}
