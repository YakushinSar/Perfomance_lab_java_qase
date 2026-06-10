package homework2.adapters;

import homework2.models.ProjectRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static homework2.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;

public class ProjectAdapter {

    private final RequestSpecification requestSpec;

    public ProjectAdapter() {
        this.requestSpec = spec;
    }

    // CREATE
    @Step("Создать проект с кодом {request.code}")
    public Response createProject(ProjectRequest request) {
        return given(requestSpec)
                .body(request)  // автоматическая сериализация
                .log().all()
                .when()
                .post("/project")
                .then()
                .log().all()
                .extract().response();
    }

    // READ
    @Step("Получить проект по коду {code}")
    public Response getProject(String code) {
        return given(requestSpec)
                .log().all()
                .when()
                .get("/project/{code}", code)
                .then()
                .log().all()
                .extract().response();
    }

    // DELETE
    @Step("Удалить проект с кодом {code}")
    public Response deleteProject(String code) {
        return given(requestSpec)
                .log().all()
                .when()
                .delete("/project/{code}", code)
                .then()
                .log().all()
                .extract().response();
    }
}