package lesson2;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProjectApiQaseTest {

    @Test
    public void checkCreateProject() {
        given()
                .contentType(ContentType.JSON)
                .header("Token", "2868f2ce1c11ace5293980f1b47b99849a7788350ebc76de225b3de43fd1032c")
                .baseUri("https://api.qase.io")
                .basePath("/v1")
                .body("{ \n" +
                        "    \"title\": \"ITM101\", \n" +
                        "    \"code\": \"itm1\", \n" +
                        "    \"description\": \"test\", \n" +
                        "    \"access\": \"all\", \n" +
                        "    \"group\": \"test1\"\n" +
                        "}")
                .log().all()
                .when()
                .post("/project")
                .then()
                .log().all()
                .statusCode(200);
    }
}
