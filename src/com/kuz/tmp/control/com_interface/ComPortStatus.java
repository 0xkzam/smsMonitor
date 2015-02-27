package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortStatus implements Comparable<ComPortStatus> {

    private String portName;
    private String Status;
    private String deviceManufacturer;
    private String deviceModel;

    public ComPortStatus() {
    }

    public ComPortStatus(String portName, String Status) {
        this.portName = portName;
        this.Status = Status;
    }

    public ComPortStatus(String portName, String Status, String deviceManufacturer, String deviceModel) {
        this.portName = portName;
        this.Status = Status;
        this.deviceManufacturer = deviceManufacturer;
        this.deviceModel = deviceModel;
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

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

}
