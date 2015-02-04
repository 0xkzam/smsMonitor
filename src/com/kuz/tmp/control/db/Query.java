
package com.kuz.tmp.control.db;

import com.kuz.tmp.model.Message;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Querying Interface
 *
 * @author Kasun Amarasena
 */
public interface Query {
    /**
     * Insert Message object
     * @param message Message 
     * @return true if successful, false otherwise
     */
    boolean insert(Message message);
    
    /**
     * Insert a List of Message objects
     * @param messages List<Message> 
     * @return int array - refer to <code>java.sql.Statement.executeBatch</code>
     */
    int[] insert(List<Message> messages);
    
    /**
     * Delete message from the database
     * @param stamp Timestamp
     * @return true if successful, false otherwise
     */
    boolean delete(Timestamp stamp);
    
    /**
     * Delete list of messages from the database
     * @param stamps List<Timestamp>
     * @return int array - refer to <code>java.sql.Statement.executeBatch</code>
     */
    int[] delete(List<Timestamp> stamps);
    
    /**
     * 
     * @param startDate
     * @param endDate
     * @return List of Messages in the specified range
     */
    List<Message> selectFromRange(Date startDate, Date endDate);
    
    /**
     * 
     * @param startRow int
     * @param endRow int
     * @return List of Messages in the specified range
     */
    List<Message> selectFromRange(int startRow, int endRow);
    
    
}
