package com.guillecanizal.etl;

import com.google.inject.Inject;
import com.guillecanizal.database.DBManager;
import com.guillecanizal.database.dao.LoadDAO;

import java.util.List;

/**
 * Hello world!
 */
public abstract class Load<T> {
    protected LoadDAO loadDAO;
    protected DBManager dbManager;

    @Inject
    public Load(DBManager dbManager, LoadDAO loadDAO) {
        this.loadDAO = loadDAO;
        this.dbManager = dbManager;
        this.loadDAO.setDBManager(dbManager);
    }

    public abstract boolean execute(List<T> models);


}
