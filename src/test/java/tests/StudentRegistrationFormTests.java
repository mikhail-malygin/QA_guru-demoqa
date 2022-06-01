package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillingRegistrationFormTest() {

        open("/automation-practice-form");

        $("#firstName").setValue("Mikhail");
        $("#lastName").setValue("Malygin");
        $("#userEmail").setValue("test@mail.ru");
        $(By.xpath("//label[text()='Male']")).click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("2022");
        $("[aria-label='Choose Monday, May 30th, 2022']").click();

        $("#subjectsInput").setValue("Computer Science").pressTab();
        $(By.xpath("//label[text()='Reading']")).click();
        $("#uploadPicture").uploadFile(new File("src/test/java/resources/test_picture_qa_guru.png"));
        $("#currentAddress").setValue("Moscow, 17 Tverskaya st");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Panipat")).click();

        $("[id=submit]").click();

        $(".modal-body").shouldHave(text("Mikhail"),
                text("Malygin"),
                text("test@mail.ru"),
                text("Male"),
                text("1234567890"),
                text("30 May,2022"),
                text("Computer Science"),
                text("Reading"),
                text("test_picture_qa_guru.png"),
                text("Moscow, 17 Tverskaya st"),
                text("Haryana Panipat"));
    }
}
