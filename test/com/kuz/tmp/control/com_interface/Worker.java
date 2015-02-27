package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
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
    protected Void doInBackground() throws Exception {
        
        port = ComPortConnector.getInstance().connectTo(this.portName);
        port.addObserver(this);
        port.send(At.CHECK);
        port.send(At.SET_TEXT_MODE);
        port.send(At.READ_ALL);
        while(!finished);
        
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
                //update(list);
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
