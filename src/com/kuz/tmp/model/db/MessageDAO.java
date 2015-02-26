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

    //PHONENO VARCHAR(12),CONTENTS VARCHR(220), SENT_TIMESTAMP TIMESTAMP, RECEIVED_TIMESTAMP TIMESTAMP
    String INSERT_MESSAGE = "insert into MESSAGE values (?, ?, ?, ?)";
    String GET_MESSAGES = "select * from MESSAGE order by SENT_TIMESTAMP desc";
    String GET_MIN_DATE = "select min(SENT_TIMESTAMP) from MESSAGE";
    String GET_MAX_DATE = "select max(SENT_TIMESTAMP) from MESSAGE";
    String GET_ROW_COUNT = "select count(PHONENO) from MESSAGE";
    String GET_MESSAGES_BY_DATE_RANGE = "select * from MESSAGES where SENT_TIMESTAMP >= ? and SENT_TIMESTAMP <= ? order by SENT_TIMESTAMP desc";
    String DELETE_MESSAGE = "delete from MESSAGE where RECEIVED_TIMESTAMP = ?";

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
