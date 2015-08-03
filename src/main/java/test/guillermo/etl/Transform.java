package test.guillermo.etl;

import com.google.inject.Inject;
import test.guillermo.common.MyLogger;
import test.guillermo.etl.model.CSVModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guillecanizal on 22/06/15.
 */
public abstract class Transform {


    @Inject
    public Transform() {

    }

    public List<CSVModel> execute(List<String[]> csvLines) {
        List<CSVModel> modelLines = new ArrayList();
        MyLogger.logger.info("Transform CSV Lines");
        Iterator csvLinesIt = csvLines.iterator();
        while (csvLinesIt.hasNext()) {
            modelLines.add(this.transformCSVLineToModel((String[]) csvLinesIt.next()));
        }
        MyLogger.logger.info("End Transform CSV Lines");
        return modelLines;
    }

    protected abstract CSVModel transformCSVLineToModel(String[] csvLine);


}
