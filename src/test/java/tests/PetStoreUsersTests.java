package tests;

import endpoints.PetStoreUsersEndPoints;
import io.restassured.response.Response;

import models.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class PetStoreUsersTests extends BaseTest {

    private static final PetStoreUsersEndPoints PET_STORE_USERS_END_POINTS = new PetStoreUsersEndPoints();

    @Test(description = "Создание пользователя")
    public void createUserTest(){
        //given
        User user = User.createUser();
        //when
        PET_STORE_USERS_END_POINTS.createUser(user);
        //then
        User createdUserFromService = PET_STORE_USERS_END_POINTS.getUserByUsername(user.getUsername()).as(User.class);

        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(createdUserFromService.getFirstName(), user.getFirstName());
        assertions.assertEquals(createdUserFromService.getLastName(), user.getLastName());
        assertions.assertEquals(createdUserFromService.getUserStatus(), user.getUserStatus());
        assertions.assertEquals(createdUserFromService.getEmail(), user.getEmail());
        assertions.assertEquals(createdUserFromService.getPhone(), user.getPhone());
        assertions.assertAll();
    }

    @Test(description = "Удаление пользователя")
    public void deleteOrderTest(){
        //given
        User user = User.createUser();
        PET_STORE_USERS_END_POINTS.createUser(user);
        String username = user.getUsername();
        //when
        PET_STORE_USERS_END_POINTS.deleteUserByUsername(username);
        //then
        Response userByUsername = PET_STORE_USERS_END_POINTS.getUserByUsername(username);
        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(userByUsername.getStatusCode(), 404);
        assertions.assertAll();
    }

    @Test(description = "Поиск пользователя")
    public void getUserByUserNameTest(){
        //given
        User user = User.createUser();
        PET_STORE_USERS_END_POINTS.createUser(user);
        //when
        User createdUserFromService = PET_STORE_USERS_END_POINTS.getUserByUsername(user.getUsername()).as(User.class);
        //then
        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(createdUserFromService.getFirstName(), user.getFirstName());
        assertions.assertEquals(createdUserFromService.getUsername(), user.getUsername());
        assertions.assertEquals(createdUserFromService.getLastName(), user.getLastName());
        assertions.assertEquals(createdUserFromService.getUserStatus(), user.getUserStatus());
        assertions.assertEquals(createdUserFromService.getEmail(), user.getEmail());
        assertions.assertEquals(createdUserFromService.getPhone(), user.getPhone());
        assertions.assertEquals(createdUserFromService.getPassword(), user.getPassword());
        assertions.assertEquals(createdUserFromService.getId(), user.getId());
        assertions.assertAll();
    }
    @Test(description = "Создание списка пользователей массивом")
    public void createWithArrayTest(){
        //given
        User[] users = {User.createUser(), User.createUser(), User.createUser()};

        users[0].setUsername("0");
        users[1].setUsername("1");
        users[2].setUsername("2");
        //when
        PET_STORE_USERS_END_POINTS.createWithArray(users);
        //then
        User createWithArrayFromService = PET_STORE_USERS_END_POINTS.getUserByUsername(users[2].getUsername()).as(User.class);

        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(createWithArrayFromService.getId(),users[2].getId());
        assertions.assertAll();
    }
    @Test(description = "Создание списка  пользователей листом")
    public void createWithListTest(){
        //given
        List<User> users = new ArrayList<User>();
        users.add(User.createUser());
        users.add(User.createUser());
        users.add(User.createUser());

        users.get(0).setUsername("0");
        users.get(1).setUsername("1");
        users.get(2).setUsername("2");

        //when
        PET_STORE_USERS_END_POINTS.createWithList(users);
        //then
        User createWithListFromService = PET_STORE_USERS_END_POINTS.getUserByUsername(users.get(0).getUsername()).as(User.class);;

        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(createWithListFromService.getId(),users.get(0).getId());
        assertions.assertAll();
    }
}


