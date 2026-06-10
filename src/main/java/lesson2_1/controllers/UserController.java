package lesson2_1.controllers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lesson2_1.models.User;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static lesson2_1.constants.CommonConstants.BASE_URI;

public class UserController {

    // общий шаблон
    RequestSpecification requestSpecification;
    private static final String USER_ENDPOINT = "user";

    // создаем конструктор для всех шагов
    public UserController() {
        this.requestSpecification = given()
                .accept(JSON)
                .contentType(JSON)
                .baseUri(BASE_URI);
    }

    public Response createUser(User user) {
        return given(this.requestSpecification)// используем общий шаблон
                .body(user)// объект → JSON (сериализация)
                .when()
                .post(USER_ENDPOINT)
                .andReturn();// возвращаем Response
    }

    public Response updateUser(User user) {
        return given(this.requestSpecification)// используем общий шаблон
                .body(user)// объект → JSON (сериализация)
                .when()
                .put(USER_ENDPOINT + "/" + user.getUsername())
                .andReturn();// возвращаем Response
    }

    public Response getUserByUsername(String username) {
        return given(this.requestSpecification)// используем общий шаблон
                .when()
                .get(USER_ENDPOINT + "/" + username)
                .andReturn();// возвращаем Response
    }

    public Response deleteUserByUsername(String username) {
        return given(this.requestSpecification)// используем общий шаблон
                .when()
                .delete(USER_ENDPOINT + "/" + username)
                .andReturn();// возвращаем Response
    }
}
