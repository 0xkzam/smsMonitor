package com.kuz.tmp.model.bean;

import java.util.Date;
import java.util.Objects;

/**
 * Message Bean class
 *
 * @author Kasun Amarasena
 */
public class Message implements Comparable<Message> {

    private String number;
    private String contents;
    private Date sentDate; //Date included in the message details(in sim data)
    private Date receivedDate;

    public Message() {
    }

    /**
     * @param number Phone number as a String
     * @param contents Message contents
     * @param sentDate Message sent Date according to SIM data
     * @param receivedDate Message received Date by the System
     */
    public Message(String number, String contents, Date sentDate, Date receivedDate) {
        this.number = number;
        this.contents = contents;
        this.sentDate = sentDate;
        this.receivedDate = receivedDate;
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

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.sentDate);
        hash = 67 * hash + Objects.hashCode(this.receivedDate);
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
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.sentDate, other.sentDate)) {
            return false;
        }
        return Objects.equals(this.receivedDate, other.receivedDate);
    }

    @Override
    public String toString() {
        return "Message{" + "number=" + number + ", sentDate=" + sentDate + ", receivedDate=" + receivedDate + ", contents=\n" + contents + '}';
    }

    /**
     * Sort in descending order using sent date
     *
     * @param msg
     */
    @Override
    public int compareTo(Message msg) {
        return (int) (msg.getSentDate().getTime() - this.getSentDate().getTime());

    }

}
