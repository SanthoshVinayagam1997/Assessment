package indusspay;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class SortCSVByUnitPrice {
    public static void main(String[] args) {
        String csvFilePath = "output\\GroupByCategory.csv";
        String outputFilePath = "output\\SortCSVByUnitPrice.csv";

        try (CSVParser csvParser = CSVParser.parse(new FileReader(csvFilePath), CSVFormat.DEFAULT.withHeader())) {
            List<CSVRecord> records = StreamSupport.stream(csvParser.spliterator(), false)
                    .collect(Collectors.toList());

            // Sort records by UnitPrice
            Collections.sort(records, Comparator.comparing(record -> Double.parseDouble(record.get("UnitPrice"))));

            CSVFormat format = CSVFormat.DEFAULT.withHeader(csvParser.getHeaderNames().toArray(new String[0]));
            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputFilePath), format)) {
                csvPrinter.printRecords(records);
            }

            System.out.println("Sorted data saved to 'sorted_output.csv'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing UnitPrice column as double. Check if all values are numeric.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        }
    }
}
