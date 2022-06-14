package tests;

import com.codeborne.selenide.selector.ByText;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsGitHubTests {

    @Test
    void checkJUnit5CodeInPageTests() {
        open("https://github.com/");
        $("[data-test-selector=nav-search-input]").setValue("Selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();
        $("#wiki-tab").click();
        $(new ByText("Soft assertions")).click();

        $("#user-content-3-using-junit5-extend-test-class").parent().shouldHave(text("Using JUnit5 extend test class:"));
        $("#user-content-3-using-junit5-extend-test-class").parent().sibling(0)
                                                                     .shouldHave(attribute("class",
                                                                                     "highlight highlight-source-java notranslate position-relative overflow-auto"));



    }

}
