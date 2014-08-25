/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.control.helper.Logger;
import com.viewer.Helper;
import com.viewer.MessageDialogBox;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Kasun Amarasena
 */
public final class DatabaseConnection {

    private Connection con;
    private static final DatabaseConnection db = new DatabaseConnection();
    private String absPath;
    private Properties properties;

    public DatabaseConnection() {

        absPath = Paths.get("").toAbsolutePath().toString();
        properties = new Properties();
        properties.setProperty("user", "kasun");
        properties.setProperty("password", "matrix");
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
            con = DriverManager.getConnection("jdbc:derby:" + absPath + "/smsDB;", properties);
        } catch (SQLException ex) {
            if (!Helper.isFound("smsDB")) {
                con = createNewDatabase();
            } else {
                Logger.printError(this.getClass().getName(), "getConnection", "Error getting connection" + ex); //logger
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
            Logger.printError(this.getClass().getName(), "loadDriver", "Error loading driver" + ex); //logger   
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
            Query.createTable(connection);
            Query.createTable2(connection);
            Query.addColumn(connection);
            Query.createTrigger(connection);
        } catch (SQLException ex) {
            Logger.printError(this.getClass().getName(), "createNewDatabase", "Error getting connection" + ex); //logger
            MessageDialogBox.showErrorMessage("Error occured while creating new database!", "ERROR!");
            System.exit(0);
        }
        return connection;
    }

    /**
     * Close Database Connection
     * @throws SQLException
     */
    public static void close() throws SQLException {
        if (db != null) {
            db.con.close();
        }
    }

}
