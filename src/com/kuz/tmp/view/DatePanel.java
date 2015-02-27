package com.kuz.tmp.view;

import java.util.Date;
import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.apache.log4j.Logger;

/**
 * This is a JPanel designed to add external Swing component-JDatePickerImpl to
 * a JFrame form.
 *
 * @author Kasun Amarasena
 */
public class DatePanel extends JPanel {

    private UtilDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private final Logger logger = Logger.getLogger(DatePanel.class);

    public DatePanel() {

        dateModel = new UtilDateModel();

        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        this.add(datePicker);
    }

    /**
     *
     * @return Selected java.sql.Date
     */
    public Date getDate() {
        return (Date) datePicker.getModel().getValue();
    }

    /**
     * Set JDatePanel initial display date to the earliest date of the message
     * received.
     */
    public void setMinDate() {
        //To do
        dateModel.setSelected(true);
    }

    /**
     * Set JDatePanel initial display date to current date.
     */
    public void setCurrentDate() {
        dateModel.setValue(new Date());
        dateModel.setSelected(true);
    }
}
