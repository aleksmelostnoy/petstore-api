package tests;

import endpoints.PetStoreUsersEndPoints;
import models.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PetStoreUsersTests extends BaseTest {

    private static final PetStoreUsersEndPoints PET_STORE_USERS_END_POINTS = new PetStoreUsersEndPoints();

    @Test(description = "Создание пользователя")
    public void createUser(){
        //given
        User user = User.createUser();
        //when
        PET_STORE_USERS_END_POINTS.createUser(user);
        //then
        User createdUserFromService = PET_STORE_USERS_END_POINTS.getUsername(user.getUsername()).as(User.class);

        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(createdUserFromService.getFirstName(), user.getFirstName());
        assertions.assertEquals(createdUserFromService.getLastName(), user.getLastName());
        assertions.assertEquals(createdUserFromService.getUserStatus(), user.getUserStatus());
        assertions.assertEquals(createdUserFromService.getEmail(), user.getEmail());
        assertions.assertEquals(createdUserFromService.getPhone(), user.getPhone());
        assertions.assertAll();
    }
}
