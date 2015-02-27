package com.kuz.tmp.control;

import java.util.List;

public interface Controller {      

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
    void updatePortStatus();

}
