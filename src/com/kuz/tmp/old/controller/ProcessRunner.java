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
package com.kuz.tmp.old.controller;


import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import static com.kuz.tmp.old.view.MessageDialogBox.showErrorMessage;
import org.apache.log4j.Logger;

/**
 * This class is the initiator of ConCurrentReader class and provides methods
 * start() and stop() methods. Using this to initiate the whole process solves
 * the problem of dealing with Communication errors with the device.<br/>
 * ex: Starting other applications which uses the same port while this
 * application(initiated by the ConCurrentReader) is running class generate
 * InterruptedExceptions related to reader thread(MessageBufferReader) but
 * doesn't terminate the writer thread(ComPortReader).
 *
 * @author Kasun Amarasena
 */


@Deprecated
public final class ProcessRunner {

    private String comport;
    private Thread runner;
    private ConcurrentReader reader;
    private final Logger logger = Logger.getLogger(ProcessRunner.class);    

    public ProcessRunner(String comport) {
        this.comport = comport;
    }

    /**
     * Starts the main process and listens to Communication errors with device.
     * In case of communication errors, this will re-initiate the main process.
     */
    public void start() {

        reader = new ConcurrentReader(this.comport);
        try {
            reader.start();
        } catch (NoSuchPortException ex) {
            showErrorMessage("NO communication device is connected to " + this.comport + " !", "ERROR!");
            logger.error("NO communication device is connected to " + this.comport, ex); //logger
            return;
        } catch (PortInUseException ex) {
            showErrorMessage("Another program is using the selected port! \n"
                    + "Try reconnecting after the other program finishes.", "ERROR: Port in use!");
            logger.error("Another program is using the selected port: ",ex); //logger
            return;
        } catch (IOException | TooManyListenersException ex) {
            logger.error("start() method",ex); //logger
            return;
        }

        runner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        if (!reader.isReaderAlive() || !reader.isWriterAlive()) {
                            reader.stop();
                            logger.info("Reader & Writer Threads are DEAD!"); //logger
                            reader.restart();
                            logger.info("Restarted."); //logger
                        }
                    } catch (InterruptedException ex) {
                        logger.warn("Thread interrupted", ex);
                        return;
                    }
                }
            }
        });
        runner.start();        
    }

    /**
     * Stops the main process by terminating ProcessRunner.
     */
    public void stop() {
        logger.info("ProcessRunner stopping............");
        runner.interrupt();
        reader.stop();
    }   

}
