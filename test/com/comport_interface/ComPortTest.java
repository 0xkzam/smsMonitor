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

package com.comport_interface;

import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortTest {    
    private static ComPort comport;
    private static MyComPortObserver obs;
    
    public ComPortTest() {
    }
    
    @BeforeClass
    public static void setUpClass(){
        try {
            comport = new ComPort(CommPortIdentifier.getPortIdentifier("COM10"));
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            assertFalse("Specified port may not be available:"+ex, true);
        }
        obs = new MyComPortObserver();
        comport.addObserver(obs);
    }
    
    @AfterClass
    public static void tearDownClass() {
        comport.close();       
        try {
            comport.send("AT\r\n");
            assertFalse("Port not closed", true);
        } catch (IOException ex) { 
        }
    }   
    
    

    /**
     * Test of send method, of class ComPort.
     */
    @Test
    public void testSend_String(){
        System.out.println("send");
        String command = "AT\r\n";       
        try {
            comport.send(command);
        } catch (IOException ex) {
            assertFalse("", true);
        }
    }

    /**
     * Test of send method, of class ComPort.
     */
    @Test
    public void testSend_byteArr() {
        System.out.println("send");
        String s = "AT\r\n";         
        byte[] command = s.getBytes();        
        try {
            comport.send(command);
        } catch (IOException ex) {
            assertFalse("", true);
        }
       
    }   



    /**
     * Test of setBufferSize method, of class ComPort.
     */
    @Test
    public void testSetBufferSize() {
        System.out.println("setBufferSize");
        int bufferSize = 500;        
        comport.setBufferSize(bufferSize);        
        assertTrue(bufferSize == comport.getBufferSize());
    }
    
}
