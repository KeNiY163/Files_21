package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ZipTest {

    private static final String zip = "TestFiles.zip";

    private static final ClassLoader cl = ZipTest.class.getClassLoader();
    @Test
    @DisplayName("csv")
    void csvTest() throws Exception {
        try (InputStream zipStream = cl.getResourceAsStream(zip);
             ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(zipStream))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String csvFile = "TestCSV.csv";
                if (zipEntry.getName().equals(csvFile)) {
                    Reader reader = new InputStreamReader(zipInputStream);
                    CSVReader csvReader = new CSVReader(reader);

                    List<String[]> content = csvReader.readAll();
                    assertEquals(1, content.size());
                    String csv_text = "Test CSV files for homework";
                    assertEquals(csv_text, content.get(0));

                }
            }
        }
    }

    @Test
    @DisplayName("xlsx")
    void xlsxTest() throws Exception {
        try (InputStream zipStream = cl.getResourceAsStream(zip);
             ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(zipStream))) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String xlsFile = "TestXLSX.xlsx";
                if (zipEntry.getName().equals(xlsFile)) {
                    XLS xls = new XLS(zipInputStream);
                    String xlsx_text = "Test XLSX files for homework";
                    assertEquals(xlsx_text, xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue());
                }
            }
        }
    }

    @Test
    @DisplayName("pdf")
    void pdfTest() throws Exception {
        try (InputStream zipStream = cl.getResourceAsStream(zip);
             ZipInputStream zipInputStream = new ZipInputStream(Objects.requireNonNull(zipStream))) {

            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String pdfFile = "TestPDF.pdf";
                if (zipEntry.getName().equals(pdfFile)) {
                    PDF pdf = new PDF(zipInputStream);
                    String pdf_text = "Test PDF files for homework";
                    Assertions.assertTrue(pdf.text.contains(pdf_text));
                }
            }
        }
    }

}
