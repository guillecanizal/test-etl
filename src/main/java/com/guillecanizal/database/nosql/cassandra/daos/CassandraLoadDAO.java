package com.guillecanizal.database.nosql.cassandra.daos;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.guillecanizal.common.MyLogger;
import com.guillecanizal.database.dao.LoadDAO;
import com.guillecanizal.database.nosql.cassandra.CassandraDBManager;
import com.guillecanizal.database.nosql.cassandra.models.CassandraFlight;
import com.guillecanizal.etl.model.Flight;

import java.util.Iterator;
import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class CassandraLoadDAO extends LoadDAO {


    private void saveIndexQueryOne(Session session, CassandraFlight f) {
        PreparedStatement statement = session.prepare(
                "INSERT INTO " + this.dbManager.getDbName() + ".queryone (FL_NUM,ORIGIN_AIRPORT_ID,DEP_TIME) VALUES (?,?,?)"

        );
        BoundStatement bs = statement.bind();
        bs.setInt("FL_NUM", f.getFlNum());
        bs.setInt("ORIGIN_AIRPORT_ID", f.getOriginAirportId());
        bs.setDate("DEP_TIME", f.getDepTime());
        session.executeAsync(bs);
    }

    private void saveIndexQueryTwo(Session session, CassandraFlight f) {
        PreparedStatement statement = session.prepare(
                "INSERT INTO " + this.dbManager.getDbName() + ".querytwo (AIR_TIME, CARRIER, ORIGIN, DEST) VALUES (?,?,?,?)"

        );
        BoundStatement bs = statement.bind();
        bs.setDate("AIR_TIME", f.getAirTime());
        bs.setString("CARRIER", f.getCarrier());
        bs.setString("ORIGIN", f.getOrigin());
        bs.setString("DEST", f.getDest());
        session.executeAsync(bs);
    }

    @Override
    public boolean loadFlightsInDB(List<Flight> models) {
        MyLogger.logger.entering("CassandraLoadDAO", "loadFlightsInDB");
        boolean response = true;
        try {

            Mapper mapper = ((CassandraDBManager) this.dbManager).getManager().mapper(CassandraFlight.class);
            //BatchStatement batch = new BatchStatement();

            Iterator<Flight> flightsIterator = models.iterator();
            while (flightsIterator.hasNext()) {
                Flight f = (Flight) flightsIterator.next();
                CassandraFlight cassandraFlight = this.transformFlight(f);
                mapper.saveAsync(cassandraFlight);
                saveIndexQueryOne(((CassandraDBManager) this.dbManager).getSession(), cassandraFlight);
                saveIndexQueryTwo(((CassandraDBManager) this.dbManager).getSession(), cassandraFlight);

            }
        } catch (Exception e) {

            MyLogger.logger.throwing("CassandraLoadDAO", "loadFlightsInDB", e);
            response = false;
        }


        MyLogger.logger.exiting("CassandraLoadDAO", "loadFlightsInDB");
        return response;
    }

    public CassandraFlight transformFlight(Flight f) {
        CassandraFlight cF = new CassandraFlight();
        cF.setActualElapsedTime(f.getActualElapsedTime());
        cF.setAirlineId(f.getAirlineId());
        cF.setAirTime(f.getAirTime());
        cF.setFlDate(f.getFlDate());
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
