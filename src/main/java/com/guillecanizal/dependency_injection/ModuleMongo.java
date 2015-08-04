package com.guillecanizal.dependency_injection;

import com.guillecanizal.database.DBManager;
import com.guillecanizal.database.dao.LoadDAO;
import com.guillecanizal.database.nosql.mongodb.MongoDBManager;
import com.guillecanizal.database.nosql.mongodb.daos.MongoLoadDAO;
import com.guillecanizal.etl.*;

/**
 * Created by guillecanizal on 10/07/15.
 */
public class ModuleMongo extends MyModule {


    @Override
    protected void configure() {
        super.configure();
        bind(DBManager.class).to(MongoDBManager.class);
        bind(LoadDAO.class).to(MongoLoadDAO.class);

        bind(Extract.class).to(ExtractCSV.class);
        bind(Transform.class).to(TransformFlights.class);
        bind(Load.class).to(LoadFlights.class);

        bind(ETL.class).to(ETLImpl.class);
    }


}
