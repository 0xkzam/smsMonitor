package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.ComPortStatus;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;

/**
 *
 * @author Kasun Amarasena
 */
public class WorkerMetaData extends SwingWorker<Void, ComPortStatus> implements Observer {

    private static final ComPortConnector portConnector = ComPortConnector.getInstance();
    private Logger logger = Logger.getLogger(WorkerMetaData.class);

    public WorkerMetaData() {

    }

    @Override
    @SuppressWarnings("empty-statement")
    protected Void doInBackground() throws Exception {
        List<String> ports = portConnector.getAvailableComports();
        for (String portName : ports) {
            ComPort port = portConnector.connectTo(portName);
            port.addObserver(this);
        }

        return null;
    }

    @Override
    protected void done() {

    }

    @Override
    protected void process(List<ComPortStatus> chunks) {
        for (ComPortStatus message : chunks) {
            System.out.println(message);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String simData = new String((byte[]) arg);

        if (simData.indexOf("OK") != -1) {

        } else if (simData.indexOf("ERROR") != -1) {
            logger.error("AT ERROR!");
        }

    }

}
