package com.kuz.tmp.control;

import java.util.List;

public interface Controller {

    //AT commands
    String AT_CHECK = "AT\r\n";
    String AT_SET_TEXT_MODE = "AT+CMGF=1\r\n";
    String AT_READ_ALL = "AT+CMGL=\"ALL\"\r\n";
    String AT_READ_UNREAD = "AT+CMGL=\"REC UNREAD\"\r\n";
    String AT_AVAILABLE_COMMANDS = "AT+CLAC\r\n";

    //Status
    String IN_USE = "In use";
    String IN_USE_OTHER = "In use by third party";
    String NOT_IN_USE = "Not in use";
    String CONNECTION_ERROR = "Unable to check";

    /**
     * This method is used to connect to a specified ComPort and start
     * ConcurrentCommandSender to send commands continously to the SIM card to
     * read SIM data through the ComPort. Each instance of
     * ConcurrentCommandSender is cached and can be reused(Flyweight pattern)
     *
     * @param portName String
     * @return false if the Port is already in use or if there is no such Port
     */
    boolean connectTo(String portName);

    /**
     * Stop the ContinuousCommandSender of the specified ComPort
     *
     * @param portName String comport name
     */
    void disconnect(String portName);

    /**
     *
     * @return All the OS registered Com ports
     */
    List<String> getAvailableComPorts();

    /**
     * Push status of all com ports to the UI
     */
    void updateStatus();

}
