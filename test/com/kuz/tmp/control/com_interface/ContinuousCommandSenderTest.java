package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 *
 * @author Kasun Amarasena
 */
public class ContinuousCommandSenderTest {

    private ComPortConnector connector = ComPortConnector.getInstance();

    public ContinuousCommandSenderTest() {
    }

    @Before
    public void setUp() {
        System.out.println("--------------------------------------------------------");        
    }

    @After
    public void tearDown() {
        System.out.println("--------------------------------------------------------");
    }

//    @Test
//    public void test1() {
//
//        List<String> list = ComPortConnector.getInstance().getAvailableComports();
//        System.out.println("available ports:" + list);
//        try {
//            CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("COM1");
//            System.out.println("owner:" + portID.getCurrentOwner());
//            System.out.println("type:" + portID.getPortType());
//            System.out.println("isCurrentlyOwned:" + portID.isCurrentlyOwned());
//
//        } catch (NoSuchPortException ex) {
//            fail();
//        }
//    }

//    @Test
//    public void test2() throws InterruptedException {
//        try {
//            ComPort port = connector.connectTo("COM4");
//            ComPortObserver ob = new ComPortObserverImpl();
//            port.addObserver(ob);
//            port.send(At.CHECK.toString());
//            port.send(At.SET_TEXT_MODE.toString());
//            port.send(At.READ_ALL.toString());
//            Thread.sleep(2000);
//            port.send(At.READ_ALL.toString());
//
//        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
//            fail(ex.toString());
//        }
//    }

    
    public void test3() throws InterruptedException, NoSuchPortException, IOException, PortInUseException, TooManyListenersException {
        
            ComPort port = ComPortConnector.getInstance().connectTo("COM4");
            ComPortObserver observer = new ComPortObserverImpl();
            port.addObserver(observer);

            List<String> pre = new ArrayList<>(2);
            //pre.add(At.CHECK.toString());
           // pre.add(At.SET_TEXT_MODE.toString());

            ContinuousCommandSender sender = new ContinuousCommandSenderImpl(port, At.READ_ALL, pre);
            sender.setInterval(2000L);
            sender.start(); 
                    
    }
    
    public static void main(String[] args) {
        try {
            new ContinuousCommandSenderTest().test3();
        } catch (InterruptedException | NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
            Assert.fail(ex.toString());
        }
    }

}
