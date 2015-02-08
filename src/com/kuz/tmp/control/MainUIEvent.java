
package com.kuz.tmp.control;

import com.kuz.tmp.model.Message;
import java.util.List;

/**
 * Contains all Event info of MainUI
 * 
 * @author Kasun Amarasena
 */
public class MainUIEvent {
    
    private List<Message> currentMessages;

    public List<Message> getCurrentMessages() {
        return currentMessages;
    }

    public void setCurrentMessages(List<Message> currentMessages) {
        this.currentMessages = currentMessages;
    }
    
    public void addMessage(Message message){
        currentMessages.add(message);
    }
}
