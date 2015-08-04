package com.guillecanizal.common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class MyLogger {
    public static final int FILE_SIZE = 1048576;
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static FileHandler fileTxt;
    private static SimpleFormatter formatterTxt;

    public static void setup() {

        logger.setLevel(Level.ALL);
        try {
            fileTxt = new FileHandler("log/test-etl.log", FILE_SIZE, 5, true);
            formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}

