
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.example.courier.*;
import org.junit.After;
import org.junit.Test;


public class TestCreateCourier {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertion check = new CourierAssertion();
    protected int courierId;

    @After
    @Step("Удаление курьера")
    public void deleteCourier(){
        if(courierId > 0) {
            var response = client.deleteCourier(courierId);
            check.deleteCourierSuccessfully(response);
        }
    }

    @Test
    @Step("Проверяем что курьера можно создать и получаем его id")
    public void createCourierAndSuccessfullyLogin(){
        var courier = generator.generic();
        ValidatableResponse courierResponse = client.createCourier(courier);
        check.createSuccessfully(courierResponse);

        Credentials cred = Credentials.from(courier);
        ValidatableResponse loginResponse = client.loginCourier(cred);
        courierId = check.loginSuccessfully(loginResponse);
    }

    @Test
    @Step("Проверяем что курьера нельзя создать без логина")
    public void createCourierWithOutLogin(){
        var courier = generator.genericWithOutLogin();
        ValidatableResponse response = client.createCourier(courier);
        check.createCourierWithOutOneParametrs(response);
    }

    @Test
    @Step("Провем что курьера нельзя создать без пароля")
    public void createCourierWithOutPassword(){
        var courier = generator.genericWithOutPassword();
        ValidatableResponse response = client.createCourier(courier);
        check.createCourierWithOutOneParametrs(response);
    }

    @Test
    @Step("Проверяем что курьера нельзя создать без логина и пароля")
    public void createCourierWithOutLoginAndPassword(){
        var courier = generator.genericCourierWithOutParametrs();
        ValidatableResponse response = client.createCourier(courier);
        check.createCourierWithOutOneParametrs(response);
    }

    @Test
    @Step("Проверяем что нельзя создать курьера с уже созданным логином")
    public void createCourierWithLastLogin(){
        var courier = generator.random();
        client.createCourier(courier);

        Credentials cred = Credentials.from(courier);
        ValidatableResponse resp = client.createSameCourier(cred);
        check.createTwoSameCourier(resp);
    }
}
