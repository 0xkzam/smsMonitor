
package com.kuz.tmp.old.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Message DAO class
 * 
 * @author Kasun Amarasena
 */
public class Message {
    
    private String number;
    private String message;
    private Date date;
    private Time time;
    
    public Message(){}
    
    public Message(String number, String message, Date date, Time time){
        this.number = number;
        this.message = message;
        this.date = date;
        this.time = time;
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
    
    public Time getTime() {
        return time;
    }
    
    public void setTime(Time time) {
        this.time = time;
    }
}
