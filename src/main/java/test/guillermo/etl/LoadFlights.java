package test.guillermo.etl;

import com.google.inject.Inject;
import test.guillermo.database.DBManager;
import test.guillermo.database.dao.LoadDAO;
import test.guillermo.etl.model.CSVModel;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class LoadFlights extends Load {

    @Inject
    public LoadFlights(DBManager dbManager, LoadDAO loadDAO) {
        super(dbManager, loadDAO);
    }

    @Override
    public boolean execute(List<CSVModel> models) {

        dbManager.openConnection();
        boolean response= loadDAO.loadFlightsInDB(models);
        dbManager.closeConnection();
        return response;
    }
}
