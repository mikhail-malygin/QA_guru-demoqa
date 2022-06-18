package tests.downloadFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ReadArchiveFilesTests {

    ClassLoader classLoader = ReadArchiveFilesTests.class.getClassLoader();
    private static final int BUFFER_SIZE = 4096;
    String outDirectory = "src/test/resources/output/";

    @Test
    void readFilesTests() throws IOException, CsvException {
        File destinationDirectory = new File(outDirectory);
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
        }

        try (InputStream inputStream = classLoader.getResourceAsStream("archiveWithPdfXlsxCsv.zip")) {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String filePath = destinationDirectory + File.separator + zipEntry.getName();
                extractFile(zipInputStream, filePath);

                switch (FilenameUtils.getExtension(filePath)) {
                    case "csv":
                        checkCsvFile();
                        break;
                    case "xlsx":
                        checkXlsxFile();
                        break;
                    case "pdf":
                        checkPdfFile();
                        break;
                }
            }
        }
    }

    void extractFile(ZipInputStream zipInputStream, String filePath) throws IOException {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read;
            while ((read = zipInputStream.read(bytesIn)) > 0) {
                bufferedOutputStream.write(bytesIn, 0, read);
            }
        }
    }

    void checkCsvFile() throws IOException, CsvException {
        try (InputStream inputStreamCsv = classLoader.getResourceAsStream("output/csv_sample.csv")){
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStreamCsv, UTF_8));
            List<String[]> csv = csvReader.readAll();

            assertThat(csv).contains(
                    new String[] {"processor","intel","20000", "10.06.2022"},
                    new String[] {"processor","amd","18000", "17.05.2022"}
            );
        }
    }

    void checkXlsxFile() throws IOException {
        try (InputStream inputStreamXlsx = FileUtils.openInputStream(new File("src/test/resources/output/xlsx_sample.xlsx"))) {
            File targetXlsxFile = new File("output/targetXlsxFile.xlsx");
            FileUtils.copyInputStreamToFile(inputStreamXlsx, targetXlsxFile);
            XLS xlsx = new XLS(targetXlsxFile);

            assertThat(
                    xlsx.excel.getSheetAt(0)
                            .getRow(0)
                            .getCell(0)
                            .getStringCellValue()
            ).contains("Прайс-лист сайта https://rdstroy.ru/");
        }
    }

    void checkPdfFile() throws IOException {
        try (InputStream inputStreamPdf = FileUtils.openInputStream(new File("src/test/resources/output/pdf_sample.pdf"))) {
            File targetPdfFile = new File("output/targetPdfFile.xlsx");
            FileUtils.copyInputStreamToFile(inputStreamPdf, targetPdfFile);
            PDF pdf = new PDF(targetPdfFile);

            assertThat(pdf.text).contains("Система управления\r\nтестированием Test IT");
        }
    }
}


