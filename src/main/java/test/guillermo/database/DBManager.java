package test.guillermo.database;

/**
 * Created by guillecanizal on 03/08/15.
 */

import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class DBManager {

    protected  String host;
    protected  String dbName;

    @Inject
    public DBManager(@Named("db-host") final String host, @Named("db-name") final String dbName) {
        this.host=host;
        this.dbName=dbName;

        }

    public abstract void openConnection();

    public   abstract void closeConnection();

    public String getHost() {
        return host;
    }

    public String getDbName() {
        return dbName;
    }
}
