package starter;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import starter.utils.Constant;

import java.io.File;

public class UserAPI {
    public static String GET_USER = Constant.BASE_URL + "/public/v2/users";
    public static String POST_USER = Constant.BASE_URL + "/public/v2/users";
    public static String PATCH_USER = Constant.BASE_URL + "/public/v2/users/{user_id}";
    public static String DELETE_USER = Constant.BASE_URL + "/public/v2/users/{user_id}";

    @Step("Get user")
    public void getUsers(String name, String email, String gender, String status) {
        SerenityRest.given()
                .queryParam("name", name)
                .queryParam("email", email)
                .queryParam("gender", gender)
                .queryParam("status", status);

    }

    @Step("Post user")
    public void postUsers(String json) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + Constant.TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Post user with file json")
    public void postUsers(File json) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + Constant.TOKEN)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Patch user with string json")
    public void patchUsers(String json, String id) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + Constant.TOKEN)
                .pathParam("user_id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Patch user with file json")
    public void patchUsers(File json, String id) {
        SerenityRest.given()
                .header("Authorization", "Bearer " + Constant.TOKEN)
                .pathParam("user_id", id)
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Patch user without token")
    public void patchUsersWithoutToken(String json, String id) {
        SerenityRest.given()
                .pathParam("user_id", id)
                .contentType(ContentType.JSON)
                 .body(json);
    }

    @Step("Delete user")
    public void deleteUsers(String id, boolean withToken) {
        SerenityRest.given()
                .header("Authorization", withToken ? "Bearer " + Constant.TOKEN : "")
                .pathParam("user_id", id);
    }
}
