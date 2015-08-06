package com.guillecanizal.webapp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.guillecanizal.common.MyLogger;
import com.guillecanizal.database.nosql.cassandra.daos.SolrQueriesDAO;
import com.guillecanizal.dependency_injection.ModuleCassandra;
import com.guillecanizal.dependency_injection.ModuleMongo;
import com.guillecanizal.etl.ETL;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static spark.Spark.get;
import static spark.Spark.setPort;

/**
 * Created by guillecanizal on 13/07/15.
 */
public class WebApp {
    private static int PORT = 8082;
    private static String TEMPLATES_FOLDER = "/freemarker";
    private final Configuration cfg;
    private Injector injector;

    public WebApp() throws IOException {

        this.cfg = createFreemarkerConfiguration();
        setPort(PORT);
        initializeRoutes();
    }

    public static void main(String[] args) throws IOException {
        MyLogger.setup();
        WebApp wbData = new WebApp();
    }

    private Configuration createFreemarkerConfiguration() {
        Configuration retVal = new Configuration();
        retVal.setClassForTemplateLoading(WebApp.class, TEMPLATES_FOLDER);
        return retVal;
    }

    private void initializeRoutes() throws IOException {

        get(new FreemarkerBasedRoute("/load-data-mongodb", "load-data.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                injector = Guice.createInjector(new ModuleMongo());
                ETL etl = injector.getInstance(ETL.class);
                etl.execute();
                SimpleHash root = new SimpleHash();
                root.put("numElements", etl.getNumElements());
                root.put("duration", etl.getDuration());
                template.process(root, writer);
            }
        });
        get(new FreemarkerBasedRoute("/load-data-cassandra", "load-data.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {

                injector = Guice.createInjector(new ModuleCassandra());
                ETL etl = injector.getInstance(ETL.class);
                etl.execute();
                SimpleHash root = new SimpleHash();
                root.put("numElements", etl.getNumElements());
                root.put("duration", etl.getDuration());
                template.process(root, writer);
            }
        });
        get(new Route("/solr-query-flights") {
            @Override
            public Object handle(Request request, Response response) {
                SolrQueriesDAO solrQueryDAO = new SolrQueriesDAO();
                int numFlight = Integer.parseInt(request.queryParams("originAirportId"));
                int rows = Integer.parseInt(request.queryParams("rows"));
                String json = solrQueryDAO.listFlightsByAirportSortedByTime(numFlight, rows);
                response.type("application/json");
                return json;
            }


        });
        get(new Route("/solr-query-buckets") {
            @Override
            public Object handle(Request request, Response response) {
                SolrQueriesDAO solrQueryDAO = new SolrQueriesDAO();
                int limit = Integer.parseInt(request.queryParams("limit"));
                int rows = Integer.parseInt(request.queryParams("rows"));
                String json = solrQueryDAO.listAirTimeBuckets(limit, rows);
                response.type("application/json");
                return json;
            }


        });

        get(new FreemarkerBasedRoute("/", "index.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash();
                template.process(root, writer);
            }
        });

    }

    abstract class FreemarkerBasedRoute extends Route {
        final Template template;

        /**
         * Constructor
         *
         * @param path The route path which is used for matching. (e.g. /hello, users/:name)
         */
        protected FreemarkerBasedRoute(final String path, final String templateName) throws IOException {
            super(path);
            template = cfg.getTemplate(templateName);
        }

        @Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {

                MyLogger.logger.throwing("FreemarkerBasedRoute", "handle", e);
                response.redirect("/internal_error");
            }
            return writer;
        }

        protected abstract void doHandle(final Request request, final Response response, final Writer writer)
                throws IOException, TemplateException;

    }

}
