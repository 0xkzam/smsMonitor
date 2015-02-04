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

import com.kuz.tmp.model.Message;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class DerbyQueryTest {
    
    private Query instance = new DerbyQuery();
    private List<Timestamp> stamps = new ArrayList<>();
    
    public DerbyQueryTest() {
    }
    
    @Test
    public void testInsert_Message() {
        System.out.println("insert a message");
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        stamps.add(timestamp);
        Message message = new Message("+941111111", "testing insert(Message m)", date, timestamp);       
        assertTrue(instance.insert(message));        
    }

    @Test
    public void testInsert_List() throws InterruptedException {
        Thread.sleep(2000);
        
        System.out.println("insert list of messages");
        List<Message> messages = new ArrayList<>();
        java.util.Date date1 = new java.util.Date();
        Timestamp timestamp1 = new Timestamp(date1.getTime());
        Message message1 = new Message("+942222222", "testing insert(List of messages)", date1, timestamp1);   
        messages.add(message1);
        stamps.add(timestamp1);
        
        Thread.sleep(2000);
        
        java.util.Date date2 = new java.util.Date();
        Timestamp timestamp2 = new Timestamp(date2.getTime());
        Message message2 = new Message("+943333333", "testing insert(List of messages)", date2, timestamp2);   
        messages.add(message2);
        stamps.add(timestamp2);
        
        int[] insert = instance.insert(messages);
        for (int i : insert) {
            assertTrue(i >= 0);
        }        
    }

    @Test
    public void testDelete_Timestamp() {
        System.out.println("delete message");
        Timestamp stamp = stamps.get(0);       
        assertTrue(instance.delete(stamp));
        stamps.remove(0);        
    }

    @Test
    public void testDelete_List() {
        System.out.println("delete list of messages");       
        int[] delete = instance.delete(stamps);
        for (int i : delete) {
            assertTrue(i >= 0);
        } 
    }

    @Test
    public void testSelectFromRange_Date_Date() {
        System.out.println("selectFrom  Date Range");
//        Date startDate = null;
//        Date endDate = null;
//        DerbyQuery instance = new DerbyQuery();
//        List<Message> expResult = null;
//        List<Message> result = instance.selectFromRange(startDate, endDate);
//        assertEquals(expResult, result);
        
    }

    @Test
    public void testSelectFromRange_int_int() {
        System.out.println("selectFrom Row Range");
//        int startRow = 0;
//        int endRow = 0;
//        DerbyQuery instance = new DerbyQuery();
//        List<Message> expResult = null;
//        List<Message> result = instance.selectFromRange(startRow, endRow);
       
    }
    
}
