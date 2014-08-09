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
package com.control.controller;

import com.control.helper.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import com.viewer.MessageDialogBox;

/**
 * This class is a SerialPortEventListener and a Runnable and used to Access a
 * COM port with Serial interface and register it, send AT commands through an
 * OutputStream. A SerialPortEventListener is used listen to the input of the
 * port through an InputStream.
 *
 * @author Kasun Amarasena
 */
final class ComPortReader implements SerialPortEventListener, Runnable {

    private CommPortIdentifier portID;
    private SerialPort port;
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean hasNext = true; // For Thread synchronization
    private String msgBuffer; // Message buffer
    //private ComPortReader comportReader;
    public static int count = 1; // testing .........
    private static String comport = "";
    private boolean error = false;

    /**
     * Returns an instance of the of ComPortReader class
     *
     * @param comPort Name of the Com port-ex: "COM4"
     * @return ComPortReader object
     * @throws NoSuchPortException
     * @throws PortInUseException
     * @throws IOException
     * @throws TooManyListenersException
     */
    public static ComPortReader getInstance(String comPort) throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {
        comport = comPort;
        //if (comportReader == null) {
        ComPortReader comportReader = new ComPortReader(comPort);
        //}
        return comportReader;
    }

    private ComPortReader(String comPort) throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {
        register(comPort);
    }

    /**
     * This method is used to access the received messages outside of this class
     *
     * @return the msgBuffer
     */
    public String getMsgBuffer() {
        return msgBuffer;
    }

    /**
     * Send AT commands through COM port to the dongle
     *
     * @param command String
     * @throws java.lang.InterruptedException
     */
    public synchronized void send(String command) throws InterruptedException {
        try {
            outputStream.write(command.getBytes());
            while (hasNext) {
                Thread.sleep(10);
            }
            hasNext = true;

        } catch (IOException ex) {
            port.close();
            Logger.printError(this.getClass().getName(), "send", ex.toString()); //logger
            error = true;
        }
    }

    @Override
    public void run() {
        try {
            //Logger.print("ComportReader.run(): testing A");
            this.send("AT\r\n"); /* AT check */

            //Logger.print("ComportReader.run(): testing B");
            this.send("AT+CMGF=1\r\n"); /*Set text mode*/

            while (true) {
                if (error) {
                    return;
                }
                //Logger.print("A: " + (count++));
                this.send("AT+CMGL=\"ALL\"\r\n"); /*Read ALL messages*/

                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            port.close();
            Logger.printError(this.getClass().getName(), "run ", e.toString()); //logger   
            return;
        }
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
                byte[] readBuffer = new byte[1000];
                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(readBuffer);
                    }
                } catch (IOException e) {
                    port.close();
                    Logger.printError(this.getClass().getName(), "serialEvent", e.toString()); //logger
                    MessageDialogBox.showUnknownError();
                }

                msgBuffer = new String(readBuffer);

                if (msgBuffer.indexOf("OK") != -1) {
                    hasNext = false;
                }
                if (msgBuffer.indexOf("ERROR") != -1) {
                    Logger.printError(this.getClass().getName(),"serialEvet","AT ERROR"); //logger
                }
                break;
        }
    }

    /**
     * Register a port to a ComPortReader Object
     *
     * @param portName String
     * @throws javax.comm.NoSuchPortException -Port is not available.
     * @throws javax.comm.PortInUseException -Port is used by some other
     * application.
     * @throws java.io.IOException -Input/Output Stream error
     * @throws java.util.TooManyListenersException
     */
    public void register(String portName) throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {
        portID = CommPortIdentifier.getPortIdentifier(portName);

        port = (SerialPort) portID.open(portName, 2000);

        inputStream = port.getInputStream();

        outputStream = port.getOutputStream();

        port.addEventListener(this);

        port.notifyOnDataAvailable(true);
    }

    /**
     * @return the hasNext
     */
    public boolean isHasNext() {
        return hasNext;
    }

    /**
     * @return the comport
     */
    public static String getComport() {
        return comport;
    }
}
