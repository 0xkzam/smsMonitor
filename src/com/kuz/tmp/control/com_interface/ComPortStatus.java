package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortStatus implements Comparable<ComPortStatus> {

    private String portName;
    private String Status;

    public ComPortStatus() {
    }

    public ComPortStatus(String portName, String Status) {
        this.portName = portName;
        this.Status = Status;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public int compareTo(ComPortStatus o) {
        return this.getPortName().compareTo(o.getPortName());
    }

}
