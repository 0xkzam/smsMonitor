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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class TestModel {
    
    private static Logger logger;

    public TestModel() {
    }
    
    @Test
    public void test() {    
        
        
        
        Date utilDate = new Date();
        System.out.println("util date:"+utilDate);
        
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("sql date:"+sqlDate);
        
        java.sql.Time sqlTime = new Time(utilDate.getTime());
        System.out.println("sql time:"+sqlTime);
        
        System.out.println("sql date converted to util:"+new Date(sqlDate.getTime()));
        System.out.println("sql time converted to util:"+new Date(sqlTime.getTime()));
        
    }
}
