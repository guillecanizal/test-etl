package test.guillermo.database.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import test.guillermo.database.DBManager;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class CassandraDBManager  extends DBManager{

    private Cluster cluster;
    private Session session;
    private MappingManager manager;

    @Inject
    public CassandraDBManager(@Named("db-host") String host, @Named("db-name") String dbName) {
        super(host, dbName);
    }

    @Override
    public void openConnection() {
        this.cluster = Cluster.builder().addContactPoint(this.host)
                .build();
        this.session = cluster.connect();
        this.manager = new MappingManager(session);
    }

    @Override
    public void closeConnection() {
        cluster.close();
        session.close();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public Session getSession() {
        return session;
    }

    public MappingManager getManager() {
        return manager;
    }
}
