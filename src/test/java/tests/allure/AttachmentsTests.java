package tests.allure;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTests {

    @Test
    public void checkIssueGitHubLambdaApproachTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the main page", () -> {
            open("https://github.com/");
            attachment("Source", webdriver().driver().source());
            Allure.getLifecycle().addAttachment(
                    "Screenshot", "image/png", "png",
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));
        });

    }

    @Test
    public void checkIssueGitHubStepsApproachTests() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot();
    }
}
