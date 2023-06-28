package order;

import io.restassured.response.ValidatableResponse;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.notNullValue;

public class OrderAssertion {
    public int createOrderSuccessfully(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");

    }

    public void assertGetOrderList(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());

    }
}
