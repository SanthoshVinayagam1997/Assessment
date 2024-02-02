package indusspay;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UpdateEmptyIntoNull {

    public static void main(String[] args) {

        String inputCsvFile = "Assessment\\src\\main\\resources\\Retail.csv";
        String outputCsvFile = "Assessment\\output\\UpdateEmptyIntoNull.csv";

        try {
            convertCsvToNull(inputCsvFile, outputCsvFile);
            System.out.println("CSV file processed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertCsvToNull(String inputCsvFile, String outputCsvFile) throws IOException {
        List<String[]> records = readCsv(inputCsvFile);
        List<String[]> processedRecords = updateEmptyCells(records);
        writeCsv(outputCsvFile, processedRecords);
    }

    private static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
                CSVParser csvParser = CSVFormat.DEFAULT.parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                List<String> rowData = new ArrayList<>();
                for (String value : csvRecord) {
                    rowData.add(value);
                }
                records.add(rowData.toArray(new String[0]));
            }
        }

        return records;
    }

    private static List<String[]> updateEmptyCells(List<String[]> records) {
        List<String[]> processedRecords = new ArrayList<>();

        for (String[] record : records) {
            for (int i = 0; i < record.length; i++) {
                if (record[i].isEmpty()) {
                    record[i] = "null";
                }
            }
            processedRecords.add(record);
        }

        return processedRecords;
    }

    private static void writeCsv(String filePath, List<String[]> records) throws IOException {
        try (Writer writer = new FileWriter(filePath);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            for (String[] record : records) {
                csvPrinter.printRecord((Object[]) record);
            }

            csvPrinter.flush();
        }
    }
}
