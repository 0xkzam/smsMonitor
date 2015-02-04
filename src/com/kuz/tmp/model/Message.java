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

package com.kuz.tmp.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Message DAO class
 * 
 * @author Kasun Amarasena
 */
public class Message {
    
    private String number;
    private String message;
    private Date date;    
    private Timestamp stamp;
    
    public Message(){}
    
    /**     
     * @param number Phone number as a String
     * @param message Message contents
     * @param date Message sent Date & Time
     * @param stamp Received Timestamp    
     */
    public Message(String number, String message, Date date, Timestamp stamp){
        this.number = number;
        this.message = message;
        this.date = date;        
        this.stamp = stamp;
    }      

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getStamp() {
        return stamp;
    }

    public void setStamp(Timestamp stamp) {
        this.stamp = stamp;
    }
    
}
