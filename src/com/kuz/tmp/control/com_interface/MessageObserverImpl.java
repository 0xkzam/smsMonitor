
package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.util.List;

/**
 *
 * @author Kasun Amarasena
 */


public class MessageObserverImpl extends MessageObserver{

    @Override
    public void update(List<Message> listOfMessages) {
        for (Message message : listOfMessages) {
            System.out.println(message);
        }
    }
    
}
