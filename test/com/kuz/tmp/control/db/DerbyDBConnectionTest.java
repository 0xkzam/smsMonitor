/*
 * Copyright (C) 2015 Kasun Amarasena
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

package com.kuz.tmp.control.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;

import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class DerbyDBConnectionTest {
    private final Logger logger = Logger.getLogger(DerbyDBConnectionTest.class);
    private DBConnection dbCon = new DerbyDBConnection();
    private Connection con  =  dbCon.getConnection();
    
    public DerbyDBConnectionTest() {
    }
 
    @After
    public void after(){
        dbCon.close();
    }

    @Test
    public void testLoadProperties() {
                
        try {
            Method method = dbCon.getClass().getDeclaredMethod("loadProperties");            
            Assert.assertNotNull(dbCon);
            
            method.setAccessible(true);

            Properties properties = (Properties)method.invoke(dbCon);
            Assert.assertNotNull(properties);
            
            Assert.assertFalse("3545".equals(properties.getProperty("user")));
            Assert.assertEquals("kasun", properties.getProperty("user"));  
            
        } catch (NoSuchMethodException | SecurityException ex) {
            logger.fatal("", ex);
            Assert.fail();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            logger.fatal("", ex);
            Assert.fail();
        }      
    }
    
    @Test
    public void testGetConnection(){
        
        System.out.println(""+System.getProperty("user.dir"));
        DatabaseMetaData metaData = null;
        try {
            metaData = con.getMetaData();
        } catch (SQLException ex) {
            Assert.fail("database access error"+ ex);
        }
        try {
            System.out.println("DB product name: "+metaData.getDatabaseProductName());            
            System.out.println("DB product version: "+metaData.getDatabaseProductVersion());
            System.out.println("DB product driver name: "+metaData.getDriverName());
            System.out.println("DB product driver version: "+metaData.getDriverVersion());
            System.out.println("Batch update support: "+metaData.supportsBatchUpdates());            
            
        } catch (SQLException ex) {
            Assert.fail("database access error"+ ex);
        }
    }
    
}
