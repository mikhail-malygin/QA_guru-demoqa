package tests.github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.github.GithubHomePage;
import pages.github.GithubSearchResultPage;
import pages.github.GithubSelenidePage;
import pages.github.GithubSelenideWikiTab;

public class TestBase {

    GithubHomePage githubHomePage = new GithubHomePage();
    GithubSearchResultPage githubSearchResultPage = new GithubSearchResultPage();
    GithubSelenidePage githubSelenidePage = new GithubSelenidePage();
    GithubSelenideWikiTab githubSelenideWikiTab = new GithubSelenideWikiTab();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1080";
    }
}
