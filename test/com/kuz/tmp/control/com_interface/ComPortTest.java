package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortTest {

    private static ComPort comport;
    private static ComPortObserverImpl obs;

    private static synchronized void setUpClass() {
        try {
            comport = new ComPort(CommPortIdentifier.getPortIdentifier("COM4"));
            comport.send("AT\r\n");
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            assertFalse("Specified port may not be available:" + ex.toString(), true);
        }
        obs = new ComPortObserverImpl();
        comport.addObserver(obs);
    }

    public static synchronized void tearDownClass() {
        comport.close();
        try {
            comport.send("AT\r\n");
            assertFalse("Port not closed", true);
        } catch (IOException ex) {
        }
    }

    public static synchronized void test1() throws InterruptedException, IOException {
        comport.send("AT+CGMI\r\n"); //Get the name of manufacturer
    }

    public static synchronized void test2() throws InterruptedException, IOException {
        comport.send("AT+GMI\r\n");//Get the ID of manufacturer
    }

    public static synchronized void test3() throws InterruptedException, IOException {
        comport.send("AT+GMM\r\n");//Get the Model information 
    }

    public static synchronized void test4() throws InterruptedException, IOException {;
        comport.send("AT+CGSN\r\n");//Get IMEI number (International Mobile Equipment Identity)
    }

    public static synchronized void test5() throws InterruptedException, IOException {
        comport.send("AT+CPAS\r\n"); //No result
    }

    public static synchronized void test6() throws InterruptedException, IOException {
        comport.send("AT+CREG\r\n");//No result
    }

    public static synchronized void test7() throws InterruptedException, IOException {
        comport.send("AT+COPS?\r\n"); //
    }

    public static synchronized void test8() throws InterruptedException, IOException {
        comport.send("AT+CSPN?\r\n");//service provider name from the SIM - NO result
    }
    
    public static synchronized void test9() throws InterruptedException, IOException {
        comport.send("AT+CNUM=?\r\n");//NO result
    }

    public static synchronized void test10() throws InterruptedException, IOException {
        comport.send("AT+CIMI\r\n");//Get IMSI number (International Mobile Subscriber Identity)
    }
    
    public static synchronized void test11() throws InterruptedException, IOException {
        comport.send("AT+CGMM\r\n");//Get the Model information 
    }
    
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
//    /**
//     * Test of setBufferSize method, of class ComPort.
//     */
//    @Test
//    public void testSetBufferSize() {
//        System.out.println("setBufferSize");
//        int bufferSize = 500;
//        comport.setBufferSize(bufferSize);
//        assertTrue(bufferSize == comport.getBufferSize());
//    }
    public static void main(String[] args) throws InterruptedException, IOException {
        setUpClass();
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
        test3();

        Thread.sleep(3000L);
        tearDownClass();
    }
}
