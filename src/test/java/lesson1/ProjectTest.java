package lesson1;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProjectTest extends BaseTest {

    @Test
    public void checkCreateProject() {
        Selenide.open("/login");
        // селенидэлемент обозначается $, по умолчанию в параметрах используется css селектор
        $("[name=email]").setValue("qai@teachmeksills.com");
        $("[name=password]").setValue("Kazantip001!");
        //  можно искать сразу по тексту с помощью byText
        $(byText("Sign in")).click();
        $(byText("Create new project")).click();
        sleep(2000);
        $("#project-name").setValue("ITMS");
        $("#project-code").setValue("123");
        $(byText("Create project")).click();
        sleep(2000);
        $(byText("Projects")).click();
        // вызываем метод удаления по имени, и передает в него имя созданного выше проекта
        deleteProject("ITMS");
        sleep(2000);
    }

    // метод для удаления ранее созданного проекта по имени
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
}
