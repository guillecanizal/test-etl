package com.guillecanizal.dependency_injection;

import com.guillecanizal.database.DBManager;
import com.guillecanizal.database.dao.LoadDAO;
import com.guillecanizal.database.nosql.cassandra.CassandraDBManager;
import com.guillecanizal.database.nosql.cassandra.daos.CassandraLoadDAO;
import com.guillecanizal.etl.*;

/**
 * Created by guillecanizal on 10/07/15.
 */
public class ModuleCassandra extends MyModule {


    @Override
    protected void configure() {
        super.configure();
        bind(DBManager.class).to(CassandraDBManager.class);
        bind(LoadDAO.class).to(CassandraLoadDAO.class);

        bind(Extract.class).to(ExtractCSV.class);
        bind(Transform.class).to(TransformFlights.class);
        bind(Load.class).to(LoadFlights.class);

        bind(ETL.class).to(ETLImpl.class);
    }


}
