package test.guillermo.database.dao;

import test.guillermo.etl.model.CSVModel;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public interface LoadDAO {

    boolean loadFlightsInDB(List<CSVModel> models);

}
