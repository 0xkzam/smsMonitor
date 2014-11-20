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
package com.comport_interface;

import java.io.IOException;
import java.util.HashMap;
import java.util.TooManyListenersException;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;

/**
 * This class acts as the interface between ComPorts and the Client.
 *
 * @author Kasun Amarasena
 */
public final class ComPortConnector {

    //Map of connected ports
    private HashMap<String, ComPort> ports;

    private ComPortConnector() {
        ports = new HashMap<>();
    }

    /**
     * ComPortConnector singleton holder
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

    public void send(CommPortIdentifier portID, String command) throws IOException {
        synchronized (portID) {
            this.ports.get(portID.getName()).send(command);
        }
    }
}
