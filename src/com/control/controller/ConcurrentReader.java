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
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;


/**
 * This class is used to initiate the Port listening process and message reading
 * process
 *
 * @author Kasun Amarasena
 */
final class ConcurrentReader {

    private String comPort;
    private Thread readerThread;
    private Thread writerThread;

    public ConcurrentReader(String comport) {
        this.comPort = comport;
    }

    /**
     * This method starts the two threads responsible for reading messages
     * 1.Writer thread - Send AT commands,Read messages 2.Reader thread -
     * Listens to the string buffer in which received messages are stored.
     */
    public synchronized void start() throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {

        ComPortReader portReader = ComPortReader.getInstance(this.comPort);

        writerThread = new Thread(portReader);
        writerThread.setName("Writer Thread");
        MessageBufferReader msgReader = new MessageBufferReader(portReader, this);
        readerThread = new Thread(msgReader);
        readerThread.setName("Reader Thread");

        writerThread.setDaemon(true);
        readerThread.setDaemon(true);
        Object ob = new Object();
        synchronized (ob) {
            writerThread.start();
            readerThread.start();
        }
    }

    /**
     * Stop the Writer Thread and Reader Thread started by a ConCurrentReader
     * object. This method must be called when a communication error is
     * occurred.
     */
    public void stop() {
        Logger.printInfo("","","ConCurrentReader stopping............"); //logger        
        killReaderThread();
        killWriterThread();
    }
    
    private void killReaderThread(){
        Logger.printInfo("","","Killing reader thread............"); //logger
        readerThread.interrupt();        
    }
    
    private void killWriterThread(){
        Logger.printInfo("","","Killing writer thread............"); //logger    
        writerThread.interrupt();        
    }

    /**
     * Check whether the Reader Thread is alive.
     *
     * @return boolean true if alive false otherwise
     */
    public boolean isReaderAlive() {
        return readerThread.isAlive();
    }

    /**
     * Check whether the Writer Thread is alive.
     *
     * @return boolean true if alive false otherwise
     */
    public boolean isWriterAlive() {
        return writerThread.isAlive();
    }

    /**
     * Restarts the ConcurrentReader process
     */
    public void restart() {
        Logger.printInfo(this.getClass().getName(),"restart","Restarting ConCurrentReader process..."); //logger
        try {
            this.start();
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            Logger.printError(this.getClass().getName(),"restart","Restart failure...Connecting again....:"+ex); //logger            
        }
    }
}
