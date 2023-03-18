package starter.user.StepDef;

import com.google.gson.JsonArray;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.UserAPI;

public class DeleteUserStepDef {
    @Steps
    UserAPI userAPI;

    @Given("delete User with valid ID")
    public void deleteUserWithValidID() {
        Response response = SerenityRest.given().get(UserAPI.GET_USER);
        JsonPath jsonPathEvaluator = response.jsonPath();
        userAPI.deleteUsers(jsonPathEvaluator.get("[0].id").toString(), true);
    }

    @When("send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when().delete(UserAPI.DELETE_USER);
    }

    @Given("delete User with {string} ID")
    public void deleteUserWithID(String id) {
        userAPI.deleteUsers(id, true);
    }

    @Given("delete User without ID and without token")
    public void deleteUserWithoutIDAndWithoutToken() {
        userAPI.deleteUsers("", false);
    }
}
