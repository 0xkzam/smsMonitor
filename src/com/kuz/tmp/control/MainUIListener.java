package com.kuz.tmp.control;

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
     * @return true is successful, false otherwise
     */
    boolean deleteAction(MainUIEvent event);

    /**
     * Filter messages using dates specified
     *
     * @param event MainUIEvent
     * @return true is successful, false otherwise
     */
    boolean filterAction(MainUIEvent event);

    /**
     * Get the next set of newer Messages(constant set size)
     *
     * @param event MainUIEvent
     * @return true is successful, false otherwise
     */
    boolean newButtonAction(MainUIEvent event);

    /**
     * Get the next set of older Messages(constant set size)
     *
     * @param event MainUIEvent
     * @return true is successful, false otherwise
     */
    boolean oldButtonAction(MainUIEvent event);

    /**
     * Load the most resent Messages
     *
     * @param event MainUIEvent
     * @return true is successful, false otherwise
     */
    boolean resetAction(MainUIEvent event);
}
