package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successfulTest() {
        open("/text-box");
        $("[id=userName]").setValue("Иванов Петр Иванович");
        $("[id=userEmail]").setValue("test@mail.ru");
        $("[id=currentAddress]").setValue("Россия, Москва, Кремль, Спасская башня");
        $("[id=permanentAddress]").setValue("Москва, ул. Тверская 17, кв. 64");
        $("[id=submit]").click();


        $("[id=output]").shouldHave(text("Иванов Петр Иванович"),
                                             text("test@mail.ru"),
                                             text("Россия, Москва, Кремль, Спасская башня"),
                                             text("Москва, ул. Тверская 17, кв. 64"));
    }


}
