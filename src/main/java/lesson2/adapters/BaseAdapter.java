package lesson2.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAdapter {
    static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation() // при серилизации участвуют только те параметры которые помечены аннотацией @Expose
            .setPrettyPrinting() // для удобства читабельности json
            .create();

    // создание спецификации запроса
    public static RequestSpecification spec = new RequestSpecBuilder()
            .setContentType("application/json")
            .setBaseUri("https://api.qase.io")
            .setBasePath("/v1")
            .addHeader("Token", "fa08486095b4c8f7e88d545b31c2dde92389abdd121e6600769a35c6f21cc5c5")
            .build();

    // создание спецификации ответа
    public static ResponseSpecification ok200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

}
