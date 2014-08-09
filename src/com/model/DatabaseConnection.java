/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.control.helper.Logger;
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
    private static DatabaseConnection db = new DatabaseConnection();

    private DatabaseConnection() {
    }

    /**
     *
     * @return Connection instance
     */
    public static Connection getInstance() {
        return db.getConnection();
    }    
 

    private Connection getConnection() {
        this.loadDriver();
        
        String absPath = Paths.get("").toAbsolutePath().toString();        
                
        Properties properties = new Properties();
        properties.setProperty("user", "kasun");
        properties.setProperty("password","matrix");     
        
        try {
            con = DriverManager.getConnection("jdbc:derby:"+absPath+"/smsDB;create=true;",properties);            
        } catch (SQLException ex) {
            Logger.printError(this.getClass().getName(),"getConnection","Error getting connection" + ex); //logger
            MessageDialogBox.showErrorMessage("Error occured while connecting the database!","ERROR!");
            System.exit(0);
        }
        return con;
    }

    private void loadDriver() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            Logger.printError(this.getClass().getName(),"loadDriver","Error loading driver" + ex); //logger   
            MessageDialogBox.showErrorMessage("Error occured while loading the database driver!","ERROR!");
            System.exit(0);
        }
    }
   
}
