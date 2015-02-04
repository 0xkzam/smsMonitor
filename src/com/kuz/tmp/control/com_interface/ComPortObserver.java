
package com.kuz.tmp.control.com_interface;

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
     * @param inputBuffer buffer of bytes from the comport
     */
    public abstract void update(Observable o, byte[] inputBuffer);
}
