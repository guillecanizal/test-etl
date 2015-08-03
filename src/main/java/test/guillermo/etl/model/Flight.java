package test.guillermo.etl.model;

import java.sql.Date;

/**
 * Created by guillecanizal on 22/06/15.
 */
public class Flight implements CSVModel {

    private int id;
    private int year;
    private int dayOfMonth;
    private Date flDate;
    private int airlineId;
    private String carrier;
    private int flNum;
    private int originAirportId;
    private String origin;
    private String originCityName;
    private String originStateAbr;
    private String dest;
    private String destCityName;
    private String destStateAbr;
    private Date depTime;
    private Date arrTime;
    private Date actualElapsedTime;
    private Date airTime;
    private int distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getFlDate() {
        return flDate;
    }

    public void setFlDate(Date flDate) {
        this.flDate = flDate;
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

    public Date getDepTime() {
        return depTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }

    public Date getActualElapsedTime() {
        return actualElapsedTime;
    }

    public void setActualElapsedTime(Date actualElapsedTime) {
        this.actualElapsedTime = actualElapsedTime;
    }

    public Date getAirTime() {
        return airTime;
    }

    public void setAirTime(Date airTime) {
        this.airTime = airTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


}
