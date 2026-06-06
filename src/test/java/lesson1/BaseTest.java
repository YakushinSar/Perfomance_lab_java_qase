package lesson1;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        // настройка селенид идет с помощью Configuration
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.clickViaJs = true;
//    Configuration.assertionMode = AssertionMode.SOFT;
        Configuration.headless = false;
        // настройка опций селениума
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        // все объявленные опции попадают в селенид
        Configuration.browserCapabilities = options;

        // для аллюр
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterMethod
    public void quit() {
        getWebDriver().quit();
    }
}
