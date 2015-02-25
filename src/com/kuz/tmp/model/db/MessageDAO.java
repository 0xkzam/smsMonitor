package com.kuz.tmp.model.db;

import com.kuz.tmp.model.bean.Message;
import java.util.Date;
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
     * @return true if successful, false otherwise
     */
    boolean insert(List<Message> messages);

    /**
     * Delete message from the database
     *
     * @param date
     * @return true if successful, false otherwise
     */
    boolean delete(Date date);

    /**
     * Delete list of messages from the database
     *
     * @param listOfDates List<Date>
     * @return true if successful, false otherwise
     */
    boolean delete(List<Date> listOfDates);

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
