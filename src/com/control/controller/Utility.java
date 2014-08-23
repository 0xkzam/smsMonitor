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
package com.control.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.comm.CommPortIdentifier;

/**
 *
 * @author Kasun Amarasena
 */
public class Utility {

    /**
     * @return List<String> of available COM port identifiers as Strings
     */
    public static List<String> getAvailableComports() {

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
