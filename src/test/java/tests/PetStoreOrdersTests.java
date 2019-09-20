package tests;

import endpoints.PetStoreOrdersEndPoints;
import io.restassured.response.Response;
import models.Order;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PetStoreOrdersTests extends BaseTest {

    private static final PetStoreOrdersEndPoints PET_STORE_ORDERS_END_POINTS = new PetStoreOrdersEndPoints();

    @Test(description = "Создание заявки")
    public void createOrder(){
        //given
        Order order = Order.createDefaultOrder();
        //when
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        //then
        long createdOrderId = orderFromResponse.body().as(Order.class).getId();
        Order createdOrderFromService = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId).as(Order.class);

        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(createdOrderFromService.getPetId(), order.getPetId());
        assertions.assertEquals(createdOrderFromService.getQuantity(), order.getQuantity());
        assertions.assertEquals(createdOrderFromService.getStatus(), order.getStatus());
        assertions.assertEquals(createdOrderFromService.isComplete(), order.isComplete());
        assertions.assertEquals(orderFromResponse.getStatusCode(), 200);
        assertions.assertAll();
    }

    @Test
    public void readOrder(){
        //given
        Order order = Order.createDefaultOrder();
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        long createdOrderId = orderFromResponse.body().as(Order.class).getId();
        //when
        Order createdOrderFromServiceObject = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId).as(Order.class);
        //then

        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(orderFromResponse.getStatusCode(), 200);
        assertions.assertEquals(createdOrderFromServiceObject.getPetId(), order.getPetId());
        assertions.assertEquals(createdOrderFromServiceObject.getQuantity(), order.getQuantity());
        assertions.assertEquals(createdOrderFromServiceObject.getStatus(), order.getStatus());
        assertions.assertEquals(createdOrderFromServiceObject.isComplete(), order.isComplete());
        assertions.assertAll();
    }

    @Test
    public void deleteOrder(){
        //given
        Order order = Order.createDefaultOrder();
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        long createdOrderId = orderFromResponse.body().as(Order.class).getId();
        //when
        PET_STORE_ORDERS_END_POINTS.deleteStoreOrderById(createdOrderId);
        //then
        Response orderById = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId);
        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(orderById.getStatusCode(), 404);
        assertions.assertAll();
    }

    @Test
    public void verifyGetResponseTime() {
        //given
        Order order = Order.createDefaultOrder();
        Response orderFromResponse = PET_STORE_ORDERS_END_POINTS.createOrder(order);
        long createdOrderId = orderFromResponse.body().as(Order.class).getId();
        //when
        Response createdOrderFromServiceObject = PET_STORE_ORDERS_END_POINTS.getStoreOrderById(createdOrderId);
        //then
        PET_STORE_ORDERS_END_POINTS.verifyResponseTime(createdOrderFromServiceObject, 1000L);
    }

}
