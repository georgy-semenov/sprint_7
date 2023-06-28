package order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetOrderTest {
    private OrderAssertion check = new OrderAssertion();
    @Test
    @Step("Получаем список заказа")
    public void getOrderList(){
        OrderClient orderClient = new OrderClient();
        ValidatableResponse resp = orderClient.getOrderList();
        check.assertGetOrderList(resp);
    }
}
