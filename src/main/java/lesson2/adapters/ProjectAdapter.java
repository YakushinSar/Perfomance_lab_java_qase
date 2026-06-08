package lesson2.adapters;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import lesson2.models.ProjectRequest;
import lesson2.models.ProjectResponse;

import static lesson2.adapters.BaseAdapter.*;

public class ProjectAdapter extends RestAssured {

    // адаптеры создаются для каждой сущности

    public static ProjectResponse createProject(ProjectRequest projectRequest) {
        return given()
                .spec(spec)
                .body(gson.toJson(projectRequest)) // тут превращаем объект билдер в формат json
                .log().all()
                .when()
                .post("/project")
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/projectShema.json")) // проверка по схеме
                .log().all()
                .spec(ok200)
                .extract().as(ProjectResponse.class);
    }

    public static void deleteProject(String code) {
        given()
                .spec(spec)
                .log().all()
                .when()
                .delete("/project/{code}")
                .then()
                .log().all()
                .spec(ok200);
    }
}