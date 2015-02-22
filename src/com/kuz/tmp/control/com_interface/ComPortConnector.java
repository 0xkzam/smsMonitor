package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;

/**
 * This class acts as the interface between native Com ports and the Client.
 * This is a Singleton and uses Flyweight pattern to store ComPort instances and
 * reuse them.
 *
 * @author Kasun Amarasena
 */
public final class ComPortConnector {

    //Map of connected ports
    private HashMap<String, ComPort> ports = new HashMap<>();

    private ComPortConnector() {
    }

    /**
     * ComPortConnector Singleton holder
     */
    private static class ComPortConnectorHolder {

        private static final ComPortConnector connector = new ComPortConnector();
    }

    /**
     *
     * @return ComPortConnector Instance
     */
    public static ComPortConnector getInstance() {
        return ComPortConnectorHolder.connector;
    }

    /**
     * Connect to a com port, if a connection already already exists returns the
     * existing instance
     *
     * @param portID CommPortIdentifier
     * @return ComPort instance associated with the PortID
     *
     * @throws NoSuchPortException
     * @throws IOException
     * @throws PortInUseException
     * @throws TooManyListenersException
     */
    public ComPort connectTo(CommPortIdentifier portID) throws NoSuchPortException, IOException, PortInUseException, TooManyListenersException {
        synchronized (portID) {
            boolean contains = ports.containsKey(portID.getName());
            if (!contains) {
                ComPort port = new ComPort(portID);
                ports.put(portID.getName(), port);
                return port;
            } else if (contains) {
                return ports.get(portID.getName());
            }
        }
        return null;
    }

    /**
     * Connect to a com port, if a connection already already exists returns the
     * existing instance
     *
     * @param portID CommPortIdentifier
     * @return ComPort instance associated with the PortID
     *
     * @throws NoSuchPortException
     * @throws IOException
     * @throws PortInUseException
     * @throws TooManyListenersException
     */
    public ComPort connectTo(String portID) throws NoSuchPortException, IOException, PortInUseException, TooManyListenersException {
        return connectTo(CommPortIdentifier.getPortIdentifier(portID));
    }

    public void send(CommPortIdentifier portID, String command) throws IOException {
        synchronized (portID) {
            this.ports.get(portID.getName()).send(command);
        }
    }

    /**
     * @return List<String> of available COM port identifiers as Strings
     */
    public List<String> getAvailableComports() {

        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        List<String> ls = new ArrayList<>(4);

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                ls.add(portId.getName());
            }
        }
        return ls;
    }
}
