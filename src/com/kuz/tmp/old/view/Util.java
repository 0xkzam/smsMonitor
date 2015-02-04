
package com.kuz.tmp.old.view;


import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

import java.text.SimpleDateFormat;

/**
 * This class contains helper methods for formatting and displaying data in the
 * main UI
 *
 * @author Kasun Amarasena
 */
public class Util {   

    /**
     * @param s String phone number
     * @return formatted phone no for GUI displaying purposes
     */
    public static String formatPhoneNo(String s) {
        if (s.startsWith("+94")) {
            s = "0" + s.substring(3);
        }
        if (s.equals("85115971031013265108101114116")) {
            s = "Usage Alert";
        }
        return s;
    }

    /**
     * Util method when deleting a record from the database
     *
     * @param s String phone number
     * @return reformatted phone no which removes the formatting done
     * previously.
     */
    public static String reFormatPhoneNo(String s) {
        if (s.startsWith("0")) {
            s = "+94" + s.substring(1);
        }
        if (s.equals("Usage Alert")) {
            s = "85115971031013265108101114116";
        }
        return s;
    }

    /**
     * @return Date object of the passed String
     */
    public static Date stringToDate(String s) {
              
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parse = time.parse(s);
            return new Date(parse.getTime());
        } catch (ParseException ex) {
            return null;
        }    
    }

    /**
     *
     * @param string
     * @return Time object of the passed String
     */
    public static Time stringToTime(String string) {
        SimpleDateFormat time = new SimpleDateFormat("h:mm:ss a");
        try {
            java.util.Date parse = time.parse(string);
            return new Time(parse.getTime());
        } catch (ParseException ex) {
            return null;
        }        
    }
}
