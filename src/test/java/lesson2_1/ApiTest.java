package lesson2_1;

import io.restassured.response.Response;
import lesson2_1.controllers.UserController;
import lesson2_1.models.AddUserResponse;
import lesson2_1.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static lesson2_1.testdata.TestData.DEFAULT_USER;
import static lesson2_1.testdata.TestData.INVALID_USER;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    @Test
    void test1() {

//  Паттерн Arrange → Act → Assert (AAA)
//  Arrange, подготовка запроса
        String bodyRequest = """
                {
                    "id": 0,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                }""";

        // Act, выполнение запроса
        // given - подготовка данных (заголовки, тело, параметры), сохраняем как переменную response, чтобы потом от нее вызывать ассерты
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .baseUri("https://petstore.swagger.io/v2")
                // when - выполнение запроса (GET, POST, PUT, DELETE).baseUri("https://petstore.swagger.io/v2/")
                .when()
                .body(bodyRequest)
                .post("/user")
                .andReturn();

        //  Assert, проверка ответа
        int actualStatusCode = response.getStatusCode();
        assertEquals(200, actualStatusCode);
    }

    @Test
    void test2() {
        String baseUrl = "https://petstore.swagger.io/v2/";
        String body = """
                {
                    "id": 0,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                }""";

        // given - подготовка данных (заголовки, тело, параметры)
        given()
                .baseUri(baseUrl)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                // when - выполнение запроса (GET, POST, PUT, DELETE)
                .when()
                .post("user")
                // then - проверка ответа (статус код, тело, заголовки)
                .then()
                // проверка с помощью хамкреста статус кода и тела ответа, что код 200, тип unknown и message не пустой
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", notNullValue(String.class));

    }

    @Test
    void createUserControllerTest() {
        UserController userController = new UserController();

//  используем конструктор для тестовых данных
        User userBuilder = User.builder()
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .phone("password")
                .userStatus(0)
                .build();

        // вызываем метод создания пользователя
        Response response = userController.createUser(userBuilder);
        // получаем ответ и делаем десирилизацию
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Test
    void fullUserLifecycleTest() {
        UserController userController = new UserController();

        // Create
        User user = User.builder().username("testUser").build();
        Response createResponse = userController.createUser(user);
        assertEquals(200, createResponse.statusCode());

        // Read
        Response getResponse = userController.getUserByUsername("testUser");
        assertEquals(200, getResponse.statusCode());

        // Delete
        Response deleteResponse = userController.deleteUserByUsername("testUser");
        assertEquals(200, deleteResponse.statusCode());

        // Verify deleted
        Response getAfterDelete = userController.getUserByUsername("testUser");
        assertEquals(404, getAfterDelete.statusCode());
    }

    //  тест c использованием testdata, с дефолтным и невалидным пользователем
    @Test
    void createUserControllerTestDataTest() {
        UserController userController = new UserController();

        Response response = userController.createUser(DEFAULT_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Test
    void createUserControllerTest2() {
        UserController userController = new UserController();

        Response response = userController.createUser(INVALID_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }


    // параметризация тестов, Тест выполняется два раза, так как в static Stream<User> users() мы задали оба варианта: DEFAULT_USER, INVALID_USER
    // Для параметризации с помощью JUnit 5 можно использовать аннотацию @ParameterizedTest. Для TestNG - это будет @DataProvider.
    static Stream<User> users() {
        return Stream.of(DEFAULT_USER, INVALID_USER);
    }

    @ParameterizedTest
    @MethodSource("users")
    void createUserParametrizedTest(User user) {
        UserController userController = new UserController();
        Response response = userController.createUser(user);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }
}

