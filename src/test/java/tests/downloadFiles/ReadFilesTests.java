package tests.downloadFiles;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadFilesTests {

    ClassLoader classLoader = ReadFilesTests.class.getClassLoader();
    private static final int BUFFER_SIZE = 4096;
    String outDirectory = "src/test/resources/output/";

    @Test
    void readFilesTests() throws IOException {
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
}


