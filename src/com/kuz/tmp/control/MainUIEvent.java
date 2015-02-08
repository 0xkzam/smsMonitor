
package com.kuz.tmp.control;

import com.kuz.tmp.model.Message;
import java.util.Date;
import java.util.List;

/**
 * Contains all Event info of MainUI
 * 
 * @author Kasun Amarasena
 */
public class MainUIEvent {
    
    private List<Message> currentMessages;
    private Date dateA;
    private Date dateB;

    /**
     * Get the messages currently visible on the Table 
     * @return List<Message>
     */
    public List<Message> getCurrentMessages() {
        return currentMessages;
    }

    /**
     * Set the messages currently visible on the Table
     * @param currentMessages List<Message>
     */
    public void setCurrentMessages(List<Message> currentMessages) {
        this.currentMessages = currentMessages;
    }
    
    /**
     * Add the message to current viewing list
     * @param message Message
     */
    public void addMessage(Message message){
        currentMessages.add(message);
    }
    
    /**
     * Get the currently visible date of Date panel A
     * @return util.Date
     */
    public Date getDateA(){
        return dateA;
    }

    /**
     * Set Date Panel A date
     * @param dateA 
     */
    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    /**
     * Get the currently visible date of Date panel B
     * @return util.Date
     */
    public Date getDateB() {
        return dateB;
    }

    /**
     * Set Date Panel B date
     * @param dateB 
     */
    public void setDateB(Date dateB) {
        this.dateB = dateB;
    }
}
