package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Kasun Amarasena
 */
public abstract class MessageObserver extends ComPortObserver implements MessageObservable{

    private Logger logger = Logger.getLogger(MessageObserver.class);

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String simData = new String(inputBuffer);

        if (simData.indexOf("OK") != -1) {
            update(process(simData));
        } else if (simData.indexOf("ERROR") != -1) {
            logger.error("AT ERROR!");
        }
    }

    /**
     * Process the sim data and constructs the Message objects
     *
     * @param simData
     * @return List of Messages
     */
    private List<Message> process(String simData) {
        simData = simData.trim();
        if (simData.equals("OK")) {
            return null;
        } else {
            //
            //To be completed - String processing & construct Message objects
            //
        }
        return null;
    }

    /**
     * New sim Data(processed) is passed to this method as a List of Message
     * objects when data is available. 
     *
     * @param listOfMessages
     */
    @Override
    public abstract void update(List<Message> listOfMessages);

}
