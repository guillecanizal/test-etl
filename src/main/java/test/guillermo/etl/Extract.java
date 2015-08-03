package test.guillermo.etl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillecanizal on 22/06/15.
 */
public class Extract
{

   public List<String[]> extractDataFromCSV()
   {

       System.out.println("Extract CSV");
       List<String[]> csvLines = new ArrayList<String[]>();
       String csvFile = "flights_from_pg.csv";
       BufferedReader br = null;
       String line = "";
       String cvsSplitBy = ",";

       try {

           br = new BufferedReader(new FileReader(csvFile));
           while ((line = br.readLine()) != null) {

               // use comma as separator
               String[] flight = line.split(cvsSplitBy);
              // System.out.println(flight[0]);
               csvLines.add(flight);

           }

       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           if (br != null) {
               try {
                   br.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
       System.out.println("End Extract CSV");
       return csvLines;
   }
    public static void main(String args[])
    {
        Extract e = new Extract();
        e.extractDataFromCSV();
    }

}
