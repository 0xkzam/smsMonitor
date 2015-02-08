/*
 * Copyright (C) 2015 Kasun Amarasena
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
package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of ConcurrentCommandSender
 * 
 * @author Kasun Amarasena
 */
public class ConcurrentCommandSenderImplTest extends ComPortObserver{

    public ConcurrentCommandSenderImplTest() {
    }

    @Test
    public void test() {

        //Connecting to COM1
        ComPortConnector connector = ComPortConnector.getInstance();
        ComPort port = null;
        try {
            port = connector.connectTo(CommPortIdentifier.getPortIdentifier("COM1"));
        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
            fail("Connecting to Comport COM1"+ex.toString());
        }
        
        //Connecting to COM1333453
        try {
            port = connector.connectTo(CommPortIdentifier.getPortIdentifier("COM1333453"));
            fail("Connecting to Comport COM133453");
        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
            assertTrue("Connecting to Comport COM133453",true);           
        }       
        
        
        port.addObserver(new ComPortObserverImpl());

        //verify the connection
        try {
            port.send("AT\r\n");
        } catch (IOException ex) {
            fail("Connection verification failed"+ex.toString());
        }

        //Setting text mode
        try {
            port.send("AT+CMGF=1\r\n");
        } catch (IOException ex) {
            fail("Error Setting text mode"+ex.toString());
        }

        //Set the comport to a ConcurrentCommandSender
//        ConcurrentCommandSender sender = new ConcurrentCommandSenderImpl();
//        sender.setPort(port);
//        sender.setCommand("AT+CMGL=\"ALL\"\r\n");

    }

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String info = new String(inputBuffer);                
        assertTrue("AT ERROR", info.indexOf("OK") != -1);       
        System.out.println("info OK:"+(info.indexOf("OK") != -1));
        System.out.println("info ERROR:"+(info.indexOf("ERROR") != -1));
    }
}
