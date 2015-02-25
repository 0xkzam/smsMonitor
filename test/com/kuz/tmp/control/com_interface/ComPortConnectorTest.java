
package com.kuz.tmp.control.com_interface;


import java.io.IOException;
import java.util.List;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortConnectorTest {
    
    private ComPortConnector connector = ComPortConnector.getInstance();
    
    public ComPortConnectorTest() {  }    
       
    @Before
    public void setUp() {
        System.out.println("--------------------------------------------------------");
    }
    
    @After
    public void tearDown() {
        System.out.println("--------------------------------------------------------");
    }

    @Test
    public void test1() {       
        
        List<String> list = ComPortConnector.getInstance().getAvailableComports();
        System.out.println("available ports:"+list);
        try {
            CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("COM1");
            System.out.println("owner:"+portID.getCurrentOwner());
            System.out.println("type:"+portID.getPortType());
            System.out.println("isCurrentlyOwned:"+portID.isCurrentlyOwned());     
            
        } catch (NoSuchPortException ex) {
            fail();
        }
    }
    
    @Test
    public void test2() throws InterruptedException {
        try {
            ComPort port = connector.connectTo("COM4");
            ComPortObserver ob = new ComPortObserverImpl();
            port.addObserver(ob);
            port.send("AT\r\n");
            port.send("AT+CMGF=1\r\n");
            port.send("AT+CMGL=\"ALL\"\r\n");
            Thread.sleep(2000);
            port.send("AT+CMGL=\"ALL\"\r\n");
            
        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
            fail(ex.getMessage());
        }
    }

    
    
}
