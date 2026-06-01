package homework1.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.clickViaJs = true;
        Configuration.headless = false;

        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--start-maximized")
                .addArguments("--disable-notifications")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-infobars");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)       // скриншоты при падении — есть
                .savePageSource(false)); // HTML страницы НЕ сохраняем
    }

    @AfterMethod
    public void quit() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}

