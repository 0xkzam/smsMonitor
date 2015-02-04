
package com.kuz.tmp.view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Utility class for displaying message dialog boxes
 * 
 * @author Kasun Amarasena
 */
public class MessageDialogBox {

    private static Component parentComponent = null;

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
                + "\nAuthor: Kasun Amarasena  (email : kasunAmarasena@gmail.com) "
                + "\nBuilt using JDK (Java SE Development Kit) 1.7.0_45 and javax.comm API 1.0"
                + "\n \n", "About SMS Monitor 1.0v", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isConfirmed() {
        int x = JOptionPane.showConfirmDialog(parentComponent, "Are you sure you want to delete the seleted messages?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return x == JOptionPane.YES_OPTION;
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
