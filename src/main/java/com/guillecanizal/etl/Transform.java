package com.guillecanizal.etl;

import com.google.inject.Inject;
import com.guillecanizal.common.MyLogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guillecanizal on 22/06/15.
 * I am using generic types just in case I decide to use other CSV formats
 */
public abstract class Transform<T> {


    @Inject
    public Transform() {

    }

    public List<T> execute(List<String[]> csvLines) {
        MyLogger.logger.entering("Transform", "execute");
        List<T> modelLines = new ArrayList();
        MyLogger.logger.info("Transform CSV Lines");
        Iterator csvLinesIt = csvLines.iterator();
        while (csvLinesIt.hasNext()) {
            modelLines.add(this.transformCSVLineToModel((String[]) csvLinesIt.next()));
        }
        MyLogger.logger.exiting("Transform", "execute");
        return modelLines;
    }

    protected abstract T transformCSVLineToModel(String[] csvLine);


}
