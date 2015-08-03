package test.guillermo.etl;

import test.guillermo.mapper.Flight;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guillecanizal on 22/06/15.
 */
public class Transform
{

    public List<Flight> transformCSVToFlights(List<String[]> csvFlights)
    {

        System.out.println("Transform CSV Lines");
        List<Flight> flights= null;
        Iterator flightsIt = csvFlights.iterator();
        while(flightsIt.hasNext())
        {
            if(flights==null)
            {
                flights= new ArrayList<Flight>();
            }
            flights.add(this.transformCSVLineToFlight((String[])flightsIt.next()));
        }
        System.out.println("End Transform CSV Lines");
        return flights;
    }

    private Flight transformCSVLineToFlight(String[] csvLine)
    {
        //3,2012,1,2012/11/11,19805,AA,1,12478,JFK,New York, NY,LAX,Los Angeles, CA,855,1142,347,330,2475
        Flight flight = new Flight();
        flight.setId(Integer.parseInt(csvLine[0]));
        flight.setYear(Integer.parseInt(csvLine[1]));
        flight.setDayOfMonth(Integer.parseInt(csvLine[2]));
        flight.setFlDate(this.transformStringToDate("yyyy/MM/dd", csvLine[3]));
        flight.setAirlineId(Integer.parseInt(csvLine[4]));
        flight.setCarrier(csvLine[5]);
        flight.setFlNum(Integer.parseInt(csvLine[6]));
        flight.setOriginAirportId(Integer.parseInt(csvLine[7]));
        flight.setOrigin(csvLine[8]);
        flight.setOriginCityName(csvLine[9]);
        flight.setOriginStateAbr(csvLine[10]);
        flight.setDest(csvLine[11]);
        flight.setDestCityName(csvLine[12]);
        flight.setDestStateAbr(csvLine[13]);
        flight.setDepTime(this.transformStringToDate("HHmm", this.transformStringHour(csvLine[14])));
        flight.setArrTime(this.transformStringToDate("HHmm", this.transformStringHour(csvLine[15])));
        flight.setActualElapsedTime(this.transformStringToDate("HHmm", this.transformStringHour(csvLine[16])));
        flight.setAirTime(this.transformStringToDate("HHmm", this.transformStringHour(csvLine[17])));
        flight.setDistance(Integer.parseInt(csvLine[18]));
        return flight;

    }

    private  Date transformStringToDate(String mask,String dateString){
        java.sql.Date sqlDate;
        try {
            java.util.Date utilDate = new SimpleDateFormat(mask).parse(dateString);
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        catch (ParseException e)
        {
            sqlDate=null;

        }
        return sqlDate;
    }
    private String transformStringHour(String hour)
    {
        String hourTransformed = hour;
        if(hour.length()==2)
        {
            hourTransformed = "00"+hour;
        }
        else if(hour.length()==3)
        {
            hourTransformed = "0"+hour;
        }
        return hourTransformed;
    }
}
