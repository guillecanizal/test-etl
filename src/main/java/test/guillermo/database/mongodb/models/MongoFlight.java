package test.guillermo.database.mongodb.models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;


import java.sql.Timestamp;
/**
 * Created by guillecanizal on 03/08/15.
 */
@Entity(value = "flights", noClassnameStored = true)
public class MongoFlight {

    @Id
    private String _id;
    private int year;
    @Property("day_of_month")
    private int dayOfMonth;
    @Property("fl_Timestamp")
    private Timestamp flTimestamp;
    @Property("airline_id")
    private int airlineId;
    private String carrier;
    @Property("fl_num")
    private int flNum;
    @Property("origin_airport_id")
    private int originAirportId;
    private String origin;
    @Property("origin_city_name")
    private String originCityName;
    @Property("origin_state_abr")
    private String originStateAbr;
    private String dest;
    @Property("dest_city_name")
    private String destCityName;
    @Property("dest_state_abr")
    private String destStateAbr;
    @Property("dep_time")
    private Timestamp depTime;
    @Property("arr_time")
    private Timestamp arrTime;
    @Property("actual_elapsed_time")
    private Timestamp actualElapsedTime;
    @Property("air_time")
    private Timestamp airTime;
    private int distance;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Timestamp getFlTimestamp() {
        return flTimestamp;
    }

    public void setFlTimestamp(Timestamp flTimestamp) {
        this.flTimestamp = flTimestamp;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getFlNum() {
        return flNum;
    }

    public void setFlNum(int flNum) {
        this.flNum = flNum;
    }

    public int getOriginAirportId() {
        return originAirportId;
    }

    public void setOriginAirportId(int originAirportId) {
        this.originAirportId = originAirportId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginCityName() {
        return originCityName;
    }

    public void setOriginCityName(String originCityName) {
        this.originCityName = originCityName;
    }

    public String getOriginStateAbr() {
        return originStateAbr;
    }

    public void setOriginStateAbr(String originStateAbr) {
        this.originStateAbr = originStateAbr;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getDestCityName() {
        return destCityName;
    }

    public void setDestCityName(String destCityName) {
        this.destCityName = destCityName;
    }

    public String getDestStateAbr() {
        return destStateAbr;
    }

    public void setDestStateAbr(String destStateAbr) {
        this.destStateAbr = destStateAbr;
    }

    public Timestamp getDepTime() {
        return depTime;
    }

    public void setDepTime(Timestamp depTime) {
        this.depTime = depTime;
    }

    public Timestamp getArrTime() {
        return arrTime;
    }

    public void setArrTime(Timestamp arrTime) {
        this.arrTime = arrTime;
    }

    public Timestamp getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(Timestamp actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public Timestamp getAirTime() {
        return airTime;
    }

    public void setAirTime(Timestamp airTime) {
        this.airTime = airTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
