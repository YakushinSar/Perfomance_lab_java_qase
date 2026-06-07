package homework1.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class ProjectTest extends BaseTest {

    private static final String EMAIL = "a.yakushin.qa@gmail.com";
    private static final String PASSWORD = "sar123321!!!";

    @Test(description = "Проверка успешного создания проекта",
            testName = "Создание нового проекта",
            priority = 1)
    @Description("Пользователь создаёт новый проект и проверяет его появление")
    @Story("Создание проекта")
    @Owner("Якушин Андрей")
    public void checkCreateProject() {
        String projectName = "test13";
        String projectCode = "test13";

        Selenide.open("/login");
        $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();

        $("[name=email]").setValue(EMAIL);
        $("[name=password]").setValue(PASSWORD);
        $(byText("Sign in")).click();
        sleep(2000);

        $(byText("Create new project")).click();
        $("#project-name").setValue(projectName);
        $("#project-code").setValue(projectCode);
        $(byText("Create project")).click();
        sleep(2000);

        $(byText("Projects")).click();
        $(byText(projectName)).click();
        sleep(2000);

        $(byText(projectCode.toUpperCase())).shouldBe(visible);
    }

    @Step("Удалить проект по ранее созданному имени: '{projectName}'")
    public void deleteProject(String projectName) {
//        $x(String.format("//*[text()='%s']/ancestor::tr//button[@aria-label='Open action menu']", projectName)).click();
        $(byText(projectName))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        $("[data-testid=remove]").click();
        sleep(2000);
        $x("//span[text()='Delete project']").click();
    }

    @Test(description = "Проверка успешного удаления созданного проекта",
            testName = "Проект удален",
            priority = 2)
    @Description("Пользователь удаляет существующий проект и проверяет его отсутствие в списке")
    @Story("Удаление проекта")
    @Owner("Якушин Андрей")
    public void checkDeleteProject() {
        String projectName = "test13";

        Selenide.open("/login");
        $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
        $("[name=email]").setValue(EMAIL);
        $("[name=password]").setValue(PASSWORD);
        $(byText("Sign in")).click();
        sleep(2000);

        $(byText("Projects")).click();

        // Проверяем, что проект есть в списке перед удалением
        $(byText(projectName)).shouldBe(visible);
        deleteProject(projectName);

        // Обновляем страницу или перезаходим в Projects
        $(byText("Projects")).click();
        sleep(1000);
        // Проверка: проекта больше нет в списке
        $(byText(projectName)).shouldNotBe(visible);
    }
}



