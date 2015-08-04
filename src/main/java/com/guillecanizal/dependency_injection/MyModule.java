package com.guillecanizal.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by guillecanizal on 04/08/15.
 */
public class MyModule extends AbstractModule {

    private static final String PROPERTIES = "/test-etl.properties";

    private Properties loadProperties(String fileUri) {
        Properties properties = new Properties();
        try {
            InputStream file = this.getClass().getResourceAsStream(fileUri);
            properties.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    protected void configure() {

        Properties propsWS = this.loadProperties(PROPERTIES);
        Names.bindProperties(binder(), propsWS);


    }
}
