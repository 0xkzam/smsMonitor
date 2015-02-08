package com.kuz.tmp.control;

import com.kuz.tmp.model.Message;
import java.util.List;

/**
 * Listener of the MainUI implemented by MainController
 *
 * @author Kasun Amarasena
 */
public interface MainUIListener {

    /**
     * Delete selected messages
     *
     * @param event MainUIEvent
     * @return array integers indicating the successful rows effected - refer to
     * <code>java.sql.Statement.executeBatch</code>
     */
    int[] deleteAction(MainUIEvent event);

    /**
     * Filter messages using dates specified
     *
     * @param event MainUIEvent
     * @return List of Messages
     */
    List<Message> filterAction(MainUIEvent event);

    /**
     * Get the next set of newer Messages(constant set size)
     *
     * @param event MainUIEvent
     * @return List of Messages
     */
    List<Message> newButtonAction(MainUIEvent event);

    /**
     * Get the next set of older Messages(constant set size)
     *
     * @param event MainUIEvent
     * @return List of Messages
     */
    List<Message> oldButtonAction(MainUIEvent event);

    /**
     * Load the most resent Messages
     *
     * @param event MainUIEvent
     * @return List of Messages
     */
    List<Message> resetAction(MainUIEvent event);
}
