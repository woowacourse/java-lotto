package lotto.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private String dbUrl;
    private String username;
    private String password;

    public PropertiesManager() {
        Properties prop = new Properties();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties");
            prop.load(in);
            dbUrl = prop.getProperty("url") + prop.getProperty("database");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
