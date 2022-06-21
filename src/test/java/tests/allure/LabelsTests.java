package tests.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTests {

    @Test
    @Owner("malyginms")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Allure")
    @Story("Static allure")
    @DisplayName("Static Labels Tests")
    @Description("Check issue tab in github")
    @Link(name = "GitHub", url = "https://github.com/")
    public void staticLabelsTests() {

    }

    @Test
    public void dynamicLabelsTests() {
        Allure.label("owner", "malyginms");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.feature("Work with labels");
        Allure.story("Dynamic allure");
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Not the best test"));
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setDescription("Check issue tab in github"));
        Allure.link("GitHub","https://github.com/" );
    }

    @Test
    public void parametersTests() {
        Allure.parameter("Sity", "Moscow");
        Allure.parameter("UTC time", "UTC + 3");

    }
}
