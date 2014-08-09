/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viewer;

import com.control.helper.Logger;
import com.model.Query;
import java.sql.SQLException;

import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

/**
 * This is a helper class which is a JPanel designed to add external swing
 * component-JDatePickerImpl to a JFrame form.
 *
 * @author Kasun Amarasena
 */
public class DatePanel extends JPanel {

    private SqlDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;

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
            Logger.printError(this.getClass().getName(),"setMinDate", ex.toString()); //logger
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
            Logger.printError(this.getClass().getName(),"setCurrentDate",ex.toString()); //logger
        }
    }
}
