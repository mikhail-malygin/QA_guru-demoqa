package tests.allure;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Open the main page")
    public void openMainPage() {
        open("https://github.com/");
    }

    @Step("Looking for a repository {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("Go to a result page")
    public void openRepositoryLink(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Open tab Issues")
    public void openIssueTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Сheck a header of issue tab. It should be {issue_header}")
    public void checkIssueHeader(String issue_header) {
        $("h3").shouldHave(text(issue_header));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
