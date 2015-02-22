package com.kuz.tmp.model.db;

import com.kuz.tmp.model.bean.Message;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * DB Modeling Interface/Message DAO
 *
 * @author Kasun Amarasena
 */
public interface MessageDAO {

    /**
     * Insert Message object
     *
     * @param message Message
     * @return true if successful, false otherwise
     */
    boolean insert(Message message);

    /**
     * Insert a List of Message objects
     *
     * @param messages List<Message>
     * @return int array - refer to <code>java.sql.Statement.executeBatch</code>
     */
    int[] insert(List<Message> messages);

    /**
     * Delete message from the database
     *
     * @param stamp Timestamp
     * @return true if successful, false otherwise
     */
    boolean delete(Timestamp stamp);

    /**
     * Delete list of messages from the database
     *
     * @param stamps List<Timestamp>
     * @return int array - refer to <code>java.sql.Statement.executeBatch</code>
     */
    int[] delete(List<Timestamp> stamps);

    /**
     *
     * @param startDate
     * @param endDate
     * @return List of Messages in the specified date range
     */
    List<Message> getMessagesFromRange(Date startDate, Date endDate);

    /**
     *
     * @param startRow int
     * @param endRow int
     * @return List of Messages in the specified row range
     */
    List<Message> getMessagesFromRange(int startRow, int endRow);

    /**
     *
     * @return Date of the earliest Message received
     */
    Date getMinDate();

    /**
     *
     * @return Date of the latest Messages received
     */
    Date getMaxDate();

}
