package org.example.courier;

import io.restassured.response.ValidatableResponse;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CourierAssertion {


    public void createSuccessfully(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    public int loginSuccessfully(ValidatableResponse response){
        return response
                .assertThat()
                .statusCode(HTTP_OK)
                .body("id", notNullValue())
                .extract()
                .path("id");
    }

    public void createTwoSameCourier(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    public void createCourierWithOutOneParametrs(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    public void deleteCourierSuccessfully(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_OK);
    }

    public void loginCourierWithOutOneParametrs(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    public void loginCourierWithRandomParametrs(ValidatableResponse response){
        response
                .assertThat()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

}
