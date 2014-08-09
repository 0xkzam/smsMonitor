/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.control.controller;

import com.control.helper.Logger;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import com.model.Query;
import com.viewer.MessageDialogBox;

/**
 * This class is a type of Buffered comPortReader which listens to the message
 * buffer of a ComporReader object and initiate the insertion of text message
 * data to the database.
 *
 * @author Kasun Amarasena
 */
final class MessageBufferReader implements Runnable {

    private static String tempMsgBuffer;
    private ComPortReader comPortReader;
    private ConcurrentReader concurrentReader;

    public MessageBufferReader(ComPortReader comPortReader, ConcurrentReader concurrentReader) {

        if (comPortReader != null && concurrentReader != null) {
            this.comPortReader = comPortReader;
            this.concurrentReader = concurrentReader;
        } else {
            Logger.printError(this.getClass().getName(),"","ComPortReader/ConCurrentReader Null instance."); //logger 
            MessageDialogBox.showUnknownError();            
        }
    }

    @Override
    public void run() {

        while (true) {
            /*
            if(!concurrentReader.isWriterAlive()){
                Logger.print("Writer(ComPortReader) thread is not alive");
                concurrentReader.stop();
            }
            */
            try {
                Thread.sleep(900);
            } catch (InterruptedException ex) {
                Logger.printInfo(this.getClass().getName(),"run","[Thread.sleep()-1]:" + ex); //logger             
                return;
            }
            String msgBuffer = this.comPortReader.getMsgBuffer();
            if (msgBuffer == null) {
                continue;
            }
            if (msgBuffer.equals(tempMsgBuffer)) {
                continue;
            } else {
                tempMsgBuffer = msgBuffer;
            }
            List<Integer> indices = this.processMSGBuffer(msgBuffer);
            if (indices != null) {
                if (!indices.isEmpty()) {
                    for (int index : indices) {
                        try {
                            this.comPortReader.send("AT+CMGD=" + index + "\r\n");
                        } catch (InterruptedException ex) {
                            Logger.printError(this.getClass().getName(),"run","AT DELETE:" + ex); //logger            

                        }
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.printInfo(this.getClass().getName(),"run","[Thread.sleep()-2]:" + ex); //logger        
                return;
            }
        }
    }

    /**
     * Split and process the input string to obtain the necessary details of the
     * text messages and send the extracted details to the database.
     *
     * @param st - A string of list of messages.
     */
    private List<Integer> processMSGBuffer(String st){
        if (st.indexOf("ERROR") != -1) {
            Logger.printError(this.getClass().getName(),"processMSGBuffer","AT : ERROR"); //logger
            this.concurrentReader.stop();            
            return null;
        }
        List<Integer> indices = new ArrayList<>();/*List of indices of each message in the modem*/

        List<String> phoneNos = new ArrayList<>();/*List of Phone Numbers/Special idenfications*/

        List<java.sql.Date> dates = new ArrayList<>(); /*List of dates*/

        List<java.sql.Time> times = new ArrayList<>(); /*List of message time */

        List<String> msgList = new ArrayList<>();/*List of text messages*/

        st = st.trim();
        String[] lines = st.split("\n");

        outerLoop:
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].startsWith("+CMGL")) {
                lines[i] = lines[i].replaceAll("\"", "");
                String[] data = lines[i].split(",");

                data[0] = data[0].replaceAll("[^0-9]+", "");
                indices.add(Integer.parseInt(data[0])); // add message indices

                data[5] = data[5].substring(0, 8);

                /**
                 * *************************************************************
                 * // To be formatted when displaying in the UI 
                 * if(data[2].startsWith("+94")) { data[2] = "0" +
                 * data[2].substring(3); } else if
                 * (data[2].equals("85115971031013265108101114116")) { data[2] =
                 * "Usage Alert"; }
                 * ************************************************************
                 */
                phoneNos.add(data[2]); // add Phone Numbers/Special idenfications

                String[] date = data[4].split("/");
                int year = Integer.parseInt("20" + date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                dates.add(new Date(year - 1900, month-1, day)); // add Date

                String[] time = data[5].split(":");
                int hour = Integer.parseInt(time[0]);
                int minutes = Integer.parseInt(time[1]);
                int seconds = Integer.parseInt(time[2]);
                times.add(new Time(hour, minutes, seconds)); // add Time

            } else {
                String message = "";
                for (int k = i; k < lines.length; k++) {
                    if (lines[k].startsWith("+CMGL")) {
                        i = k - 1;
                        break;
                    }
                    if (lines[k].equals("") || lines[k].indexOf("OK") != -1) {
                        msgList.add(message.trim()); //add last text message of the buffer
                        break outerLoop;
                    }
                    message += (lines[k] + "\n");
                }
                msgList.add(message.trim()); // add text messages
            }
        }
        
        //Save to database
        try {
            Query.insert(phoneNos, dates, times, msgList);
        } catch (SQLException ex) {
            Logger.printError(this.getClass().getName(),"processMSGVBuffer", ex.toString()); //logger
            return null;
        }
        return indices;
    }
}
