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

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Kasun Amarasena
 */
public class MessageDialogBox {

    static Component parentComponent = null;

    public static void showErrorMessage(String msg, String title) {
        JOptionPane.showMessageDialog(parentComponent, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(parentComponent, msg, msg, JOptionPane.ERROR_MESSAGE);
    }

    public static void showUnknownError() {
        JOptionPane.showMessageDialog(parentComponent, "Unknown connection error! \n"
                + "Please close the close and restart.", "ERROR!", JOptionPane.ERROR_MESSAGE);
    }

    public static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void aboutPage() {
        JOptionPane.showMessageDialog(null, "SMS Monitor 1.0v"
                + "\nCopyright (C) August, 2014"
                + "\nAuthor: Kasun Amarasena  (email : kasunAmarasena@gmail.com) \n"
                + "\nBuilt uising JDK (Java SE Development Kit) 1.7.0_45 and javax.comm API 1.0"
                + "\n \n", "About SMS Monitor 1.0v", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isConfirmed() {
        int x = JOptionPane.showConfirmDialog(parentComponent, "Are you sure you want to delete the seleted messages?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (x == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    /**
     * Set the parent component for the Message Dialog Boxes
     *
     * @param parent Parent Component
     */
    public static void setParentComponent(Component parent) {
        parentComponent = parent;
    }
}
