package com.guillecanizal.database.nosql.cassandra.daos;

import com.guillecanizal.common.MyLogger;
import com.guillecanizal.database.dao.QueriesDAO;
import com.guillecanizal.models.Flight;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

/**
 * Created by guillecanizal on 06/08/15.
 */
public class SolrQueriesDAO extends QueriesDAO {

    private static final String QUERY_LIST_FLIGHTS_BY_AIRPORT_SORTED_BY_TIME = "http://localhost:8983/solr/testetl.flights/select?q=origin_airport_id%3A#ORIGIN_AIRPORT_ID#&start=1&rows=#ROWS#&sort=dep_time+asc&rows=100&fl=fl_num%2Cdep_time&wt=json&indent=true";
    private static final String QUERY_LIST_AIR_TIME_BUCKETS = "http://localhost:8983/solr/testetl.flights/select?q=*%3A*&start=1&rows=#ROWS#&wt=json&indent=true&indent=true%20&group=true%20&fl=air_time%2Ccarrier%2Corigin%2Cdest%20&group.func=rint(div(ms(air_time),mul(10,mul(60,1000))))%20&group.limit=#LIMIT#&sort=air_time%20asc";

    public String listFlightsByAirportSortedByTime(int originAirportId, int rows) {

        String json = null;
        List<Flight> flights = null;
        HttpResponse<JsonNode> response = null;
        try {
            String query = QUERY_LIST_FLIGHTS_BY_AIRPORT_SORTED_BY_TIME.replaceAll("#ORIGIN_AIRPORT_ID#", Integer.toString(originAirportId));
            query = query.replaceAll("#ROWS#", Integer.toString(rows));
            response = Unirest.get(query)
                    .asJson();
            json = response.getBody().toString();
            MyLogger.logger.info("Solr listFlightsByAirportSortedByTime: " + json);
        } catch (UnirestException e) {
            MyLogger.logger.throwing("SolrQueriesDAO", "listFlightsByAirportSortedByTime", e);
            json = null;
        } finally {
            if (null != json) {
               /* GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                SolrResponse solrResponse = gson.fromJson(json, SolrResponse.class);
                flights=solrResponse.getResponse().getDocs();*/
            }
        }
        return json;
    }

    @Override
    public String listAirTimeBuckets(int limit, int rows) {
        String json = null;
        List<Flight> flights = null;
        HttpResponse<JsonNode> response = null;
        try {
            String query = QUERY_LIST_AIR_TIME_BUCKETS.replaceAll("#LIMIT#", Integer.toString(limit));
            query = query.replaceAll("#ROWS#", Integer.toString(rows));
            response = Unirest.get(query)
                    .asJson();
            json = response.getBody().toString();
            MyLogger.logger.info("Solr listAirTimeBuckets: " + json);
        } catch (UnirestException e) {
            MyLogger.logger.throwing("SolrQueriesDAO", "listAirTimeBuckets", e);
            json = null;
        }
        return json;
    }

}
