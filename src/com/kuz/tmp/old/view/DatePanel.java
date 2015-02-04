
package com.kuz.tmp.old.view;


import com.kuz.tmp.old.model.Query;
import java.sql.SQLException;

import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;
import org.apache.log4j.Logger;

/**
 * This is a JPanel designed to add external Swing
 * component-JDatePickerImpl to a JFrame form.
 *
 * @author Kasun Amarasena
 */
public class DatePanel extends JPanel {

    private SqlDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private final Logger logger = Logger.getLogger(DatePanel.class);    

    public DatePanel() {

        dateModel = new SqlDateModel();

        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        this.add(datePicker);
    }

    /**
     *
     * @return Selected java.sql.Date
     */
    public java.sql.Date getDate() {
        return (java.sql.Date) datePicker.getModel().getValue();
    }

    /**
     * Set JDatePanel initial display date to the earliest date of the message
     * received.
     */
    public void setMinDate() {
        try {
            dateModel.setValue(Query.getMinDate());
            dateModel.setSelected(true);
        } catch (SQLException ex) {
            logger.error("Error setting MinDate", ex);
        }
    }

    /**
     * Set JDatePanel initial display date to current date.
     */
    public void setCurrentDate() {
        try {
            dateModel.setValue(Query.getCurrentDate());
            dateModel.setSelected(true);
        } catch (SQLException ex) {
            logger.error("Error setting Current date", ex);
        }
    }
}
