package com.guillecanizal.etl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.guillecanizal.common.MyLogger;

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

    //TODO Maybe is better to use https://commons.apache.org/proper/commons-csv/
    public List<String[]> execute() {

        MyLogger.logger.entering("ExtractCSV", "execute");
        List<String[]> csvLines = new ArrayList<String[]>();
        String csvFile = this.filePath;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            MyLogger.logger.info("Load file");
            br = new BufferedReader(new FileReader(csvFile));
            MyLogger.logger.info("Parsing starting");
            while ((line = br.readLine()) != null) {
                String[] arrayLine = line.split(cvsSplitBy);
                csvLines.add(arrayLine);
            }
            MyLogger.logger.info("CSV Parsed");

        } catch (Exception e) {
            MyLogger.logger.info(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    MyLogger.logger.throwing("ExtractCSV", "execute", e);
                }
            }
        }
        MyLogger.logger.exiting("ExtractCSV", "execute");
        return csvLines;
    }


}
