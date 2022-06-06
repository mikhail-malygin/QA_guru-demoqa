package tests;

import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    void fillingRegistrationFormTest() {

        String firstName = "Mikhail";
        String lastName = "Malygin";
        String userEmail = "test@mail.ru";
        String gender = "Male";
        String phoneNumber = "1234567890";
        String dayOfBirth = "30";
        String monthOfBirth = "May";
        String yearOfBirth = "2022";
        String subject = "Computer Science";
        String hobby = "Reading";
        String pathPicture = "img/test_picture_qa_guru.png";
        String namePicture = "test_picture_qa_guru.png";
        String currentAddress = "Moscow, 17 Tverskaya st";
        String state = "Haryana";
        String city = "Panipat";

        studentRegistrationFormPage.openPage()
                                   .setFirstName(firstName)
                                   .setLastName(lastName)
                                   .setUserEmail(userEmail)
                                   .setGender(gender)
                                   .setPhoneNumber(phoneNumber)
                                   .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                                   .setSubject(subject)
                                   .setHobby(hobby)
                                   .uploadPicture(pathPicture)
                                   .setCurrentAddress(currentAddress)
                                   .setState(state)
                                   .setCity(city)
                                   .submit();

        studentRegistrationFormPage.checkResult("Student Name", firstName + " " + lastName)
                                   .checkResult("Student Email", userEmail)
                                   .checkResult("Gender", gender)
                                   .checkResult("Mobile", phoneNumber)
                                   .checkResult("Date of Birth", (dayOfBirth + " " + monthOfBirth + "," + yearOfBirth))
                                   .checkResult("Subjects", subject)
                                   .checkResult("Hobbies", hobby)
                                   .checkResult("Picture", namePicture)
                                   .checkResult("Address", currentAddress)
                                   .checkResult("State and City", state + " " + city);
    }
}
