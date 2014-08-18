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
 * This class acts as the interface between ComPorts and the User.
 *
 * @author Kasun Amarasena
 */
public final class ComPortConnector {

    //Map of connected ports
    private HashMap<CommPortIdentifier, ComPort> ports;
    //Map of observers observing the connected ports
    private HashMap<CommPortIdentifier, ComPortObserver> observers;

    private ComPortConnector() {
        ports = new HashMap<>();
        observers = new HashMap<>();
    }

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

    public boolean connectTo(CommPortIdentifier portID) throws NoSuchPortException, PortInUseException, IOException, TooManyListenersException {
        boolean contains = ports.containsKey(portID);
        if (!contains) {
            ComPort port = new ComPort(portID);
            MyComPortObserver observer = new MyComPortObserver();
            port.addObserver(observer);
            ports.put(portID, port);
            observers.put(portID, observer);
            return true;
        }
        return false;
    }

    public void send(CommPortIdentifier id,String command) throws IOException {
        this.ports.get(id).send(command);
    }
}
