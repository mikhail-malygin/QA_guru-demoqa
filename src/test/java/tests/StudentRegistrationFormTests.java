package tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    void fillingRegistrationFormTest() {

        open("/automation-practice-form");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue("Mikhail");
        $("#lastName").setValue("Malygin");
        $("#userEmail").setValue("test@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("2022");
        $("[aria-label='Choose Monday, May 30th, 2022']").click();

        $("#subjectsInput").setValue("Computer Science").pressTab();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/test_picture_qa_guru.png"));
        $("#currentAddress").setValue("Moscow, 17 Tverskaya st");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Panipat")).click();

        $("[id=submit]").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        checkTable("Student Name", "Mikhail Malygin");
        checkTable("Student Email", "test@mail.ru");
        checkTable("Gender", "Male");
        checkTable("Mobile", "1234567890");
        checkTable("Date of Birth", "30 May,2022");
        checkTable("Subjects", "Computer Science");
        checkTable("Hobbies", "Reading");
        checkTable("Picture", "test_picture_qa_guru.png");
        checkTable("Address", "Moscow, 17 Tverskaya st");
        checkTable("State and City", "Haryana Panipat");
    }

    void checkTable(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));
    }
}
