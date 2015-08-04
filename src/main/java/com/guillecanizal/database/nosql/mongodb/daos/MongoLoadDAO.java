package com.guillecanizal.database.nosql.mongodb.daos;

import com.guillecanizal.common.MyLogger;
import com.guillecanizal.database.dao.LoadDAO;
import com.guillecanizal.database.nosql.mongodb.MongoDBManager;
import com.guillecanizal.database.nosql.mongodb.models.MongoFlight;
import com.guillecanizal.etl.model.Flight;

import java.util.Iterator;
import java.util.List;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class MongoLoadDAO extends LoadDAO {

    @Override
    public boolean loadFlightsInDB(List<Flight> models) {
        MyLogger.logger.entering("MongoLoadDAO", "loadFlightsInDB");
        boolean response = true;
        try {

            //BatchStatement batch = new BatchStatement();

            Iterator<Flight> flightsIterator = models.iterator();
            MongoFlight mongoFlight;
            while (flightsIterator.hasNext()) {
                Flight f = (Flight) flightsIterator.next();
                mongoFlight = this.transformFlight(f);
                ((MongoDBManager) this.dbManager).getDs().save(mongoFlight);

            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLogger.logger.throwing("MongoLoadDAO", "loadFlightsInDB", e);
            response = false;
        }

        MyLogger.logger.exiting("MongoLoadDAO", "loadFlightsInDB");
        return response;
    }

    public MongoFlight transformFlight(Flight f) {
        //TODO Repasar tema de fechas
        MongoFlight mf = new MongoFlight();
        mf.set_id(f.getId() + "");
        // mf.setActualElapsedTime(new Timestamp(f.getActualElapsedTime().getTime()));
        mf.setAirlineId(f.getAirlineId());
        //mf.setAirTime(new Timestamp(f.getAirTime().getTime()));
        //mf.setFlDate( new Timestamp(f.getFlDate().getTime()));
        mf.setCarrier(f.getCarrier());
        mf.setDayOfMonth(f.getDayOfMonth());
        //mf.setDepTime(new Timestamp(f.getDepTime().getTime()));
        mf.setDest(f.getDest());
        mf.setDestCityName(f.getDestCityName());
        mf.setDestStateAbr(f.getDestStateAbr());
        mf.setDistance(f.getDistance());
        //mf.setFlDate(new Timestamp(f.getFlDate().getTime()));
        mf.setFlNum(f.getFlNum());

        mf.setOrigin(f.getOrigin());
        mf.setOriginAirportId(f.getOriginAirportId());
        mf.setOriginCityName(f.getOriginCityName());
        mf.setOriginStateAbr(f.getOriginStateAbr());
        mf.setYear(f.getYear());
        return mf;
    }
}
