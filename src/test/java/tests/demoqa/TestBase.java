package tests.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.demoqa.StudentRegistrationFormPage;

import static java.lang.String.format;

public class TestBase {

    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();
    static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void beforeAll() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true); // включение потовокого вещание видео
        capabilities.setCapability("enableVideo", true); // запись видео теста, записывается на сервер селеноида

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = format("https://%s:%s@%s", config.login(), config.password(),
                                      System.getProperty("selenoidStand"));
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
