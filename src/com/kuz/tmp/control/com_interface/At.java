package com.kuz.tmp.control.com_interface;

/**
 * AT commands
 * 
 * @author Kasun Amarasena
 */
public enum At {

    CHECK("AT\r\n"),
    SET_TEXT_MODE("AT+CMGF=1\r\n"),
    READ_ALL("AT+CMGL=\"ALL\"\r\n"),
    READ_UNREAD("AT+CMGL=\"REC UNREAD\"\r\n"),
    AVAILABLE_COMMANDS("AT+CLAC\r\n"),
    SERVICE_CENTER("AT+CSCA?\r\n"),
    MANUFACTURER_NAME("AT+CGMI\r\n"),
    MANUFACTURER_ID("AT+GMI\r\n"),
    DEVICE_MODEL("AT+CGMM\r\n"),
    /**
     * IMEI number (International Mobile Equipment Identity)
     */
    IMEI("AT+CGSN\r\n"),
    /**
     * IMSI number (International Mobile Subscriber Identity)
     */
    IMSI("AT+CIMI\r\n");

    private final String command;

    private At(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return this.command;
    }

}
