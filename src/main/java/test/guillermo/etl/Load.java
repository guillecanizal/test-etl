package test.guillermo.etl;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.common.util.concurrent.ListenableFuture;
import test.guillermo.mapper.Flight;

import java.util.Iterator;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Load
{

    public boolean loadInCassandra(List<Flight> flights)
    {
        System.out.println("Load into Cassandra");
        boolean response = true;
        try {
            Cluster cluster = Cluster.builder().addContactPoint("localhost")
                    .build();
            Session session = cluster.connect();
            MappingManager manager = new MappingManager(session);
            Mapper mapper = manager.mapper(Flight.class);
            BatchStatement batch = new BatchStatement();

            Iterator<Flight> flightsIterator = flights.iterator();
            int pending = 0;
            while (flightsIterator.hasNext())
            {
                Flight f = flightsIterator.next();
                //System.out.println(f.getId());
                mapper.saveAsync(f);
                saveIndexQueryOne(session, f);
                saveIndexQueryTwo(session, f);

            }
            session.close();
            cluster.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            response=false;
        }

        System.out.println("End Load into Cassandra");
        return response;
    }
    private void saveIndexQueryOne(Session session, Flight f)
    {
        PreparedStatement statement = session.prepare(
                "INSERT INTO testguillermo.queryone (FL_NUM,ORIGIN_AIRPORT_ID,DEP_TIME) VALUES (?,?,?)"

        );
        BoundStatement bs  = statement.bind();
        bs.setInt("FL_NUM", f.getFlNum());
        bs.setInt("ORIGIN_AIRPORT_ID", f.getOriginAirportId());
        bs.setDate("DEP_TIME",f.getDepTime());
        session.executeAsync(bs);
    }

    private void saveIndexQueryTwo(Session session, Flight f)
    {
        PreparedStatement statement = session.prepare(
                "INSERT INTO testguillermo.querytwo (AIR_TIME, CARRIER, ORIGIN, DEST) VALUES (?,?,?,?)"

        );
        BoundStatement bs  = statement.bind();
        bs.setDate("AIR_TIME",f.getAirTime());
        bs.setString("CARRIER",f.getCarrier());
        bs.setString("ORIGIN",f.getOrigin());
        bs.setString("DEST",f.getDest());
        session.executeAsync(bs);
    }
}
