package test.guillermo.etl;

import com.google.inject.Inject;
import test.guillermo.database.dao.LoadDAO;
import test.guillermo.etl.model.CSVModel;

import java.util.List;

/**
 * Hello world!
 */
public abstract class Load {
    protected LoadDAO loadDAO;

    @Inject
    public Load(LoadDAO loadDAO) {
        this.loadDAO = loadDAO;
    }

    public abstract boolean execute(List<CSVModel> models);


}
