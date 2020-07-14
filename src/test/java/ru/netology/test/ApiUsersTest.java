package ru.netology.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.UserInfo;

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

    @Test
    public void shouldRewriteExistsUser() {
        UserInfo user = DataGenerator.RegistrationInfo.generateActiveUserInfo("ru");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK

        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    public void shouldCreateWithEmptyUserStatus() {
        UserInfo user = DataGenerator.RegistrationInfo.generateActiveUserInfo("ru");
        user.setStatus("");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500
    }

    @Test
    public void shouldCreateWithInvalidUserLogin() {
        UserInfo user = DataGenerator.RegistrationInfo.generateActiveUserInfo("ru");
        user.setLogin("   ");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    public void shouldCreateWithInvalidUserPassword() {
        UserInfo user = DataGenerator.RegistrationInfo.generateActiveUserInfo("ru");
        user.setPassword("   ");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    public void shouldCreateWithBlockedUserStatus() {
        UserInfo user = DataGenerator.RegistrationInfo.generateBlockedUserInfo("ru");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    public void shouldLoginAterCreateWithActiveUserStatus() {
        UserInfo user = DataGenerator.RegistrationInfo.generateActiveUserInfo("ru");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK

        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/auth") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    public void shouldLoginAterCreateWithBlockedUserStatus() {
        UserInfo user = DataGenerator.RegistrationInfo.generateBlockedUserInfo("ru");
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK

        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/auth") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(400); // код 400
    }
}
