package homework2.tests;

import com.github.javafaker.Faker;
import homework2.adapters.CaseAdapter;
import homework2.adapters.ProjectAdapter;
import homework2.models.CaseRequest;
import homework2.models.CaseResponse;
import homework2.models.ProjectRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CaseTest {

    private final Faker faker = new Faker();
    private ProjectAdapter projectAdapter;
    private CaseAdapter caseAdapter;
    private String projectCode;

    @BeforeMethod
    public void setUp() {
        projectAdapter = new ProjectAdapter();
        caseAdapter = new CaseAdapter();

        projectCode = faker.regexify("[A-Z0-9]{8}");

        // Создаём проект для кейсов
        ProjectRequest projectRequest = ProjectRequest.builder()
                .title("Project for Cases")
                .code(projectCode)
                .description("Project with test cases")
                .access("all")
                .build();
        projectAdapter.createProject(projectRequest);
    }

    @AfterMethod
    public void tearDown() {
        // Удаляем проект после теста
        projectAdapter.deleteProject(projectCode);
    }

    @Test(description = "Проверка успешного создания тест-кейса",
            testName = "Создание тест-кейса",
            priority = 1)
    @Description("Пользователь создаёт новый тест-кейс в существующем проекте и проверяет, что кейс создан с правильными данными")
    @Story("Работа с тест-кейсами")
    @Owner("Якушин Андрей")
    public void testCreateCase() {
        CaseRequest caseRequest = CaseRequest.builder()
                .title("Login Test")
                .description("Check login with valid credentials")
                .preconditions("User is registered")
                .postconditions("User is logged out")
                .severity(4)
                .priority(2)
                .type(1)
                .build();

        Response response = caseAdapter.createCase(projectCode, caseRequest);
        Assert.assertEquals(response.statusCode(), 200);

        CaseResponse caseResponse = response.as(CaseResponse.class);
        Assert.assertTrue(caseResponse.getStatus());
        Assert.assertNotNull(caseResponse.getResult().getId());
    }
}