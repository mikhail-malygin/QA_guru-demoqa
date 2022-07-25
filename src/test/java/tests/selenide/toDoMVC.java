package tests.selenide;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class toDoMVC {

    @Test
    void toDoMVCTests() {
        open("https://todomvc.com/examples/react/#/");
        $(".new-todo").setValue("task1").pressEnter();
        $(".new-todo").setValue("task2").pressEnter();
        $$("label").findBy(exactText("task2")).doubleClick();
    }
}
