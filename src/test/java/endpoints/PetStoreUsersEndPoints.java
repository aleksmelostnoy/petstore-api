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
}



