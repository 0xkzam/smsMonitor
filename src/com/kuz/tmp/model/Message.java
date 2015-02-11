
package com.kuz.tmp.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Message Bean class
 * 
 * @author Kasun Amarasena
 */
public class Message {
    
    private String number;
    private String contents;    
    private Date sentDate; //Sent sentDate    
    private Timestamp stamp; //Received timestamp
    
    public Message(){}
    
    /**     
     * @param number Phone number as a String
     * @param contents Message contents
     * @param sentDate Message sent Date & Time
     * @param stamp Received Timestamp    
     */
    public Message(String number, String contents, Date sentDate, Timestamp stamp){
        this.number = number;
        this.contents = contents;
        this.sentDate = sentDate;        
        this.stamp = stamp;
    }      

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String message) {
        this.contents = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date date) {
        this.sentDate = date;
    }

    public Timestamp getStamp() {
        return stamp;
    }

    public void setStamp(Timestamp stamp) {
        this.stamp = stamp;
    }    

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.sentDate);
        hash = 67 * hash + Objects.hashCode(this.stamp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.sentDate, other.sentDate)) {
            return false;
        }
        return Objects.equals(this.stamp, other.stamp);
    }
    
    
}
