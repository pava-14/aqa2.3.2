package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.UserInfo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class ApiUsersTest {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .addFilter(new ResponseLoggingFilter())
            .log(LogDetail.ALL)
            .build();

    private void setUpUser(UserInfo user) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user)
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    private void loginForm (String login, String password) {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue(login);
        $("[data-test-id=password] input").setValue(password);
        $("[data-test-id=action-login]").click();
    }

    @Test
    public void shouldLoginExistsActiveUser() {
        UserInfo user = DataGenerator.RegistrationInfo.generateUserInfo("ru", false);
        setUpUser(user);
        loginForm(user.getLogin(), user.getPassword());
        $(byText("Личный кабинет")).waitUntil(visible, 15000);
    }

    @Test
    public void shouldLoginExistsBlockedUser() {
        UserInfo user = DataGenerator.RegistrationInfo.generateUserInfo("ru", true);
        setUpUser(user);
        loginForm(user.getLogin(), user.getPassword());
        $(withText("Пользователь заблокирован")).waitUntil(visible, 15000);
    }

    @Test
    public void shouldLoginWithWrongUsername() {
        UserInfo user = DataGenerator.RegistrationInfo.generateUserInfo("ru", false);
        setUpUser(user);
        loginForm(DataGenerator.RegistrationInfo.makeUserName("ru"), user.getPassword());
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }

    @Test
    public void shouldLoginWithWrongPassword() {
        UserInfo user = DataGenerator.RegistrationInfo.generateUserInfo("ru", false);
        setUpUser(user);
        loginForm(user.getLogin(), DataGenerator.RegistrationInfo.makeUserPassword("ru"));
        $(withText("Неверно указан логин или пароль")).waitUntil(visible, 15000);
    }
}
