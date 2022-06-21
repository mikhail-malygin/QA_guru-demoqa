package tests.downloadFiles;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tests.downloadFiles.domain.Forecast;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import static java.nio.charset.StandardCharsets.UTF_8;

public class FileJsonParseTest {

    ClassLoader classLoader = FileJsonParseTest.class.getClassLoader();

    @Test
    void jsonParseTest() throws IOException {
        try (InputStream inputStream = classLoader.getResourceAsStream("forecast.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            assert inputStream != null;
            JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream, UTF_8));

            assertThat(jsonNode.get("city").asText()).isEqualTo("Moscow");
            assertThat(jsonNode.get("temperature").asInt()).isEqualTo(23);
            assertThat(jsonNode.get("isRaining").asBoolean()).isEqualTo(true);
            assertThat(jsonNode.get("wind").get(1).get("windDirection").asText()).isEqualTo("W");
        }
    }

    @Test
    void betterJsonParseTest() throws IOException {
        try (InputStream inputStream = classLoader.getResourceAsStream("forecast.json")) {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createParser(inputStream);
            ObjectMapper objectMapper = new ObjectMapper();

            Forecast forecast = objectMapper.readValue(jsonParser, Forecast.class);

            assertThat(forecast.getDate()).isEqualTo("18.06.2022");
            assertThat(forecast.getIsRaining()).isEqualTo(true);
            assertThat(forecast.getTemperature()).isEqualTo(23);
            assertThat(forecast.getWind().get(1).getWindSpeed()).isEqualTo(4);
            assertThat(forecast.getWind().get(0).getWindDirection()).isEqualTo("NW");

        }
    }
}

