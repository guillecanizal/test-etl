package test.guillermo.database.dao;

import test.guillermo.database.DBManager;
import test.guillermo.etl.model.CSVModel;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public abstract class LoadDAO {

    protected DBManager dbManager;

    public void setDBManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public abstract boolean loadFlightsInDB(List<CSVModel> models);

}
