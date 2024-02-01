Assessment Task: Dataset Modification 

This Java application performs various tasks on a dataset containing product details. 
The program is organized into four components, each addressing specific requirements:

   1. Remove duplicate records and update empty cells to null
         * Reads a CSV file containing product details
         * Replaces empty cells with the string "null"
         * Writes the modified dataset to a new CSV file
    
   2. Group the dataset by category
      * Groups the dataset by category
      * Writes the grouped records to a new CSV file
      
   4. Sort the records by unit price
        *  Sorts the dataset records by unit price
        *  Writes the sorted data to a new CSV file
     
   5. Get records excluding the Sales Region as Canada
      * Excludes records with Sales Region as "Canada" from the dataset.
      * Writes the filtered data to a new CSV file.

Prerequisites

    Java Development Kit (JDK) installed on your machine.
    Apache Commons CSV library JAR file.
