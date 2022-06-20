package tests.downloadFiles.domain;

import java.util.List;

public class Forecast {

    private String city;
    private String date;
    private Integer temperature;
    private Boolean isRaining;
    private String humidity;
    private Integer pressure;
    private List<Wind> wind;


    public static class Wind {
        private Integer windSpeed;
        private String windDirection;

        public Integer getWindSpeed() {
            return windSpeed;
        }

        public String getWindDirection() {
            return windDirection;
        }
    }

    public Boolean getIsRaining() {
        return isRaining;
    }

    public Integer getPressure() {
        return pressure;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public List<Wind> getWind() {
        return wind;
    }

}
