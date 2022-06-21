package tests.allure;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class IssueAssertionGitHubTests extends TestBase{

    private final String REPOSITORY = "mikhail-malygin/QA_guru-demoqa";
    private final String ISSUE_HEADER = "There aren’t any open issues.";

    @Test
    @Description("Check issue tab in github using Selenide")
    public void checkIssueGitHubTests() {

        open("https://github.com/");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("mikhail-malygin/QA_guru-demoqa");
        $(".header-search-input").submit();

        $(linkText("mikhail-malygin/QA_guru-demoqa")).click();
        $(partialLinkText("Issues")).click();
        $("h3").shouldHave(text(ISSUE_HEADER));
    }

    @Test
    @Description("Check issue tab in github using lambda approach")
    public void checkIssueGitHubLambdaApproachTests() {

        step("Open the main page", () -> open("https://github.com/"));
        step("Looking for a repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Go to a result page", () -> $(linkText(REPOSITORY)).click());
        step("Open tab Issues", () -> $(partialLinkText("Issues")).click());
        step("Сheck a header of issue tab. It should be " + ISSUE_HEADER, () -> {
            $("h3").shouldHave(text(ISSUE_HEADER));
        });
    }

    @Test
    @Description("Check issue tab in github using steps approach")
    public void checkIssueGitHubStepsApproachTests() {

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.checkIssueHeader(ISSUE_HEADER);
    }
}
