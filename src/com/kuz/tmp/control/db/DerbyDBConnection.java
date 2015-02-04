
package com.kuz.tmp.control.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Connect to a Derby Database
 *
 * @author Kasun Amarasena
 */
public class DerbyDBConnection implements DBConnection {

    private Connection connection;
    private final Logger logger = Logger.getLogger(DerbyDBConnection.class);    
    
    @Override
    public Connection getConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            logger.error("Driver Error", ex);
            return null;
        }
        
        Properties properties = loadProperties();
        
        try {
            connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);
        } catch (SQLException ex) {
            logger.error("Database Connection Error", ex);
            return null;
        }
        
        return connection;
    }
    
    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.warn("Could not close connection", ex);
            }
        }
    }

    /**
     * Read RDBMS properties-Database url,user & password
     *
     * @return Properties
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        String uri = System.getProperty("user.dir") + "\\data\\derby-connection.properties";
        
        try (FileInputStream in = new FileInputStream(uri)) {
            properties.load(in);
        } catch (IOException ex) {
            logger.fatal("Could not read derby-connection properties", ex);
        }        
        return properties;
    }
    
}
