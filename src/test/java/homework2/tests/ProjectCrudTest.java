package homework2.tests;

import com.github.javafaker.Faker;
import homework2.adapters.ProjectAdapter;
import homework2.models.ProjectRequest;
import homework2.models.ProjectResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectCrudTest {
    private final Faker faker = new Faker();

    private final String uniqueCode = faker.regexify("[A-Z0-9]{8}");
    private ProjectAdapter projectAdapter;

    @BeforeClass
    public void setUp() {
        projectAdapter = new ProjectAdapter();
    }

    @Test(description = "Проверка успешного создания проекта",
            testName = "Создание нового проекта",
            priority = 1)
    @Description("Пользователь создаёт новый проект через API и проверяет, что проект появился с правильным кодом")
    @Story("CRUD операции с проектами")
    @Owner("Якушин Андрей")
    public void testCreateProject() {
        ProjectRequest request = ProjectRequest.builder()
                .title("CRUD Project")
                .code(uniqueCode)
                .description("Test CRUD operations")
                .access("all")
                .build();

        Response response = projectAdapter.createProject(request);
        Assert.assertEquals(response.statusCode(), 200);

        ProjectResponse projectResponse = response.as(ProjectResponse.class);
        Assert.assertTrue(projectResponse.getStatus());
        Assert.assertEquals(projectResponse.getResult().getCode(), uniqueCode);
    }

    @Test(description = "Проверка успешного получения проекта по коду",
            testName = "Получение проекта",
            priority = 2)
    @Description("Пользователь получает данные существующего проекта по его уникальному коду и проверяет, что данные корректны")
    @Story("CRUD операции с проектами")
    @Owner("Якушин Андрей")
    public void testGetProject() {
        createProjectForTest();

        Response response = projectAdapter.getProject(uniqueCode);
        Assert.assertEquals(response.statusCode(), 200);

        ProjectResponse projectResponse = response.as(ProjectResponse.class);
        Assert.assertTrue(projectResponse.getStatus());
        Assert.assertEquals(projectResponse.getResult().getCode(), uniqueCode);
    }

    @Test(description = "Проверка успешного удаления проекта",
            testName = "Удаление проекта",
            priority = 3)
    @Description("Пользователь удаляет существующий проект по его уникальному коду и проверяет успешность операции")
    @Story("CRUD операции с проектами")
    @Owner("Якушин Андрей")
    public void testDeleteProject() {
        createProjectForTest();

        Response response = projectAdapter.deleteProject(uniqueCode);
        Assert.assertEquals(response.statusCode(), 200);
    }

    private void createProjectForTest() {
        ProjectRequest request = ProjectRequest.builder()
                .title("CRUD Project")
                .code(uniqueCode)
                .description("Test CRUD operations")
                .access("all")
                .build();
        projectAdapter.createProject(request);
    }
}
