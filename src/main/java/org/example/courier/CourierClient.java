package org.example.courier;


import io.restassured.response.ValidatableResponse;

public class CourierClient extends Client {
    static final String CREATE_COURIER_API = "/api/v1/courier";
    static final String LOGIN_COURIER_API = "/api/v1/courier/login";
    static final String DELETE_COURIER_API = "/api/v1/courier/";


    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(CREATE_COURIER_API)
                .then().log().all();
    }

    public ValidatableResponse createSameCourier(Credentials cred) {
        return spec()
                .body(cred)
                .when()
                .post(CREATE_COURIER_API)
                .then().log().all();
    }

    public ValidatableResponse loginCourier(Credentials cred){
        return spec()
                .body(cred)
                .when()
                .post(LOGIN_COURIER_API)
                .then().log().all();
    }

    public ValidatableResponse loginRandomCourier(Courier courier){
        return spec()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_API)
                .then().log().all();
    }


    public ValidatableResponse deleteCourier( int courierID){
        return spec()
                .when()
                .delete(DELETE_COURIER_API + courierID)
                .then().log().all();

    }




}
