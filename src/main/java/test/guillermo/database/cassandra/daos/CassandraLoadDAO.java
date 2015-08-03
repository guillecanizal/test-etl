package test.guillermo.database.cassandra.daos;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import test.guillermo.database.cassandra.CassandraDBManager;
import test.guillermo.database.cassandra.models.CassandraFlight;
import test.guillermo.database.dao.LoadDAO;
import test.guillermo.etl.model.CSVModel;
import test.guillermo.etl.model.Flight;

import java.util.Iterator;
import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class CassandraLoadDAO extends LoadDAO {


    private void saveIndexQueryOne(Session session, CassandraFlight f) {
        PreparedStatement statement = session.prepare(
                "INSERT INTO " + ((CassandraDBManager) this.dbManager).getDbName()+ ".queryone (FL_NUM,ORIGIN_AIRPORT_ID,DEP_TIME) VALUES (?,?,?)"

        );
        BoundStatement bs = statement.bind();
        bs.setInt("FL_NUM", f.getFlNum());
        bs.setInt("ORIGIN_AIRPORT_ID", f.getOriginAirportId());
        bs.setDate("DEP_TIME", f.getDepTime());
        session.executeAsync(bs);
    }

    private void saveIndexQueryTwo(Session session, CassandraFlight f) {
        PreparedStatement statement = session.prepare(
                "INSERT INTO " + ((CassandraDBManager) this.dbManager).getDbName()+ ".querytwo (AIR_TIME, CARRIER, ORIGIN, DEST) VALUES (?,?,?,?)"

        );
        BoundStatement bs = statement.bind();
        bs.setDate("AIR_TIME", f.getAirTime());
        bs.setString("CARRIER", f.getCarrier());
        bs.setString("ORIGIN", f.getOrigin());
        bs.setString("DEST", f.getDest());
        session.executeAsync(bs);
    }

    @Override
    public boolean loadFlightsInDB(List<CSVModel> models) {
        System.out.println("Load into Cassandra");
        boolean response = true;
        try {

            Mapper mapper = ((CassandraDBManager)this.dbManager).getManager().mapper(CassandraFlight.class);
            //BatchStatement batch = new BatchStatement();

            Iterator<CSVModel> flightsIterator = models.iterator();
            while (flightsIterator.hasNext()) {
                Flight f = (Flight) flightsIterator.next();
                CassandraFlight cassandraFlight = this.transformFlight(f);
                //System.out.println(f.getId());
                mapper.saveAsync(cassandraFlight);
                saveIndexQueryOne(((CassandraDBManager)this.dbManager).getSession(), cassandraFlight);
                saveIndexQueryTwo(((CassandraDBManager)this.dbManager).getSession(), cassandraFlight);

            }
        } catch (Exception e) {
            e.printStackTrace();
            response = false;
        }

        System.out.println("End Load into Cassandra");
        return response;
    }

    public CassandraFlight transformFlight(Flight f) {
        CassandraFlight cF = new CassandraFlight();
        cF.setActualElapsedTime(f.getActualElapsedTime());
        cF.setAirlineId(f.getAirlineId());
        cF.setAirTime(f.getAirTime());
        cF.setArrTime(f.getFlDate());
        cF.setCarrier(f.getCarrier());
        cF.setDayOfMonth(f.getDayOfMonth());
        cF.setDepTime(f.getDepTime());
        cF.setDest(f.getDest());
        cF.setDestCityName(f.getDestCityName());
        cF.setDestStateAbr(f.getDestStateAbr());
        cF.setDistance(f.getDistance());
        cF.setFlDate(f.getFlDate());
        cF.setFlNum(f.getFlNum());
        cF.setId(f.getId());
        cF.setOrigin(f.getOrigin());
        cF.setOriginAirportId(f.getOriginAirportId());
        cF.setOriginCityName(f.getOriginCityName());
        cF.setOriginStateAbr(f.getOriginStateAbr());
        cF.setYear(f.getYear());
        return cF;
    }
}
