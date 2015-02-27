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
public class Util {
    
    private static Logger logger = Logger.getLogger(Util.class);
    private final static DateFormat dateFormat = new SimpleDateFormat("yy/MM/ddHH:mm:ss");

    /**
     * Process the SIM data and constructs the Message objects
     *
     * @param simData String of data received from the SIM
     * @return List of Messages
     */
    public static List<Message> process(String simData) {
        simData = simData.trim();
        if (simData.equals("OK")) {
            return null;
        }
        List<Message> list = new ArrayList<>();
        String[] lines = simData.split("\n");
        Message msg = null;

        outerLoop:
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
                    Date receivedDate = dateFormat.parse(info[4] + info[5]);
                    msg.setSentDate(receivedDate);
                } catch (ParseException ex) {
                    logger.error("Error setting sent date", ex);
                }

                //setting message contents
                int temp = i;
                while ((++temp < lines.length) && lines[temp].indexOf("+CMGL:") <= -1) {
                    if (lines[temp].indexOf("OK") > -1 || lines[temp].trim().equals("")) {
                        break outerLoop;
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
