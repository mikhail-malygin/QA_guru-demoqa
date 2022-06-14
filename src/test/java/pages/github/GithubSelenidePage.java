package pages.github;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class GithubSelenidePage {

    protected static final SelenideElement wikiTab = $("#wiki-tab");

    public void goToWikiTab() {
        wikiTab.click();

    }
}
