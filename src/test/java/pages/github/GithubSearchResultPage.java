package pages.github;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class GithubSearchResultPage {

    protected static final ElementsCollection searchResults = $$("ul.repo-list li");

    public void goToSearchResultPage() {
        searchResults.first().$("a").click();

    }
}
