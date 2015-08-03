package test.guillermo.etl;

import com.google.inject.Inject;
import test.guillermo.etl.model.CSVModel;

import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class ETLImpl implements ETL {
    protected Extract extract;
    protected Transform transform;
    protected Load load;

    @Inject
    public ETLImpl(Extract extract, Transform transform, Load load) {
        this.extract = extract;
        this.transform = transform;
        this.load = load;
    }

    @Override
    public void execute() {
        //Extract data from the CSV file
        List<String[]> lines = extract.execute();
        List<CSVModel> modelLines = transform.execute(lines);
        load.execute(modelLines);
    }
}
