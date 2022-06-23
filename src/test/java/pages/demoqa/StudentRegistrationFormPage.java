package pages.demoqa;

import com.codeborne.selenide.SelenideElement;
import pages.demoqa.components.CalendarComponent;
import pages.demoqa.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage extends CalendarComponent {

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    protected static final SelenideElement firstNameInput = $("#firstName");
    protected static final SelenideElement lastNameInput = $("#lastName");
    protected static final SelenideElement userEmailInput = $("#userEmail");
    protected static final SelenideElement genderCheckBox = $("#genterWrapper");
    protected static final SelenideElement userNumberInput = $("#userNumber");
    protected static final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    protected static final SelenideElement subjectInput = $("#subjectsInput");
    protected static final SelenideElement hobbyInput = $("#hobbiesWrapper");
    protected static final SelenideElement uploadFileButton = $("#uploadPicture");
    protected static final SelenideElement addressInput =  $("#currentAddress");
    protected static final SelenideElement stateSelector =  $("#state");
    protected static final SelenideElement citySelector =  $("#city");
    protected static final SelenideElement submitButton =  $("[id=submit]");
    protected static final SelenideElement titleSubmitModalWindow =  $("#example-modal-sizes-title-lg");

    public StudentRegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

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
