package test.guillermo.common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class MyLogger {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
    public static final int FILE_SIZE = 1048576;
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static public void setup() {

        // Get the global logger to configure it
        logger.setLevel(Level.INFO);
        try {
            fileTxt = new FileHandler("test-etl.log", FILE_SIZE, 5, true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        // Create txt Formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

    }
}

