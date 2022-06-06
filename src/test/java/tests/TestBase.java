package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.StudentRegistrationFormPage;

public class TestBase {

    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
}
