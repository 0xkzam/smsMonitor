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
package com.kuz.tmp.old.controller;

import com.kuz.tmp.old.controller.MessageBufferReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class MessageBufferReaderTest {

    public MessageBufferReaderTest() {
    }

    /**
     * Test of processMSGBuffer method, of class MessageBufferReader.
     */
    @Test
    public void testProcessMSGBuffer() {
        System.out.println("Testing processMSGBuffer()\n");
        String st = "\n"
                + "+CMGL: 6,\"REC READ\",\"+94715882182\",,\"14/07/26,13:12:58+22\"\n"
                + "Testing phase 16 MessageBufferClass\n"
                + "+CMGL: 8,\"REC READ\",\"+94715882182\",,\"14/07/26,14:38:50+22\"\n"
                + "Testing phase 17 MessageBufferClass synchronized.\n"
                + "+CMGL: 9,\"REC READ\",\"85115971031013265108101114116\",,\"14/07/26,17:41:00+22\"\n"
                + "Balance Bundle : 438.78MB\n"
                + "Unbilled Usage as at 24/07/2014 18:50:37-Rs. 0.20,\n"
                + "Billed Due amount - Rs.185.13CR, \n"
                + "Total amount -Rs.184.93CR  \n"
                + "+CMGL: 10,\"REC READ\",\"85115971031013265108101114116\",,\"14/07/27,20:41:57+22\"\n"
                + "Balance Bundle : 438.78MB\n"
                + "Unbilled Usage as at 26/07/2014 12:59:29-Rs. 0.40,\n"
                + "Billed Due amount - Rs.185.13CR, \n"
                + "Total amount -Rs.184.73CR  \n"
                + "\n"
                + "OK";
        MessageBufferReader m = new MessageBufferReader(null, null);
        m.processMSGBuffer(st);

    }

    public static void test3() {
        String st = "\n"
                + "+CMGL: 6,\"REC READ\",\"+94715882182\",,\"14/07/26,13:12:58+22\"\n"
                + "Testing phase 16 MessageBufferClass\n"
                + "+CMGL: 8,\"REC READ\",\"+94715882182\",,\"14/07/26,14:38:50+22\"\n"
                + "Testing phase 17 MessageBufferClass synchronized.\n"
                + "+CMGL: 9,\"REC READ\",\"85115971031013265108101114116\",,\"14/07/26,17:41:00+22\"\n"
                + "Balance Bundle : 438.78MB\n"
                + "Unbilled Usage as at 24/07/2014 18:50:37-Rs. 0.20,\n"
                + "Billed Due amount - Rs.185.13CR, \n"
                + "Total amount -Rs.184.93CR  \n"
                + "+CMGL: 10,\"REC READ\",\"85115971031013265108101114116\",,\"14/07/27,20:41:57+22\"\n"
                + "Balance Bundle : 438.78MB\n"
                + "Unbilled Usage as at 26/07/2014 12:59:29-Rs. 0.40,\n"
                + "Billed Due amount - Rs.185.13CR, \n"
                + "Total amount -Rs.184.73CR  \n"
                + "\n"
                + "OK";
        st = st.trim();
        String[] lines = st.split("\n");
        List<Integer> indices = new ArrayList<>();
        List<List<String>> info = new ArrayList<>();
        List<String> msgList = new ArrayList<>();

        outerLoop:
        for (int i = 0; i < lines.length; i++) {

            if (lines[i].startsWith("+CMGL")) {
                lines[i] = lines[i].replaceAll("\"", "");
                String[] data = lines[i].split(",");

                data[0] = data[0].replaceAll("[^0-9]+", "");
                indices.add(Integer.parseInt(data[0]));

                data[5] = data[5].substring(0, 8);
                List<String> temp = new ArrayList<>();
                if (data[2].equals("85115971031013265108101114116")) {
                    data[2] = "Usage Alert";
                }
                temp.add(data[2]);
                temp.add(data[4]);
                temp.add(data[5]);
                info.add(temp);
            } else {
                String message = "";
                for (int k = i; k < lines.length; k++) {
                    if (lines[k].startsWith("+CMGL")) {
                        i = k - 1;
                        break;
                    }
                    if (lines[k].equals("") || lines[k].indexOf("OK") != -1) {
                        msgList.add(message.trim());
                        break outerLoop;
                    }
                    message += (lines[k] + "\n");
                }
                msgList.add(message.trim());
            }
        }
        System.out.println("List of Indices:" + indices);
        System.out.println("List of details:" + info);
        System.out.println("List of messages:" + msgList);
    }

    public static void test2() {
        String st = "+CMGL: 0,\"REC UNREAD\",\"+94715882182\",,\"14/07/25,14:41:27+22\"\n"
                + "Testing phase 9 interrupts\n"
                + "+CMGL: 1,\"REC UNREAD\",\"+94715882182\",,\"14/07/25,21:06:37+22\"\n"
                + "Testing phase 10 string processing\n"
                + "+CMGL: 2,\"REC UNREAD\",\"+94715882182\",,\"14/07/25,21:18:24+22\"\n"
                + "Testing phase 11 long text ghdhhdhhdbgd djs djshs shshtdgsvd gd dhdbd dvdhhdhehd dhd   dhe dg due  fud djdhx dddehd   bdhdhdbbdbhdhdh jdjhshdhd dhh hdgdhjzhydhh\n"
                + "+CMGL: 2,\"REC UNREAD\",\"+94715882182\",,\"14/07/25,21:18:24+22\"\n"
                + "Testing phase 11 long text ghdhhdhhdbgd djs djshs shshtdgsvd gd dhdbd dvdhhdhehd dhd   dhe dg due  fud djdhx dddehd   bdhdhdbbdbhdhdh jdjhshdhd dhh hdgdhjzhydhh\n"
                + "\n"
                + "OK";

        String[] line = st.split("\n");
        int[] indices = new int[(line.length - 2) / 2];
        String[] info = new String[4]; /*{phoneNumber, date, time, message}*/

        int count = 0;
        for (int i = 0; i < line.length - 2; i += 2) {
            if (line[i].startsWith("+CMGL")) {
                indices[count++] = Integer.parseInt(line[i].substring(6, 8).trim());
                info[0] = line[i].substring(23, 35);
                info[1] = line[i].substring(39, 47);
                info[2] = line[i].substring(48, 56);
                info[3] = line[i + 1];
                System.out.println(indices[count - 1] + " " + info[0] + " " + info[1] + " " + info[2]);
                System.out.println(info[3]);
            }
        }
    }
}
