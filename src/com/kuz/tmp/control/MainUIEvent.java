package com.kuz.tmp.control;

import com.kuz.tmp.model.Message;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Observable;

/**
 * Contains all Event data of MainUI
 *
 * @author Kasun Amarasena
 */
public class MainUIEvent extends Observable {

    private List<Message> currentMessages;
    private List<Timestamp> selected;
    private Date dateFrom;
    private Date dateTo;
    private int startRow;
    private int endRow;

    /**
     * Get the messages currently visible on the Table
     *
     * @return List<Message>
     */
    public List<Message> getCurrentMessages() {
        return currentMessages;
    }

    /**
     * Set the messages currently visible on the Table
     *
     * @param currentMessages List<Message>
     */
    public void setCurrentMessages(List<Message> currentMessages) {
        this.currentMessages = currentMessages;
    }

    /**
     * Add the message to current viewing list
     *
     * @param message Message
     */
    public void addMessage(Message message) {
        currentMessages.add(message);
    }

    /**
     * Get the currently visible date of Date panel A
     *
     * @return util.Date
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * Set Date Panel A date
     *
     * @param dateFrom
     */
    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Get the currently visible date of Date panel B
     *
     * @return util.Date
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * Set Date Panel B date
     *
     * @param dateTo
     */
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    /**
     *
     * @return List of Timestamps which corresponds to selected Messages in the
     * Table
     */
    public List<Timestamp> getSelected() {
        return selected;
    }

    /**
     * List of Timestamps which corresponds to selected Messages in the Table
     *
     * @param selected
     */
    public void setSelected(List<Timestamp> selected) {
        this.selected = selected;
    }

    /**
     * Specifies starting row number corresponding to the RDBMS table row number
     * sorted by the timestamp in descending order in the current set of
     * messages of visible on the table
     *
     * @return start row number
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * Specifies starting row number corresponding to the RDBMS table row number
     * sorted by the timestamp in descending order in the current set of
     * messages of visible on the table
     *
     * @param startRow
     */
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    /**
     * Specifies ending row number corresponding to the RDBMS table row number
     * sorted by the timestamp in descending order in the current set of
     * messages of visible on the table.
     *
     * @return end row number
     */
    public int getEndRow() {
        return endRow;
    }

    /**
     * Specifies ending row number corresponding to the RDBMS table row number
     * sorted by the timestamp in descending order in the current set of
     * messages of visible on the table
     *
     * @param endRow
     */
    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

}
