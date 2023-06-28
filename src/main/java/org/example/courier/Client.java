package org.example.courier;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {
    static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    protected RequestSpecification spec(){
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}
