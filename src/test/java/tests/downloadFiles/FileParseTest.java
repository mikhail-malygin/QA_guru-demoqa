package tests.downloadFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import tests.downloadFiles.domain.Teacher;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class FileParseTest {

    // сlassLoader, которым наш файл(класс) будет загружен
    ClassLoader classLoader = FileParseTest.class.getClassLoader(); // сlassLoader, которым наш класс будет загружен

    @Test
    void pdfTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File file = $("a[href*='junit-user-guide-5.8.2.pdf']").download();
        PDF pdf = new PDF(file);

        assertThat(pdf.author).isEqualTo("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein");
    }

    @Test
    void xlsTest() throws Exception {
        open("http://romashka2008.ru/price");
        File file = $(".site-content__right a[href*='/f/prajs_ot_0806.xls']").download();
        XLS xls = new XLS(file);

        assertThat(
                xls.excel.getSheetAt(0)
                         .getRow(22)
                         .getCell(2)
                         .getStringCellValue()
        ).contains("БОЛЬШАЯ РАСПРОДАЖА");
    }

    @Test
    void csvTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("sample.csv")){
            // преобразование из байтового стрима в посимвольный ридер
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream, UTF_8));
            // читает посимвольно файл типа reader
            List<String[]> csv = csvReader.readAll();

            assertThat(csv).contains(
                    new String[] {"teacher","lesson","date"},
                    new String[] {"Tuchs","junit","03.06"},
                    new String[] {"Eroshenko","allure","07.06"}
            );
        }
    }

    @Test
    void zipTest() throws IOException {
        try (InputStream inputStream = classLoader.getResourceAsStream("archive.zip")) {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            ZipEntry entry; // entry - файл, который засунут в архив
            // Перебираем все файлы в архиве
            while ((entry = zipInputStream.getNextEntry()) != null) {
                assertThat(entry.getName()).isEqualTo("test_picture_qa_guru.png");
            }
        }
    }

    @Test
    void jsonTest() throws IOException {
        try (InputStream inputStream = classLoader.getResourceAsStream("teacher.json")) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);

            assertThat(jsonObject.get("name").getAsString()).isEqualTo("Dmitrii");
            assertThat(jsonObject.get("age").getAsInt()).isEqualTo(33);
            assertThat(jsonObject.get("isGoodTeacher").getAsBoolean()).isEqualTo(true);
            assertThat(jsonObject.get("passport").getAsJsonObject().get("number").getAsInt()).isEqualTo(1234);
            assertThat(jsonObject.get("passport").getAsJsonObject().get("dateIssue").getAsString()).isEqualTo("22.07.2006");
        }
    }

    @Test
    void jsonTestNG() throws IOException {
        try (InputStream inputStream = classLoader.getResourceAsStream("teacher.json")) {
            Gson gson = new Gson();
            Teacher jsonObject = gson.fromJson(new InputStreamReader(inputStream), Teacher.class);

            assertThat(jsonObject.getName()).isEqualTo("Dmitrii");
            assertThat(jsonObject.getAge()).isEqualTo(33);
            assertThat(jsonObject.isGoodTeacher()).isEqualTo(true);
            assertThat(jsonObject.getPassport().getNumber()).isEqualTo(1234);
            assertThat(jsonObject.getPassport().getDateIssue()).isEqualTo("22.07.2006");
        }
    }

}
