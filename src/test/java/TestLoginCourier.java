import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.courier.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLoginCourier {
    private final Credentials cred = new Credentials();
    private final CourierClient client = new CourierClient();
    private final CourierAssertion check = new CourierAssertion();
    private final CourierGenerator generator = new CourierGenerator();

    protected String login;
    protected String password;
    protected int courierId;

    @Before
    @Step("Создаем курьера")
    public void createCourier(){
        Credentials cred = Credentials.from(new Courier(RandomStringUtils.randomAlphabetic(5,10), "12335", "senya"));
        client.createSameCourier(cred);
        login = cred.getLogin();
        password = cred.getPassword();

        cred.setLogin(login);
        cred.setPassword(password);
        ValidatableResponse resp = client.loginCourier(cred);
        courierId = check.loginSuccessfully(resp);
    }

    @After
    @Step("Удаление курьера")
    public void deleteCourier(){
        if(courierId > 0) {
            var response = client.deleteCourier(courierId);
            check.deleteCourierSuccessfully(response);
        }
    }

    @Test
    @Step("Проверяем что курьер может авторизоваться")
    public void courierLogin(){
        cred.setLogin(login);
        cred.setPassword(password);
        ValidatableResponse resp = client.loginCourier(cred);
        check.loginSuccessfully(resp);
    }

    @Test
    @Step("Проверяем что курьер не может войти без логина")
    public void loginCourierWithOutLogin(){
        cred.setPassword(password);
        ValidatableResponse resp = client.loginCourier(cred);
        check.loginCourierWithOutOneParametrs(resp);
    }

    @Test
    @Step("Проверяем что курьер не может войти без пароля")
    public void loginCourierWithOutPassword(){
        cred.setLogin(login);
        cred.setPassword("");
        ValidatableResponse resp = client.loginCourier(cred);
        check.loginCourierWithOutOneParametrs(resp);
    }

    @Test
    @Step("Проверяем что нельзя авторизоваться под несуществующим пользователем")
    public void loginRandomCourier(){
        var courier = generator.random();
        ValidatableResponse resp = client.loginRandomCourier(courier);
        check.loginCourierWithRandomParametrs(resp);
    }

    @Test
    @Step("Успещная авторизация возвращает id")
    public void getIdCourierByAutorisation(){
        cred.setLogin(login);
        cred.setPassword(password);
        ValidatableResponse resp = client.loginCourier(cred);
        courierId = check.loginSuccessfully(resp);
        assert(courierId != 0 );
    }
}

