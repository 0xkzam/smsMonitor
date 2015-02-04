
package com.kuz.tmp.old.model;


import com.kuz.tmp.old.controller.FileUtil;
import com.kuz.tmp.old.view.MessageDialogBox;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Kasun Amarasena
 */

@Deprecated
public final class DBConnection{

    private Connection con;    
    private String absPath;
    private Properties properties;
    private static final DBConnection db = new DBConnection();
    private final Logger logger = Logger.getLogger(DBConnection.class); 

    DBConnection() {
        
        properties = new Properties();
        String uri = System.getProperty("user.dir") + "\\data\\derby-connection.properties";
        
        try (FileInputStream in = new FileInputStream(uri)) {
            properties.load(in);
        } catch (IOException ex) {
            logger.fatal("Could not read derby-connection properties", ex);
        }          
    }

    /**
     *
     * @return Database Connection instance, initial run will create an embedded
     * database.
     */
    public static Connection getInstance() {
        return db.getConnection();
    }

    private Connection getConnection() {
        this.loadDriver();

        try {
            con = DriverManager.getConnection("jdbc:derby:" + absPath + "\\smsDB;", properties);
        } catch (SQLException ex) {
            logger.info("Creating new database");
            if (!FileUtil.isFound("smsDB",absPath)) {
                con = createNewDatabase();
            } else {
                logger.error("Error getting connection:",ex);
                MessageDialogBox.showErrorMessage("Error occured while connecting the database!", "ERROR!");
                System.exit(0);
            }
        }
        return con;
    }

    /**
     * Load database driver
     */
    private void loadDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            logger.error("Error loading driver", ex); //logger   
            MessageDialogBox.showErrorMessage("Error occured while loading the database driver!", "ERROR!");
            System.exit(0);
        }
    }
/**
 * Create new database
 * 
 * @return Connection to the newly created database 
 */
    private Connection createNewDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:" + absPath + "/smsDB;create=true;", properties);
            Query.createSMSDB(connection);
        } catch (SQLException ex) {
            logger.error("Error getting connection", ex); //logger
            MessageDialogBox.showErrorMessage("Error occured while creating new database!", "ERROR!");
//            try {
//                FileUtil.deleteFile(new File(absPath+""));
//            } catch (IllegalAccessException ex1) {
//                Logger.printError(this.getClass().getName(), "createNewDatabase", "Could not delete folder" + ex); //logger
//            }
            System.exit(0);
        }
        return connection;
    }

}
