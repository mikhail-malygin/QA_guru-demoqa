package tests.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void beforeEach() {
        Allure.label("owner", "malyginms");
        Allure.label("severity", SeverityLevel.NORMAL.value());
        Allure.feature("GitHub tabs");
        Allure.story("GitHub issues tab");
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Check issue tab in github"));
        Allure.link("GitHub","https://github.com/" );
        Allure.parameter("Sity", "Moscow");
        Allure.parameter("UTC time", "UTC + 3");
    }
}
