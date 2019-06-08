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

    private PropertiesUtil() {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(in);
            dbURL = String.format("jdbc:mysql://%s/%s", properties.getProperty("server"), properties.getProperty("database"));
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
        private static final PropertiesUtil INSTANCE = new PropertiesUtil();
    }
}
