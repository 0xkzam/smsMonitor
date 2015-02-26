package com.kuz.tmp.control.com_interface;

import org.junit.After;
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
//            port.send("AT\r\n");
//            port.send("AT+CMGF=1\r\n");
//            port.send("AT+CMGL=\"ALL\"\r\n");
//            Thread.sleep(2000);
//            port.send("AT+CMGL=\"ALL\"\r\n");
//
//        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
//            fail(ex.toString());
//        }
//    }

//    @Test
//    public void test3() throws InterruptedException, NoSuchPortException, IOException, PortInUseException, TooManyListenersException {
//        
//            ComPort port = ComPortConnector.getInstance().connectTo("COM4");
//            ComPortObserver observer = new ComPortObserverImpl();
//            port.addObserver(observer);
//
//            List<String> pre = new ArrayList<>(2);
//            pre.add("AT\r\n");
//            pre.add("AT+CMGF=1\r\n");
//
//            ContinuousCommandSender sender = new ContinuousCommandSenderImpl(port, "AT+CMGL=\"ALL\"\r\n", pre);
//            sender.setInterval(2000L);
//            sender.start(); 
//        
//    }

}
