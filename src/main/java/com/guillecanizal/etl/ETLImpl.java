package com.guillecanizal.etl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.guillecanizal.common.MyLogger;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
@Singleton
public class ETLImpl<T> extends ETL {


    @Inject
    public ETLImpl(Extract extract, Transform transform, Load load) {
        super(extract, transform, load);
    }

    @Override
    public void execute() {

        MyLogger.logger.entering("ETLImpl", "execute");
        long initTime = System.currentTimeMillis();
        List<String[]> lines = extract.execute();
        List<T> modelLines = transform.execute(lines);
        load.execute(modelLines);
        this.numElements = modelLines.size();
        long finalTime = System.currentTimeMillis();
        this.duration = (finalTime - initTime);
        MyLogger.logger.exiting("ETLImpl", "execute");
    }
}
