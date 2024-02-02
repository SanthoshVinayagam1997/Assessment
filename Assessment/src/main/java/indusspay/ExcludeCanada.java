package indusspay;

import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExcludeCanada {
    public static void main(String[] args) {
        String csvFilePath = "Assessment\\output\\SortCSVByUnitPrice.csv";
        String outputFilePath = "Assessment\\output\\FinalOutPut.csv";

        try (CSVParser csvParser = CSVParser.parse(new FileReader(csvFilePath), CSVFormat.DEFAULT.withHeader())) {
            List<CSVRecord> filteredRecords = StreamSupport.stream(csvParser.spliterator(), false)
                    .filter(record -> !"Canada".equals(record.get("SalesRegion")))
                    .collect(Collectors.toList());

            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputFilePath), CSVFormat.DEFAULT
                    .withHeader(csvParser.getHeaderNames().toArray(new String[0])))) {
                csvPrinter.printRecords(filteredRecords);
            }

            System.out.println("Filtered data (excluding Canada) saved to 'output.csv'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
