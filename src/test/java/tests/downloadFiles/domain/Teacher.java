package tests.downloadFiles.domain;

public class Teacher {

    private String name;
    private Boolean isGoodTeacher;
    private Integer age;
    private Passport passport;

    public static class Passport {
        private Integer number;
        private String dateIssue;

        public Integer getNumber() {
            return number;
        }

        public String getDateIssue() {
            return dateIssue;
        }
    }


    public String getName() {
        return name;
    }

    public Boolean isGoodTeacher() {
        return isGoodTeacher;
    }

    public Integer getAge() {
        return age;
    }

    public Passport getPassport() {
        return passport;
    }
}
