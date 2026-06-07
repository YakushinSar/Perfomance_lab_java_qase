package lesson2;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetStoreTest {

    @Test
    public void checkAddPathWithEmptyBody() {
        given() // дано
                .contentType(ContentType.JSON)  //  тип данных, это header
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2")
                .body("") // тело запроса
                .log().all()    // для логирование в консоли запроса
                .when() //  когда, работа с запросом
                .post("/pet")
                .then() //   тогда, работа ответом
                .log().all()    // для логирование в консоли ответа
                .statusCode(405)
                .body("code", equalTo(405))  // проверка тела ответа
                .body("type", equalTo("unknown"))
                .body("message", equalTo("no data"));
    }

    @Test
    public void checkCRUD() {
        // create
        long id = given() // дано
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"Tom\"\n" +
                        "  },\n" +
                        "  \"name\": \"Cat\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"www.avito.ru\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"Street\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .log().all()
                .when()
                .post("/pet")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("id"); // извлекаем из ответа id, который сгенерировался при создании питомца

        // read
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2")
                .pathParam("id", id)
                .log().all()
                .when()
                .get("/pet/{id}")
                .then()
                .log().all()
                .statusCode(200);

        // update
        given() // дано
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2")
                .body("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"Reks\"\n" +
                        "  },\n" +
                        "  \"name\": \"Dog\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"www.avito.ru\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"Cocer\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .log().all()
                .when()
                .put("/pet")
                .then()
                .log().all()
                .statusCode(200);

        // delete
        given()
                .contentType(ContentType.JSON)
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2")
                .pathParam("id", id)
                .log().all()
                .when()
                .delete("/pet/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }
}
