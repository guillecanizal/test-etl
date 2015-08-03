package test.guillermo.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import test.guillermo.database.cassandra.daos.CassandraLoadDAO;
import test.guillermo.database.dao.LoadDAO;
import test.guillermo.etl.*;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by guillecanizal on 10/07/15.
 */
public class Module extends AbstractModule {

    private static final String PROPERTIES = "/test-etl.properties";

    @Override
    protected void configure() {

        Properties propsWS = this.loadProperties(PROPERTIES);

        Names.bindProperties(binder(), propsWS);


        bind(LoadDAO.class).to(CassandraLoadDAO.class);

        bind(Extract.class).to(ExtractCSV.class);
        bind(Transform.class).to(TransformFlights.class);
        bind(Load.class).to(LoadFlights.class);

        bind(ETL.class).to(ETLImpl.class);

    }

    private Properties loadProperties(String fileUri) {
        Properties properties = new Properties();
        try {
            InputStream file = this.getClass().getResourceAsStream(fileUri);
            properties.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
