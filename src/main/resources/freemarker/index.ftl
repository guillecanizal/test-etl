<!DOCTYPE html>

<html>
  <head>
    <title>Index</title>
    <style type="text/css">
    </style>
  </head>
  <body>
    <h1>Index</h1>
    <h2>Options:</h2>
    <ul>
        <li>Cassandra
          <ul>
            <li><a href="/load-data-cassandra">ETL</a></li>
            <li>Solr Queries:

                   <p>List all flights leaving a particular airport, sorted by time</p>
                   <form action="/solr-query-flights" method="get">
                     Flight Number: <br>
                     <input type="number" name="numFlight" value="12954"><br>
                     <input type="number" name="rows" value="10"><br>
                     <input type="submit" value="Submit">
                   </form>
                   <br>
                   <p>Give me the carrier, origin and destination airport based on 10 minute buckets of air_time</p>
                   <form action="/solr-query-buckets" method="get">
                     Limit: <br>
                     <input type="number" name="limit"><br>
                     <input type="number" name="rows" value="10"><br>
                     <input type="submit" value="Submit">
                   </form>
                   <br>
            </li>
          </ul>
        </li>
        <li><a href="/load-data-mongodb">ETL MongoDB</a></li>
    </ul>
  </body>

</html>
