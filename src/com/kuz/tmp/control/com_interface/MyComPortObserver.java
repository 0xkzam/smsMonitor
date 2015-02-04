
package com.kuz.tmp.control.com_interface;

import java.util.Observable;

/**
 *
 * @author Kasun Amarasena
 */
class MyComPortObserver extends ComPortObserver {

    @Override
    public void update(Observable o, byte[] inputBuffer) {
        String string = new String(inputBuffer);
        System.out.println("Observer: " + string);
    }

}
