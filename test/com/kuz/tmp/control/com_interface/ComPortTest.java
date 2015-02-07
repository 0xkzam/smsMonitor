
package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.control.com_interface.ComPort;
import com.kuz.tmp.control.com_interface.ComPortObserverImpl;
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
    private static ComPortObserverImpl obs;
    
    public ComPortTest() {
    }
    
    @BeforeClass
    public static void setUpClass(){
        try {
            comport = new ComPort(CommPortIdentifier.getPortIdentifier("COM10"));
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            assertFalse("Specified port may not be available:"+ex, true);
        }
        obs = new ComPortObserverImpl();
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
            comport.send("AT+CMGF=1\r\n");
            comport.send("AT+CMGL=\"ALL\"\r\n");
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
