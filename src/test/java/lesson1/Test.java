package lesson1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Test extends BaseTest {

    @org.testng.annotations.Test
    public void checkCreateProject() {
        Selenide.open("/login");
        // селенидэлемент обозначается $, по умолчанию в параметрах используется css селектор
        $("[name=email]").setValue("qai@teachmeksills.com");
        $("[name=password]").setValue("Kazantip001!");
        //  можно искать сразу по тексту с помощью byText
        $(byText("Sign in")).click();

        $("[type=radio]").selectRadio("AccountsGemailAddress0");
        // работа с селектами
        $("#account-type").attr("select");
        // ассертионы, проверки
        $("[name=password]").shouldBe(Condition.visible);
        $("[name=password]").shouldHave(Condition.name("password"));

        // доступ к драйверу с которым мы работаем в данной сессии с селенид и далее обращаясь к driver с ним работать
        WebDriver driver = getWebDriver();
        driver.get("http://localhost/litecart");
    }
}
