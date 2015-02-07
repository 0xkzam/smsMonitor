
package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */
class ComPortObserverImpl extends ComPortObserver {

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String string = new String(inputBuffer);
        System.out.println("Observer: " + string);
    }

}
