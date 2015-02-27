package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Abstract class used to observe text messages, should be implemented by
 * classes which need to receive the text message feed
 *
 * @author Kasun Amarasena
 */
public abstract class MessageObserver extends ComPortObserver {

    private Logger logger = Logger.getLogger(MessageObserver.class);   

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String simData = new String(inputBuffer);

        if (simData.indexOf("OK") != -1) {
            List<Message> list = Util.process(simData);
            if (list != null) {
                update(list);
            }
            //Throws null pointer - reason ????????
//            if (!list.isEmpty()) {
//                System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
//            }
        } else if (simData.indexOf("ERROR") != -1) {
            logger.error("AT ERROR!");
        }
    }

    /**
     * New SIM Data(listed) is passed to this method as a List of Message
     * objects when data is available.
     *
     * @param listOfMessages List<Message>
     */
    public abstract void update(List<Message> listOfMessages);    
}
