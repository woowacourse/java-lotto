package lotto.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private String dbURL;
    private Properties properties;
    private String driverClassName;

    public static PropertiesUtil getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static PropertiesUtil getTestIntance() {
        return LazyHolder.INSTANCE_TEST;
    }

    private PropertiesUtil(String path) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
            properties = new Properties();
            properties.load(in);
            dbURL = properties.getProperty("url");
            driverClassName = properties.getProperty("driver-class-name");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDbURL() {
        return dbURL;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    private static class LazyHolder {
        private static final PropertiesUtil INSTANCE = new PropertiesUtil("jdbc.properties");
        private static final PropertiesUtil INSTANCE_TEST = new PropertiesUtil("jdbcTest.properties");
    }
}
