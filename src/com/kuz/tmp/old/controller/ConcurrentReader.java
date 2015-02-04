
package com.kuz.tmp.old.controller;


import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.apache.log4j.Logger;


/**
 * This class is used to initiate the Port listening process and message reading
 * process
 *
 * @author Kasun Amarasena
 */

@Deprecated
final class ConcurrentReader {

    private String comPort;
    private Thread readerThread;
    private Thread writerThread;
    private final Logger logger = Logger.getLogger(ConcurrentReader.class); 

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
        logger.info("ConCurrentReader stopping............"); //logger        
        killReaderThread();
        killWriterThread();
    }
    
    private void killReaderThread(){
        logger.info("Killing reader thread............"); //logger
        readerThread.interrupt();        
    }
    
    private void killWriterThread(){
        logger.info("Killing writer thread............"); //logger    
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
        logger.info("Restarting ConCurrentReader process..."); //logger
        try {
            this.start();
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            logger.error("Restart failure...Connecting again....:",ex); //logger            
        }
    }
}
