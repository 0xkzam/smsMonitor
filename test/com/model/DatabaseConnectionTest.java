/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class DatabaseConnectionTest {

    public DatabaseConnectionTest() {
    }

    /**
     * Test of getInstance method, of class DatabaseConnection.
     */
    @Test
    public void testGetInstance() {
        Connection con = DatabaseConnection.getInstance();
        DatabaseMetaData metaData = null;
        try {
            metaData = con.getMetaData();
        } catch (SQLException ex) {
            Assert.assertFalse("database access error or closed connection", true);
        }
        try {
            System.out.println("DB product name: "+metaData.getDatabaseProductName());            
            System.out.println("DB product version: "+metaData.getDatabaseProductVersion());
            System.out.println("DB product driver name: "+metaData.getDriverName());
            System.out.println("DB product driver version: "+metaData.getDriverVersion());
            System.out.println("Batch update support: "+metaData.supportsBatchUpdates());            
            
        } catch (SQLException ex) {
            Assert.assertFalse("database access error", true);
        }
        
        
    }

}
