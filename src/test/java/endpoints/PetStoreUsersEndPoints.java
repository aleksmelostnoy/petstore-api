package endpoints;

import io.restassured.response.Response;
import models.User;
import org.apache.http.HttpStatus;
import utils.PropertiesController;

import static io.restassured.RestAssured.given;

public class PetStoreUsersEndPoints {

    private static final String CREATE_USER = PropertiesController.getProperty("petstore.create.user");
    private static final String GET_USER_USERNAME = PropertiesController.getProperty("petstore.get.by.username");


    public Response getUsername(String username) {
        return given()
                .when()
                .pathParam("username", username)
                .get(GET_USER_USERNAME);
    }

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
}
