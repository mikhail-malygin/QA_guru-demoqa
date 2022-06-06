package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormPage extends CalendarComponent {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement userEmailInput = $("#userEmail");
    SelenideElement genderCheckBox = $("#genterWrapper");
    SelenideElement userNumberInput = $("#userNumber");
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement subjectInput = $("#subjectsInput");
    SelenideElement hobbyInput = $("#hobbiesWrapper");
    SelenideElement uploadFileButton = $("#uploadPicture");
    SelenideElement addressInput =  $("#currentAddress");
    SelenideElement stateSelector =  $("#state");
    SelenideElement citySelector =  $("#city");
    SelenideElement submitButton =  $("[id=submit]");
    SelenideElement titleSubmitModalWindow =  $("#example-modal-sizes-title-lg");

    public StudentRegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    public StudentRegistrationFormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    public StudentRegistrationFormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    public StudentRegistrationFormPage setUserEmail(String userEmail) {
        userEmailInput.setValue(userEmail);

        return this;
    }

    public StudentRegistrationFormPage setGender(String gender) {
        genderCheckBox.$(byText(gender)).click();

        return this;
    }

    public StudentRegistrationFormPage setPhoneNumber(String phoneNumber) {
        userNumberInput.setValue(phoneNumber);

        return this;
    }

    public StudentRegistrationFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public StudentRegistrationFormPage setSubject(String subject) {
        subjectInput.setValue(subject).pressTab();

        return this;
    }

    public StudentRegistrationFormPage setHobby(String hobby) {
        hobbyInput.$(byText(hobby)).click();

        return this;
    }

    public StudentRegistrationFormPage uploadPicture(String path) {
        uploadFileButton.uploadFromClasspath(path);

        return this;
    }

    public StudentRegistrationFormPage setCurrentAddress(String address) {
        addressInput.setValue(address);

        return this;
    }

    public StudentRegistrationFormPage setState(String state) {
        stateSelector.click();
        stateSelector.$(byText(state)).click();

        return this;
    }

    public StudentRegistrationFormPage setCity(String city) {
        citySelector.click();
        citySelector.$(byText(city)).click();

        return this;
    }

    public void submit() {
        submitButton.click();
    }

    public StudentRegistrationFormPage checkResult(String key, String value) {
        titleSubmitModalWindow.shouldHave(text("Thanks for submitting the form"));
        resultTableComponent.checkTable(key, value);

        return this;
    }
}
