package com.guillecanizal.etl;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public abstract class Extract {

    protected String filePath;

    @Inject
    public Extract(@Named("csv-path") final String filePath) {
        this.filePath = filePath;
    }

    public abstract List<String[]> execute();

}
