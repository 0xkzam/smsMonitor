package com.kuz.tmp.control.com_interface;

import org.apache.log4j.Logger;

/**
 *
 * @author Kasun Amarasena
 */
class ComPortObserverImpl extends ComPortObserver {

    private Logger logger = Logger.getLogger(ComPortObserverImpl.class);

    @Override
    public void update(ComPort port, byte[] inputBuffer) {
        String info = new String(inputBuffer);

        if (info.indexOf("OK") != -1) {

        }
        if (info.indexOf("ERROR") != -1) {
            logger.error("AT ERROR!");
        }
    }

}
