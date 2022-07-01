package tests.demoqa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import pages.demoqa.enums.Month;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static tests.demoqa.TestData.*;
import static tests.demoqa.TestData.CITY;

public class StudentRegistrationFormByParametrizedTests extends TestBase {

    @CsvFileSource(resources = "tests.data/data_studentRegistrationForm.csv")
    @ParameterizedTest(name = "Filling a student registration form test using the next test data: " +
            "First Name = {0}, Last Name = {1}, Email = {2}, Gender = {3}, Mobile = {4}," +
            "Day of birth = {5}, Month of birth = {6}, Year of birth = {7}, Subject = {8}" +
            "Hobby = {9}, Picture name = {10}, Picture path = {11}, Current address = {12}," +
            "State = {13}, City = {14}")
    @Tag("demoQa")
    void fillingRegistrationFormUsingCsvFileSource(String firstName, String lastName, String email, String gender,
                                                   String mobile, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                                   String subject, String hobby, String pictureName, String picturePath,
                                                   String currentAddress, String state, String city) {
        studentRegistrationFormPage.openPage()
                                   .setFirstName(firstName)
                                   .setLastName(lastName)
                                   .setUserEmail(email)
                                   .setGender(gender)
                                   .setPhoneNumber(mobile)
                                   .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                                   .setSubject(subject)
                                   .setHobby(hobby)
                                   .uploadPicture(picturePath)
                                   .setCurrentAddress(currentAddress)
                                   .setState(state)
                                   .setCity(city)
                                   .submit();

        studentRegistrationFormPage.checkResult("Student Name", firstName + " " + lastName)
                                   .checkResult("Student Email", email)
                                   .checkResult("Gender", gender)
                                   .checkResult("Mobile", mobile)
                                   .checkResult("Date of Birth", (dayOfBirth + " " + monthOfBirth + "," + yearOfBirth))
                                   .checkResult("Subjects", subject)
                                   .checkResult("Hobbies", hobby)
                                   .checkResult("Picture", pictureName)
                                   .checkResult("Address", currentAddress)
                                   .checkResult("State and City", state + " " + city);
    }


    @EnumSource(Month.class)
    @ParameterizedTest(name = "Filling a student registration form test using all months of year: {0}")
    @Tag("demoQa")
    @Disabled
    void fillingRegistrationFormUsingEnumTest(Month month) {

        studentRegistrationFormPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setUserEmail(USER_EMAIL)
                .setGender(GENDER)
                .setPhoneNumber(PHONE_NUMBER)
                .setDateOfBirth(DAY_OF_BIRTH, month.desc, YEAR_OF_BIRTH)
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
                .checkResult("Date of Birth", (DAY_OF_BIRTH + " " + month.desc + "," + YEAR_OF_BIRTH))
                .checkResult("Subjects", SUBJECT)
                .checkResult("Hobbies", HOBBY)
                .checkResult("Picture", NAME_PICTURE)
                .checkResult("Address", CURRENT_ADDRESS)
                .checkResult("State and City", STATE + " " + CITY);
    }

    @ValueSource(strings = {"Sports", "Reading", "Music"})
    @ParameterizedTest(name = "Filling a student registration form test using all hobbies: {0}")
    @Tag("demoQa")
    void fillingRegistrationFormUsingValueSource(String hobby) {

        studentRegistrationFormPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setUserEmail(USER_EMAIL)
                .setGender(GENDER)
                .setPhoneNumber(PHONE_NUMBER)
                .setDateOfBirth(DAY_OF_BIRTH, MONTH_OF_BIRTH, YEAR_OF_BIRTH)
                .setSubject(SUBJECT)
                .setHobby(hobby)
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
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", NAME_PICTURE)
                .checkResult("Address", CURRENT_ADDRESS)
                .checkResult("State and City", STATE + " " + CITY);
    }

    static Stream<Arguments> birthdayDataProvider() {
        return Stream.of(
                Arguments.of("3", "May", "1983"),
                Arguments.of("10", "September", "1956"),
                Arguments.of("17", "June", "1970"),
                Arguments.of("23", "January", "2000"),
                Arguments.of("31", "December", "1999")
        );
    }

    @MethodSource(value = "birthdayDataProvider")
    @ParameterizedTest(name = "Filling a student registration form test using different birthdays: {0} {1} {2}")
    @Tag("demoQa")
    void fillingRegistrationFormUsingMethodSource(String day, String month, String year) {

        studentRegistrationFormPage.openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setUserEmail(USER_EMAIL)
                .setGender(GENDER)
                .setPhoneNumber(PHONE_NUMBER)
                .setDateOfBirth(day, month, year)
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
                .checkResult("Date of Birth", (day + " " + month + "," + year))
                .checkResult("Subjects", SUBJECT)
                .checkResult("Hobbies", HOBBY)
                .checkResult("Picture", NAME_PICTURE)
                .checkResult("Address", CURRENT_ADDRESS)
                .checkResult("State and City", STATE + " " + CITY);
    }
}
