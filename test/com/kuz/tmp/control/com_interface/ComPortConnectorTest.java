
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
public class ComPortConnectorTest extends ComPortObserver{

    public ComPortConnectorTest() {
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
