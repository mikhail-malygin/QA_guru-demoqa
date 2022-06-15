package pages.github;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class GithubSelenideWikiTab {

    protected static final SelenideElement showMorePages = $(".Box-row.wiki-more-pages-link");
    protected static final SelenideElement softAssertionsLink = $("ul[data-filterable-for=wiki-pages-filter] " +
                                                                 "a[href=\"/selenide/selenide/wiki/SoftAssertions\"]");
    protected static final SelenideElement junit5Anchor = $("#user-content-3-using-junit5-extend-test-class");

    public void goToSoftAssertionsPage() {
        showMorePages.$(byText("Show 2 more pages…")).click();
        softAssertionsLink.click();

    }

    public void checkJUnit5BlockDisplayed() {
        junit5Anchor.parent().shouldHave(text("Using JUnit5 extend test class:"));
        junit5Anchor.parent().sibling(0).shouldHave(attribute("class",
                        "highlight highlight-source-java notranslate position-relative overflow-auto"));

    }
}
