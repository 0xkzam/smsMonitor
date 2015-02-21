package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.util.List;

/**
 * This must be implemented by the UI and Data model classes to be able to
 * observe incoming Messages real time
 *
 * @author Kasun Amarasena
 */
public interface MessageObservable {

    /**
     * New sim Data(processed) is passed to this method as a List of Message
     * objects when data is available.
     *
     * @param listOfMessages
     */
    abstract void update(List<Message> listOfMessages);
}
