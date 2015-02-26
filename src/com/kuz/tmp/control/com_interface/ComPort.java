
package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.TooManyListenersException;
import java.util.Observable;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;

/**
 * This class is a wrapper around a COM port with a Serial interface.
 *
 * @author Kasun Amarasena
 */
public class ComPort extends Observable implements SerialPortEventListener {

    private final CommPortIdentifier portID;
    private SerialPort port;
    private InputStream inputStream;
    private OutputStream outputStream;
    private byte[] readBuffer; // Input Stream Buffer    
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    private int bufferSize;

    public ComPort(CommPortIdentifier portID) throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {
        this.portID = portID;
        register();
        bufferSize = DEFAULT_BUFFER_SIZE;
    }

    /**
     * This method is used to read the input byte stream
     *
     * @return byte[] of the input stream
     */
    public byte[] getReadBuffer() {
        return readBuffer;
    }

    /**
     * Send commands to the COM port as Strings <br/>
     * ex: AT commands
     *
     * @param command String
     * @throws java.io.IOException
     */
    public synchronized void send(String command) throws IOException {
        outputStream.write(command.getBytes());
    }

    /**
     * Send commands to the COM port as an array of bytes
     *
     * @param command byte[]
     * @throws IOException
     */
    public synchronized void send(byte[] command) throws IOException {
        outputStream.write(command);
    }

    /**
     * Send commands to the COM port as an integer
     *
     * @param command int
     * @throws IOException
     */
    public synchronized void send(int command) throws IOException {
        outputStream.write(command);
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        int type = event.getEventType();        
        switch (type) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:                
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            case SerialPortEvent.DATA_AVAILABLE:
                readBuffer = new byte[bufferSize];
                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(readBuffer);
                    }
                } catch (IOException e) {
                    port.close();
                }
                setChanged();
                notifyObservers(this.readBuffer);                
                
                break;
        }
    }

    /**
     * Register a port to a ComPort Object
     *
     * @param portName String
     * @throws javax.comm.NoSuchPortException -Port is not available.
     * @throws javax.comm.PortInUseException -Port is used by some other
     * application.
     * @throws java.io.IOException -Input/Output Stream error
     * @throws java.util.TooManyListenersException
     */
    private void register() throws PortInUseException, IOException, TooManyListenersException {
        String portName = this.portID.getName();
        port = (SerialPort) this.portID.open(portName, 2000);

        inputStream = this.port.getInputStream();

        outputStream = this.port.getOutputStream();

        this.port.addEventListener(this);

        this.port.notifyOnDataAvailable(true);
    }

    /**
     * @return the ComPort ID
     */
    public CommPortIdentifier getPortID() {
        return this.portID;
    }

    /**
     * Close the currently opened comport
     */
    public void close() {
        this.port.close();
    }

    /**
     * @return the bufferSize of the input stream buffer
     */
    public int getBufferSize() {
        return bufferSize;
    }

    /**
     * Set the size of the input stream buffer<br/>
     * If buffer size is not explicitly set Default buffer size will be used.
     *
     * @param bufferSize Custom bufferSize to set
     */
    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
