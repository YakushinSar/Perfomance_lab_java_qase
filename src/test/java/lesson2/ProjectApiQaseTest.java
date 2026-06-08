package lesson2;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import lesson2.adapters.ProjectAdapter;
import lesson2.models.ProjectRequest;
import lesson2.models.ProjectResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static lesson2.adapters.ProjectAdapter.createProject;

public class ProjectApiQaseTest {
    // создаем объект от models в формате json с помощью библиотеки Gson
    Gson gson = new Gson();

    ProjectRequest projectRequest = ProjectRequest.builder()
            .title("IT123")
            .code("AQQQ")
            .description("test")
            .access("all")
            .group("test1")
            .build();

    // создаем проект через бек
    @Test
    public void checkCreateProject() {
        String responce = given()
                .contentType(ContentType.JSON)
                .header("Token", "370398eba89fa3dda029f46639290281bbfce75b93f89969179bbe0b7b4a9691")
                .baseUri("https://api.qase.io")
                .basePath("/v1")
                .body(gson.toJson(projectRequest)) // тут превращаем объект билдер в формат json
                .log().all()
                .when()
                .post("/project")
                .then()
                .log().all()
                .statusCode(200)
                .extract().asString(); // получаем строку

//      создается объект ответа из models
        ProjectResponse projectResponse = gson.fromJson(responce, ProjectResponse.class);
        Assert.assertTrue(projectResponse.status);
        Assert.assertEquals(projectResponse.result.code, "12345");

    }

    // обновленная версия метода создания и удаления проекта
    @Test
    public void checkCreateProject2() {
        ProjectResponse pr = createProject(projectRequest);
        Assert.assertTrue(pr.status);
        Assert.assertEquals(pr.result.code, "AQQQ");

        ProjectAdapter.deleteProject("AQQQ");
    }
}
