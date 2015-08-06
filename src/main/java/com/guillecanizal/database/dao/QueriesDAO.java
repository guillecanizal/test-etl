package com.guillecanizal.database.dao;


/**
 * Created by guillecanizal on 06/08/15.
 */
public abstract class QueriesDAO {

    //list all flights leaving a particular airport, sorted by time
    public abstract String listFlightsByAirportSortedByTime(int originAirportId, int rows);

    //Give me the carrier, origin and destination airport based on 10 minute buckets of air_time.
    public abstract String listAirTimeBuckets(int limit, int rows);
}
