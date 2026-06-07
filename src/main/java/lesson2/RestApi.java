package lesson2;

public class RestApi {

    /*
# REST Assured - библиотека практически для всех языков программирования, позволяющая взаимодействовать с веб-сервисами
 Библиотека позволяет работать с разными типами веб-сервисов, входными/выходными данными в разных форматах (json, xml),
 обрабатывать коды ответа
Зависимость:
<!-- Source: https://mvnrepository.com/artifact/io.rest-assured/rest-assured -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>6.0.0</version>
    <scope>test</scope>
</dependency>

Как валидировать ответ?
1. Валидировать только интересующие нас поля
2. Поместить ожидаемый результат в файл (например json) и сравнивать с ответом (Актуально только для статичных запроса/ответа)
3. Обернуть ответ в объект и валидировать полностью либо частично (сериализация и десериализация)
Пример:
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

# GSON - библиотека от Google, позволяющая взаимодействовать с JSON-строками с помощью аннотаций:
<!-- Source: https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.14.0</version>
    <scope>compile</scope>
</dependency>

     */
}
