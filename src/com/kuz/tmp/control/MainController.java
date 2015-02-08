package com.kuz.tmp.control;

import com.kuz.tmp.control.db.Query;
import com.kuz.tmp.model.Message;
import com.kuz.tmp.view.MainUI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Main Controller class
 *
 * @author Kasun Amarasena
 */
public class MainController implements MainUIListener {

    private MainUI ui;
    private Query model;

    public MainController() {
    }

    MainController(MainUI ui, Query model) {
        this.ui = ui;
        this.model = model;
    }

    @Override
    public boolean deleteAction(MainUIEvent event) {
        List<Timestamp> selected = event.getSelected();
        int[] rowsAffected = model.delete(selected);

        if (rowsAffected == null) {
            return false;
        } else {
            for (int i : rowsAffected) {
                if (i < 0) {
                    return false;
                }
            }
        }
        event.setCurrentMessages(model.getMessagesFromRange(event.getStartRow(), event.getEndRow()));
        return true;
    }

    @Override
    public boolean filterAction(MainUIEvent event) {
        Date dateFrom = event.getDateFrom();
        Date dateTo = event.getDateTo();
        List<Message> messagesFromRange = model.getMessagesFromRange(dateFrom, dateTo);
        if (messagesFromRange != null) {
            event.setCurrentMessages(messagesFromRange);
            return true;
        }
        return false;
    }

    @Override
    public boolean newButtonAction(MainUIEvent event) {
        List<Message> messagesFromRange = model.getMessagesFromRange(event.getStartRow(), event.getEndRow());
        if (messagesFromRange != null) {
            event.setCurrentMessages(messagesFromRange);
            return true;
        }
        return false;
    }

    @Override
    public boolean oldButtonAction(MainUIEvent event) {
        return newButtonAction(event);
    }

    @Override
    public boolean resetAction(MainUIEvent event) {
        return newButtonAction(event);
    }

}
