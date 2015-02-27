package com.kuz.tmp.control;

import com.kuz.tmp.control.com_interface.*;
import com.kuz.tmp.model.bean.Message;
import com.kuz.tmp.model.ui.MessageTableModel;
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
public class ReadingMessagesTask extends SwingWorker<Void, Message> implements Observer {

    private volatile boolean finished = false;
    private String portName;
    private ComPort port;
    private MessageTableModel messageTableModel;
    private Logger logger = Logger.getLogger(ReadingMessagesTask.class);

    public ReadingMessagesTask(String portName, MessageTableModel messageTableModel) {
        this.portName = portName;
        this.messageTableModel = messageTableModel;
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected Void doInBackground() {
        try {
            logger.info("ReadingMessageTask started for port "+portName);
            port = new ComPort(portName);
            port.addObserver(this);
            port.send(At.CHECK);
            port.send(At.SET_TEXT_MODE);
            int count = 0;
            while (true) {
                port.send(At.READ_ALL);
                while (!finished);
                System.out.println("phase "+(count++)+" finished");
                Thread.sleep(30000L);
            }            
        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException | InterruptedException ex) {
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
        if (messageTableModel != null) {
            messageTableModel.addAll(chunks);
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
