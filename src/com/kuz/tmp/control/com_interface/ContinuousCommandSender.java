package com.kuz.tmp.control.com_interface;

import java.util.List;

/**
 * Thread sending the AT commands continuously to a specified COM port in a
 * specified time period. Command can be in the form of a String, byte array or
 * integer. This is thread-safe by default since <code>send()</code> method in
 * ComPort class in synchronized. If the interval between 2 commands are not
 * specified, a default value of 1000 milliseconds will be used.
 *
 * @author Kasun Amarasena
 */
public abstract class ContinuousCommandSender extends Thread {

    public static final long DEFAULT_INTERVAL = 1000L;
    private ComPort port;
    private String commandString;
    private byte[] commandByte;
    private int commandInt;
    private long interval = DEFAULT_INTERVAL;
    private List<String> preCommands;    
    private volatile boolean continueOn = true;

    public ContinuousCommandSender() {
    }

    public ContinuousCommandSender(ComPort port, String commandString) {
        this.port = port;
        this.commandString = commandString;
    }

    public ContinuousCommandSender(ComPort port, byte[] commandByte) {
        this.port = port;
        this.commandByte = commandByte;
    }

    public ContinuousCommandSender(ComPort port, int commandInt) {
        this.port = port;
        this.commandInt = commandInt;
    }

    @Override
    public abstract void run();

    public ComPort getPort() {
        return port;
    }

    public void setPort(ComPort port) {
        this.port = port;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommand(String commandString) {
        this.commandString = commandString;
    }

    public byte[] getCommand() {
        return commandByte;
    }

    public void setCommand(byte[] commandByte) {
        this.commandByte = commandByte;
    }

    public int getCommandInt() {
        return commandInt;
    }

    public void setCommand(int commandInt) {
        this.commandInt = commandInt;
    }

    public boolean isContinueOn() {
        return continueOn;
    }

    /**
     * Used to stop this thread
     *
     * @param continueOn Set false if the thread needs to be stopped, setting
     * true can be used on an already instantiated but stopped Sender, usually a
     * cached one, otherwise setting true on a new sender is useless.
     */
    public void setContinueOn(boolean continueOn) {
        this.continueOn = continueOn;
    }

    public List<String> getPreCommands() {
        return preCommands;
    }

    public void setPreCommands(List<String> preCommands) {
        this.preCommands = preCommands;
    }   

}
