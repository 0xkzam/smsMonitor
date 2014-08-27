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
public class Helper {

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
     * Helper method when deleting a record from the database
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
    static Date stringToDate(String s) {
        String[] str = s.split("-");
        int year = Integer.parseInt(str[0]);
        int month = Integer.parseInt(str[1]);
        int day = Integer.parseInt(str[2]);
        return new Date(year - 1900, month, day);
    }

    /**
     *
     * @param string
     * @return Time object of the passed String
     */
    static Time stringToTime(String string) {
        SimpleDateFormat time = new SimpleDateFormat("h:mm:ss a");
        try {
            java.util.Date parse = time.parse(string);
            return new Time(parse.getHours(), parse.getMinutes(), parse.getSeconds());
        } catch (ParseException ex) {
            Logger.printError("com.viewe.Helper", "stringToTime", ex.toString()); //logger
        }
        return null;
    }
}
