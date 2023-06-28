package order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;



@RunWith(Parameterized.class)
public class OrderTest {
    private final List<String> color;
    private int track;
    private OrderClient orderClient = new OrderClient();
    private OrderAssertion check = new OrderAssertion();

    public OrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Цвет самоката. Тестовые данные: {0}")
    public static Object[][] getColor() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of()}
        };
    }
    @After
    @Step("Отмена заказа")
    public void cancelOrder(){
        orderClient.cancelOrder(track);
    }
   @Test
   @Step("Создание нового заказа")
    public void createNewOrder(){
        Order order = new Order(color);
        ValidatableResponse resp = orderClient.createOrder(order);
        track = check.createOrderSuccessfully(resp);
   }

}