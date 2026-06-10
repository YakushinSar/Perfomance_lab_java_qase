package homework2.adapters;

import homework2.models.CaseRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static homework2.adapters.BaseAdapter.spec;
import static io.restassured.RestAssured.given;

public class CaseAdapter {

    private final RequestSpecification requestSpec;

    public CaseAdapter() {
        this.requestSpec = spec;
    }

    // CREATE case
    @Step("Создать тест-кейс в проекте {projectCode}")
    public Response createCase(String projectCode, CaseRequest caseRequest) {
        return given(requestSpec)
                .body(caseRequest)
                .log().all()
                .when()
                .post("/case/{projectCode}", projectCode)
                .then()
                .log().all()
                .extract().response();
    }
}
