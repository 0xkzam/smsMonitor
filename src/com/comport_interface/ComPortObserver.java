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

import java.util.Observable;
import java.util.Observer;

/**
 * This abstract class represents an Observer for a ComPort object and should 
 * be sub-classed and override update method to implement a port observer for
 * specific port. This is same as implementing Observer interface except for 
 * convenience second parameter of update method is casted to a ComPort type. 
 *
 * @author Kasun Amarasena
 */
public abstract class ComPortObserver implements Observer {

    /**
     * This method is called whenever the observed object is changed.
     *
     * @param o the observable object
     * @param inputBuffer an argument passed to the <code>notifyObservers</code>
     * method which is an input buffer of bytes from the comport
     */
    @Override
    public void update(Observable o, Object inputBuffer) {
        if (inputBuffer instanceof byte[]) {
            update(o, (byte[]) inputBuffer);
        }
    }

    /**
     * This method is called whenever the observed object is changed.
     *
     * @param o the observable object
     * @param inputBuffer ComPort Object
     */
    public abstract void update(Observable o, byte[] inputBuffer);
}
