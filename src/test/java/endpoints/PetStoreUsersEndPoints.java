package endpoints;

import io.restassured.response.Response;
import models.User;
import org.apache.http.HttpStatus;
import utils.PropertiesController;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetStoreUsersEndPoints {

    private static final String CREATE_USER = PropertiesController.getProperty("petstore.create.user");
    private static final String GET_USER_USERNAME = PropertiesController.getProperty("petstore.get.by.username");
    private static final String CREATE_USER_WITH_ARREY = PropertiesController.getProperty("petstore.create.user.with.array");
    private static final String CREATE_USER_WITH_LIST = PropertiesController.getProperty("petstore.create.user.with.list");
    private static final String GET_LOGIN_USER = PropertiesController.getProperty("petstore.get.by.loginUser");
    private static final String UPDATE_USER_BY_USERNAME = PropertiesController.getProperty("petstore.update.user.by.username");
    private static final String LOGOUT = PropertiesController.getProperty("petstore.logout");

    public Response createUser(User user) {
        Response response = given()
                .body(user)
                .when()
                .post(CREATE_USER);

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response sendUserToServis(User user) {
        Response response = given()
                .body(user)
                .when().log().all()
                .get(GET_LOGIN_USER);
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK).log().all();
        return response;
    }

    public Response deleteUserByUsername(String username) {
        return given()
                .when()
                .pathParam("username", username)
                .delete(GET_USER_USERNAME);
    }

    public Response getUserByUsername(String username) {
        return given()
                .when()
                .pathParam("username", username)
                .get(GET_USER_USERNAME);
    }



    public Response createWithArray(User[] users) {
        Response response = given()
                .body(users)
                .when()
                .post(CREATE_USER_WITH_ARREY);

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        return response;
    }

    public Response createWithList(List<User> users) {
        Response response = given()
                .body(users)
                .when()
                .post(CREATE_USER_WITH_LIST);

        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        return response;
    }
    public Response updateUserByUsername(User user, String username) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .pathParam("username", username)
                .put(UPDATE_USER_BY_USERNAME);
        response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
        return response;
    }

    public Response logout() {
        Response response = given()
                .when()
                .get(LOGOUT);
        return response;
    }

}



