package pages.github;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class GithubSelenideWikiTab {

    protected static final SelenideElement softAssertionsLink = $(new ByText("Soft assertions"));
    protected static final SelenideElement junit5Anchor = $("#user-content-3-using-junit5-extend-test-class");

    public void goToSoftAssertionsPage() {
        softAssertionsLink .click();

    }

    public void checkJUnit5BlockDisplayed() {
        junit5Anchor.parent().shouldHave(text("Using JUnit5 extend test class:"));
        junit5Anchor.parent().sibling(0).shouldHave(attribute("class",
                        "highlight highlight-source-java notranslate position-relative overflow-auto"));

    }
}
