
package com.kuz.tmp.control.db;

import com.kuz.tmp.model.db.DerbyMessageDAO;
import com.kuz.tmp.model.db.MessageDAO;
import com.kuz.tmp.model.bean.Message;
import java.util.Date;
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

    private MessageDAO instance = new DerbyMessageDAO();
    private List<Date> stamps = new ArrayList<>();

    public DerbyQueryTest() {
    }

    @Test
    public void testInsert_Message() {
        System.out.println("insert a message");
        java.util.Date date = new java.util.Date();        
        stamps.add(date);
        Message message = new Message("+941111111", "testing insert(Message m)", date, date);
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

        boolean success = instance.insert(messages);

        assertTrue(success);

    }

    @Test
    public void testDelete_Timestamp() {
        System.out.println("delete message");
        Date stamp = stamps.get(0);
        assertTrue(instance.delete(stamp));
        stamps.remove(0);
    }

    @Test
    public void testDelete_List() {
        System.out.println("delete list of messages");
        boolean success = instance.delete(stamps);
        assertTrue(success);
    }

    @Test
    public void testSelectFromRange_Date_Date() {
        System.out.println("selectFrom  Date Range");
//        Date startDate = null;
//        Date endDate = null;
//        DerbyMessageDAO instance = new DerbyMessageDAO();
//        List<Message> expResult = null;
//        List<Message> result = instance.getMessagesFromRange(startDate, endDate);
//        assertEquals(expResult, result);

    }

    @Test
    public void testSelectFromRange_int_int() {
        System.out.println("selectFrom Row Range");
//        int startRow = 0;
//        int endRow = 0;
//        DerbyMessageDAO instance = new DerbyMessageDAO();
//        List<Message> expResult = null;
//        List<Message> result = instance.getMessagesFromRange(startRow, endRow);

    }

}
