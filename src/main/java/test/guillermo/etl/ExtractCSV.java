package test.guillermo.etl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import test.guillermo.common.MyLogger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillecanizal on 22/06/15.
 */
public class ExtractCSV extends Extract {
    @Inject
    public ExtractCSV(@Named("csv-path") String filePath) {
        super(filePath);
    }

    public List<String[]> execute() {

        MyLogger.logger.info("Extract CSV");
        List<String[]> csvLines = new ArrayList<String[]>();
        String csvFile = this.filePath;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] arrayLine = line.split(cvsSplitBy);
                csvLines.add(arrayLine);
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
        MyLogger.logger.info("End Extract CSV");
        return csvLines;
    }


}
