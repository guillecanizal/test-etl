package test.guillermo.etl;

import com.google.inject.Inject;
import test.guillermo.database.DBManager;
import test.guillermo.database.dao.LoadDAO;
import test.guillermo.etl.model.CSVModel;

import java.util.List;

/**
 * Hello world!
 */
public abstract class Load {
    protected LoadDAO loadDAO;
    protected DBManager dbManager;

    @Inject
    public Load(DBManager dbManager,LoadDAO loadDAO) {
        this.loadDAO = loadDAO;
        this.dbManager=dbManager;
        this.loadDAO.setDBManager(dbManager);
    }

    public abstract boolean execute(List<CSVModel> models);


}
