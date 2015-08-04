package com.guillecanizal.etl;

import com.google.inject.Inject;
import com.guillecanizal.common.MyLogger;
import com.guillecanizal.database.DBManager;
import com.guillecanizal.database.dao.LoadDAO;
import com.guillecanizal.etl.model.Flight;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class LoadFlights extends Load<Flight> {

    @Inject
    public LoadFlights(DBManager dbManager, LoadDAO loadDAO) {
        super(dbManager, loadDAO);
    }

    @Override
    public boolean execute(List<Flight> models) {
        MyLogger.logger.entering("LoadFlights", "execute");
        dbManager.openConnection();
        MyLogger.logger.info("DB Connection opened");
        boolean response = loadDAO.loadFlightsInDB(models);
        dbManager.closeConnection();
        MyLogger.logger.info("DB Connection closed");
        MyLogger.logger.exiting("LoadFlights", "execute");
        return response;
    }
}
