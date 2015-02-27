package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */
public enum PortStatus {

    IN_USE("In use"),
    IN_USE_OTHER("In use by third party"),
    NOT_IN_USE("Not in use"),
    CONNECTION_ERROR("Unable to check");

    private final String status;

    private PortStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
