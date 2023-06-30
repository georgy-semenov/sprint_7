package order;

import io.restassured.response.ValidatableResponse;
import org.example.courier.Client;

public class OrderClient extends Client {
    static final String ORDER_API = "/api/v1/orders";
    static final String CANCEL_ORDER_API = "/api/v1/orders/cancel";

    public ValidatableResponse createOrder(Order order){
        return spec()
                .body(order)
                .when()
                .post(ORDER_API)
                .then().log().all();
    }

    public ValidatableResponse getOrderList(){
        return spec()
                .when()
                .get(ORDER_API)
                .then().log().all();
    }

    public ValidatableResponse cancelOrder(int track){
        return spec()
                .body(track)
                .put(CANCEL_ORDER_API)
                .then().log().all();
    }

}
