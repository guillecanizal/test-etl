package com.guillecanizal.database.dao;

import com.guillecanizal.database.DBManager;
import com.guillecanizal.models.Flight;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public abstract class LoadDAO {

    protected DBManager dbManager;

    public void setDBManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public abstract boolean loadFlightsInDB(List<Flight> models);

}
