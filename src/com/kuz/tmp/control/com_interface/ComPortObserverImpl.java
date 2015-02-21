
package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */


public class ComPortObserverImpl  extends ComPortObserver{

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String simData = new String(inputBuffer);

        if (simData.indexOf("OK") != -1) {
            System.out.println(simData);
        } else if (simData.indexOf("ERROR") != -1) {
            
        }
    }    
}
