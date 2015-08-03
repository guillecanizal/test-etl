package test.guillermo.database.mongodb;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import test.guillermo.database.DBManager;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class MongoDBManager extends DBManager {

    private Datastore ds;
    private MongoClient mongoClient;

    @Inject
    public MongoDBManager(@Named("db-host") String host, @Named("db-name") String dbName) {
        super(host, dbName);
    }


    @Override
    public void openConnection() {
        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://"+this.host));
        this.ds = new Morphia().createDatastore(this.mongoClient, this.dbName);
    }

    @Override
    public void closeConnection() {
        this.mongoClient.close();
    }

    public Datastore getDs() {
        return ds;
    }
}
