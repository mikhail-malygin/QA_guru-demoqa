package tests.github;

import org.junit.jupiter.api.Test;

public class SoftAssertionsGitHubTests extends TestBase{

    @Test
    void checkJUnit5CodeInPageTests() {
        githubHomePage.openHomePage().search("Selenide");
        githubSearchResultPage.goToSearchResultPage();
        githubSelenidePage.goToWikiTab();
        githubSelenideWikiTab.goToSoftAssertionsPage();

        githubSelenideWikiTab.checkJUnit5BlockDisplayed();

    }

}
