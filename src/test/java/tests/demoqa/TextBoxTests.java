package tests.demoqa;

import org.junit.jupiter.api.Test;
import tests.demoqa.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TextBoxTests extends TestBase {

    @Test
    void successfulTest() {
        open("/text-box");
        $("#userName").setValue("Иванов Петр Иванович");
        $("#userEmail").setValue("test@mail.ru");
        $("#currentAddress").setValue("Россия, Москва, Кремль, Спасская башня");
        $("#permanentAddress").setValue("Москва, ул. Тверская 17, кв. 64");
        $("#submit").click();


        $("#output").shouldHave(text("Иванов Петр Иванович"),
                                             text("test@mail.ru"),
                                             text("Россия, Москва, Кремль, Спасская башня"),
                                             text("Москва, ул. Тверская 17, кв. 64"));
    }


}
