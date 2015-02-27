package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;

/**
 *
 * @author Kasun Amarasena
 */
public class Worker extends SwingWorker<Void, Message> implements Observer {

    private volatile boolean finished = false;
    private String portName;
    private ComPort port;
    private Logger logger = Logger.getLogger(Worker.class);

    public Worker(String portName) {
        this.portName = portName;
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected Void doInBackground() throws InterruptedException {
        try {
            System.out.println("Worker started");
            port = new ComPort(portName);
            port.addObserver(this);
            port.send(At.CHECK);
            port.send(At.SET_TEXT_MODE);
            int count = 0;
            while (true) {
                port.send(At.READ_ALL);
                while (!finished); 
                Thread.sleep(3000L);
                finished = false;
                System.out.println("phase "+(count++)+" finished");
            }
            //return null;
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            logger.error("", ex);
        }
        return null;

    }

    @Override
    protected void done() {
        port.close();
        port.deleteObserver(this);
    }

    @Override
    protected void process(List<Message> chunks) {
        for (Message message : chunks) {
            System.out.println(message);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String simData = new String((byte[]) arg);

        if (simData.indexOf("OK") != -1) {
            List<Message> list = Util.process(simData);
            if (list != null) {           
                for (Message message : list) {
                    publish(message);
                }
            }
        } else if (simData.indexOf("ERROR") != -1) {
            logger.error("AT ERROR!");
        }
        finished = true;
    }

}
