package test.guillermo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import test.guillermo.dependency_injection.Module;
import test.guillermo.etl.ETL;

/**
 * Created by guillecanizal on 03/08/15.
 */
public class App {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new Module());
        ETL etl = injector.getInstance(ETL.class);
        etl.execute();

    }
}
