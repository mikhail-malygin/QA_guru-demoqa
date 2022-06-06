package tests;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import pages.StudentRegistrationFormPage;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestData extends StudentRegistrationFormPage {

    private final static Faker fakerEnglish = new Faker(new Locale("en"));
    private final static Faker fakerRussian = new Faker(new Locale("ru"));

    private final static List<String> allMonths = Arrays.asList("January", "February", "March",
                                                                "April", "May", "June",
                                                                "July", "August", "September",
                                                                "October", "November", "December");

    private final static List<String> severalSubjects = Arrays.asList("Physics", "Chemistry", "Computer Science",
                                                                      "Commerce", "Accounting", "Economics",
                                                                      "Social Studies", "Civics");

    private final static List<String> allStates = Arrays.asList("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    private final static List<String> allCitiesStateNCR = Arrays.asList("Delhi", "Gurgaon", "Noida");
    private final static List<String> allCitiesStateUttarPradesh = Arrays.asList("Agra", "Lucknow", "Merrut");
    private final static List<String> allCitiesStateHaryana = Arrays.asList("Karnal", "Panipat");
    private final static List<String> allCitiesStateRajasthan = Arrays.asList("Jaipur", "Jaiselmer");

    protected final static String FIRST_NAME = fakerRussian.name().firstName(),
                                  LAST_NAME = fakerRussian.name().lastName(),
                                  USER_EMAIL = fakerEnglish.internet().emailAddress(),
                                  GENDER = getRandomGenderValue(),
                                  PHONE_NUMBER = fakerRussian.number().digits(10),
                                  DAY_OF_BIRTH = String.valueOf(fakerEnglish.number().numberBetween(1, 28)),
                                  MONTH_OF_BIRTH = getRandomValue(allMonths),
                                  YEAR_OF_BIRTH = String.valueOf(fakerEnglish.number().numberBetween(1922, 2006)),
                                  SUBJECT = getRandomValue(severalSubjects),
                                  HOBBY = getRandomHobbyValue(),
                                  PATH_PICTURE = "img/test_picture_qa_guru.png",
                                  NAME_PICTURE = "test_picture_qa_guru.png",
                                  CURRENT_ADDRESS = fakerRussian.address().streetAddress(),
                                  STATE = getRandomValue(allStates),
                                  CITY = getRandomConnectedValue();

    private static String getRandomGenderValue() {
        ElementsCollection allGenderValues = genderCheckBox.$$("input");

        return allGenderValues.get(fakerEnglish.number().numberBetween(0, allGenderValues.size() - 1)).getValue();
    }


    private static String getRandomValue(List<String> list) {
        Random rand = new Random();

        return list.get(rand.nextInt(list.size()));
    }

    private static String getRandomHobbyValue() {
        ElementsCollection allHobbyValues = hobbyInput.$$(".custom-control-label");

        return allHobbyValues.get(fakerEnglish.number().numberBetween(0, allHobbyValues.size() - 1)).getText();
    }

    private static String getRandomConnectedValue() {
        Random rand = new Random();
        switch (STATE) {
            case "NCR":
                return allCitiesStateNCR.get(rand.nextInt(allCitiesStateNCR.size()));
            case "Uttar Pradesh":
                return allCitiesStateUttarPradesh.get(rand.nextInt(allCitiesStateUttarPradesh.size()));
            case "Haryana":
                return allCitiesStateHaryana.get(rand.nextInt(allCitiesStateHaryana.size()));
            case "Rajasthan":
                return allCitiesStateRajasthan.get(rand.nextInt(allCitiesStateRajasthan.size()));
        }
        return null;
    }
}
