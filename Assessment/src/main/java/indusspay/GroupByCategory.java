package indusspay;

import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GroupByCategory {

    public static void main(String[] args) throws IOException {
        String csvFilePath = "Assessment\\output\\UpdateEmptyIntoNull.csv";
        String outputFilePath = "Assessment\\output\\GroupByCategory.csv";

        try (CSVParser csvParser = CSVParser.parse(new FileReader(csvFilePath), CSVFormat.DEFAULT.withHeader())) {
            Map<String, List<CSVRecord>> groupedByCategory = StreamSupport.stream(csvParser.spliterator(), false)
                    .collect(Collectors.groupingBy(record -> record.get("Category")));

            // Write the grouped records to a new CSV file
            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(outputFilePath), CSVFormat.DEFAULT
                    .withHeader(csvParser.getHeaderNames().toArray(new String[0])))) {
                groupedByCategory.forEach((category, records) -> {
                    try {
                        for (CSVRecord record : records) {
                            csvPrinter.printRecord(record);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }

            System.out.println("Grouped data saved to 'grouped_output.csv'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
