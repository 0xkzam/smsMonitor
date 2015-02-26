package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Kasun Amarasena
 */
public abstract class MessageObserver extends ComPortObserver {

    private Logger logger = Logger.getLogger(MessageObserver.class);  
    private final DateFormat format = new SimpleDateFormat("yy/MM/ddHH:mm:ss");

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String simData = new String(inputBuffer);

        if (simData.indexOf("OK") != -1) {
            List<Message> list = process(simData);
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

    /**
     * Process the SIM data and constructs the Message objects
     *
     * @param simData String of data received from the SIM
     * @return List of Messages
     */
    private List<Message> process(String simData) {
        simData = simData.trim();
        if (simData.equals("OK")) {
            return null;
        }
        List<Message> list = new ArrayList<>();
        String[] lines = simData.split("\n");
        Message msg = null;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            if (line.indexOf("+CMGL:") > -1) {
                msg = new Message();
                line = line.replaceAll("\"", "");
                String[] info = line.split(",");

                //setting number
                msg.setNumber(info[2]);

                //setting received date               
                msg.setReceivedDate(new Date());

                //setting sent date               
                try {
                    Date receivedDate = format.parse(info[4] + info[5]);
                    msg.setSentDate(receivedDate);
                } catch (ParseException ex) {
                    logger.error("Error setting sent date", ex);
                }

                //setting message contents
                int temp = i;
                while ((++temp < lines.length) && lines[temp].indexOf("+CMGL:") <= -1) {
                    if (lines[temp].indexOf("OK") > -1) {
                        continue;
                    }
                    if (msg.getContents() != null) {
                        msg.setContents(msg.getContents() + lines[temp] + "\n");
                    } else {
                        msg.setContents(lines[temp] + "\n");
                    }
                }
                i = temp - 1;
            }
            if (msg != null) {
                msg.setContents(msg.getContents().trim());
                list.add(msg);
            }
        }
        return list;
    }
}
