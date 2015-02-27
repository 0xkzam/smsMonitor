package com.kuz.tmp.control.com_interface;

import java.io.IOException;
import java.util.List;

/**
 * Thread sending an AT command continuously to a specified COM port at a
 * specified time internal. Command is passed as a String(AT Commands)
 *
 * @author Kasun Amarasena
 */
public class ContinuousCommandSenderImpl extends ContinuousCommandSender {

    public ContinuousCommandSenderImpl() {
    }

    /**
     *
     * @param port ComPort
     * @param continuousCommand This is executed continuously after each time
     * interval
     */
    public ContinuousCommandSenderImpl(ComPort port, String continuousCommand) {
        super(port, continuousCommand);
    }

    /**
     *
     * @param port ComPort
     * @param continuousCommand At - This is executed continuously after each
     * time interval
     */
    public ContinuousCommandSenderImpl(ComPort port, At continuousCommand) {
        super(port, continuousCommand.toString());
    }

    /**
     *
     * @param port ComPort
     * @param continuousCommand This is executed continuously after each time
     * interval
     * @param preCommands List of commands, executed prior to the continuous
     * command
     */
    public ContinuousCommandSenderImpl(ComPort port, String continuousCommand, List<String> preCommands) {
        super(port, continuousCommand);
        this.setPreCommands(preCommands);
    }
    
        /**
     *
     * @param port ComPort
     * @param continuousCommand This is executed continuously after each time
     * interval
     * @param preCommands List of commands, executed prior to the continuous
     * command
     */
    public ContinuousCommandSenderImpl(ComPort port, At continuousCommand, List<String> preCommands) {
        super(port, continuousCommand.toString());
        this.setPreCommands(preCommands);
    }

    @Override
    public void run() {
        ComPort port = this.getPort();
        if (port != null) {

            List<String> preCommands = getPreCommands();
            if (preCommands != null || !preCommands.isEmpty()) {
                for (String preCommand : preCommands) {
                    try {
                        port.send(preCommand);
                        try {
                            Thread.sleep(this.getInterval());
                        } catch (InterruptedException ex) {
                        }
                    } catch (IOException ex) {
                    }
                }
            }

            while (isContinueOn()) {
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
