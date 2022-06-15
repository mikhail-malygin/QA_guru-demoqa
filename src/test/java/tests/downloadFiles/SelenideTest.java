package tests.downloadFiles;

import org.junit.jupiter.api.Test;

import java.io.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideTest {
/*
    Для случаев когда нет ссылка на скачивание файл. Из-за прокси тесты не стабильные.
    Это используется в редких случаях. Лучше использовать когда это действительно нужно
    static {
       Configuration.fileDownload = FileDownloadMode.PROXY;
    }

*/
    @Test
    void downloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File file = $("#raw-url").download();
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] fileContent = inputStream.readAllBytes();
            String asString = new String(fileContent, UTF_8);
            assertThat(asString).contains("sjd");
        }
    }

    @Test
    void uploadTest() {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type=file]").uploadFromClasspath("img/test_picture_qa_guru.png");
        $("#file-submit").click();

        $("h3").shouldHave(text("File Uploaded!"));
        $("#uploaded-files").shouldHave(text("test_picture_qa_guru.png"));
    }
}
