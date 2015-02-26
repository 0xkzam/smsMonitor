package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
    public static void setUpClass() {
        try {
            comport = new ComPort(CommPortIdentifier.getPortIdentifier("COM4"));
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            assertFalse("Specified port may not be available:" + ex, true);
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

    @Before
    public void before() {
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    /**
     * Test of send method, of class ComPort.
     * @throws java.lang.InterruptedException
     * @throws java.io.IOException
     */
    @Test
    public void testSend_String() throws InterruptedException, IOException {
        System.out.println("send");       
        
        comport.send("AT\r\n");
        Thread.sleep(1000L);
        comport.send("AT+CRSM\r\n");
        Thread.sleep(2000L);
        comport.send("AT+CSIM\r\n");
        Thread.sleep(2000L);
        comport.send("AT+CMGF=1\r\n");
        Thread.sleep(2000L);
        comport.send("AT+CMGL=\"ALL\"\r\n");

    }

    /**
     * Test of send method, of class ComPort.
     */
//    @Test
//    public void testSend_byteArr() {
//        System.out.println("send");
//        String s = "AT\r\n";         
//        byte[] command = s.getBytes();        
//        try {
//            comport.send(command);
//        } catch (IOException ex) {
//            assertFalse("", true);
//        }
//       
//    }   
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
