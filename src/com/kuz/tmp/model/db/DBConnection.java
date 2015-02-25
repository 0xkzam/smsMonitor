
package com.kuz.tmp.model.db;

import java.sql.Connection;

/**
 * Database Connection interface
 * 
 * @author Kasun Amarasena
 */
public interface DBConnection {
    
    /**
     * 
     * @return SQL Connection instance of a specified rdbms 
     */
    Connection getConnection();
    
    /**
     * Close the Connection
     */
    void close();
}
