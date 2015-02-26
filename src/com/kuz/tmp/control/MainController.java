package com.kuz.tmp.control;

import com.kuz.tmp.control.com_interface.ComPort;
import com.kuz.tmp.control.com_interface.ComPortConnector;
import com.kuz.tmp.control.com_interface.ComPortStatus;
import com.kuz.tmp.control.com_interface.ContinuousCommandSender;
import com.kuz.tmp.control.com_interface.ContinuousCommandSenderImpl;
import com.kuz.tmp.control.com_interface.MessageObserver;
import com.kuz.tmp.model.bean.Message;
import com.kuz.tmp.model.db.MessageDAO;
import com.kuz.tmp.view.MainUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import org.apache.log4j.Logger;

/**
 * Main Controller class
 *
 * @author Kasun Amarasena
 */
public class MainController implements Controller {

    private MainUI view;
    private MessageDAO model;
    private static final ComPortConnector portConnector = ComPortConnector.getInstance();
    private Map<String, ContinuousCommandSender> sendersMap = new HashMap<>();
    private final Logger logger = Logger.getLogger(MainController.class);
    private MessageObserver messageObserver = new MyMessageObserver();

    public MainController() {
    }

    public MainController(MainUI view, MessageDAO model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public List<String> getAvailableComPorts() {
        return portConnector.getAvailableComports();
    }

    /**
     * This method is used to connect to a specified ComPort and start
     * ConcurrentCommandSender to send commands continously to the SIM card to
     * read SIM data through the ComPort. Each instance of
     * ConcurrentCommandSender is cached and can be reused(Flyweight pattern)
     *
     * @param portName String
     * @return false if the Port is already in use or if there is no such Port
     */
    @Override
    public boolean connectTo(String portName) {
        if (!sendersMap.containsKey(portName)) {
            ComPort port;
            try {
                port = portConnector.connectTo(portName);
                port.addObserver(messageObserver);
            } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
                logger.error("NoSuchPort or PortInUse", ex);
                return false;
            }
            List<String> pre = new ArrayList<>(2);
            pre.add(AT_CHECK);
            pre.add(AT_SET_TEXT_MODE);
            ContinuousCommandSender sender = new ContinuousCommandSenderImpl(port, AT_READ_ALL, pre);
            sendersMap.put(portName, sender);
            sender.start();
            logger.info("ContinuousCommandSender for " + portName + " started.");
            return true;
        } else {
            return false;
        }        
    }

    /**
     * Stop the ContinuousCommandSender of the specified ComPort
     *
     * @param portName String comport name
     */
    @Override
    public void disconnect(String portName) {
        if (sendersMap.containsKey(portName)) {
            ContinuousCommandSender sender = sendersMap.get(portName);
            sender.setContinueOn(false);
            sendersMap.remove(portName);
            logger.info("ContinuousCommandSender for " + portName + " stopped.");
        }
    }

    /**
     * Implementing the MessageObserver - The incoming Messages, sorted by sent
     * date are pushed to the UI
     */
    private class MyMessageObserver extends MessageObserver {

        @Override
        public void update(List<Message> listOfMessages) {
            Collections.sort(listOfMessages);
            if (view != null) {
                view.addMessages(listOfMessages);
            }
        }
    }

    /**
     * Push status of all com ports to the UI
     */
    @Override
    public void updateStatus() {

        List<ComPortStatus> statusList = new ArrayList<>(portConnector.getAvailableComports().size());
        Set<String> connectedPorts = sendersMap.keySet();
        for (String portName : connectedPorts) {
            statusList.add(new ComPortStatus(portName, IN_USE));
        }

        Set<String> availablePorts = new HashSet<>(portConnector.getAvailableComports());
        availablePorts.removeAll(connectedPorts);
        for (String portName : availablePorts) {
            ComPort port = null;
            try {
                port = portConnector.connectTo(portName);
                statusList.add(new ComPortStatus(portName, NOT_IN_USE));
            } catch (PortInUseException ex) {
                statusList.add(new ComPortStatus(portName, IN_USE_OTHER));
            } catch (IOException | NoSuchPortException | TooManyListenersException ex) {
                statusList.add(new ComPortStatus(portName, CONNECTION_ERROR));
            } finally {
                if (port != null) {
                    port.close();
                }
            }
        }
        if (view != null) {
            view.updateStatus(statusList);
        }
    }

}
