package pages.github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubHomePage {

    protected static final SelenideElement signUpButton = $("[type=submit]");
    protected static final SelenideElement searchInput = $("[data-test-selector=nav-search-input]");

    public GithubHomePage openHomePage() {
        open(Configuration.baseUrl);
        signUpButton.shouldHave(text("Sign up for GitHub"));

        return this;
    }

    public void search(String search) {
        searchInput.setValue(search).pressEnter();
    }
}
