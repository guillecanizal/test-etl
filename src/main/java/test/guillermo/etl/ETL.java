package test.guillermo.etl;

import test.guillermo.mapper.Flight;

import java.util.List;

/**
 * Created by guillecanizal on 22/06/15.
 */
public class ETL
{
    public static void main( String[] args )
    {
        //Extract data from the CSV file
        Extract e = new Extract();
        List<String[]> lines = e.extractDataFromCSV();

        //Transform data according to the data model provided
        Transform t = new Transform();
        List<Flight> flights = t.transformCSVToFlights(lines);

        //Load data into Cassandra
        Load l = new Load();
        l.loadInCassandra(flights);



    }
}
