package tests;

import org.junit.jupiter.api.Test;

import static tests.TestData.*;


public class StudentRegistrationFormTests extends TestBase {

    @Test
    void fillingRegistrationFormTest() {

        studentRegistrationFormPage.openPage()
                                   .setFirstName(FIRST_NAME)
                                   .setLastName(LAST_NAME)
                                   .setUserEmail(USER_EMAIL)
                                   .setGender(GENDER)
                                   .setPhoneNumber(PHONE_NUMBER)
                                   .setDateOfBirth(DAY_OF_BIRTH, MONTH_OF_BIRTH, YEAR_OF_BIRTH)
                                   .setSubject(SUBJECT)
                                   .setHobby(HOBBY)
                                   .uploadPicture(PATH_PICTURE)
                                   .setCurrentAddress(CURRENT_ADDRESS)
                                   .setState(STATE)
                                   .setCity(CITY)
                                   .submit();

        studentRegistrationFormPage.checkResult("Student Name", FIRST_NAME + " " + LAST_NAME)
                                   .checkResult("Student Email", USER_EMAIL)
                                   .checkResult("Gender", GENDER)
                                   .checkResult("Mobile", PHONE_NUMBER)
                                   .checkResult("Date of Birth", (DAY_OF_BIRTH + " " + MONTH_OF_BIRTH + "," + YEAR_OF_BIRTH))
                                   .checkResult("Subjects", SUBJECT)
                                   .checkResult("Hobbies", HOBBY)
                                   .checkResult("Picture", NAME_PICTURE)
                                   .checkResult("Address", CURRENT_ADDRESS)
                                   .checkResult("State and City", STATE + " " + CITY);
    }
}
