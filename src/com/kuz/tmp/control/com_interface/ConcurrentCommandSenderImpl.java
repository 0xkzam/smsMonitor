package com.kuz.tmp.control.com_interface;

import java.io.IOException;

/**
 * Thread sending the AT commands concurrently to a specified COM port. Command
 * is passed as a String(AT Commands)
 *
 * @author Kasun Amarasena
 */
public class ConcurrentCommandSenderImpl extends ConcurrentCommandSender {

    public ConcurrentCommandSenderImpl() {
    }

    public ConcurrentCommandSenderImpl(ComPort port, String command) {
        super(port, command);
    }

    @Override
    public void run() {
        ComPort port = this.getPort();
        if (port != null) {
            
            //Setting text mode
            try {
                port.send("AT+CMGF=1\r\n"); 
            } catch (IOException ex) {
            }

            while (true) {
                try {
                    port.send(this.getCommandString());
                } catch (IOException ex) {
                }

                try {
                    Thread.sleep(this.getInterval());
                } catch (InterruptedException ex) {
                }
            }

        }

    }

}
