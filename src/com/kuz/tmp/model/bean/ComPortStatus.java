package com.kuz.tmp.model.bean;

import com.kuz.tmp.control.com_interface.PortStatus;
import java.util.Objects;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortStatus implements Comparable<ComPortStatus> {

    private String portName;
    private PortStatus status;
    private String deviceManufacturer;
    private String deviceModel;

    public ComPortStatus() {
    }

    public ComPortStatus(String portName, PortStatus Status) {
        this.portName = portName;
        this.status = Status;
    }

    public ComPortStatus(String portName, PortStatus status, String deviceManufacturer, String deviceModel) {
        this.portName = portName;
        this.status = status;
        this.deviceManufacturer = deviceManufacturer;
        this.deviceModel = deviceModel;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public PortStatus getStatus() {
        return status;
    }

    public void setStatus(PortStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "ComPortStatus{portName=" + portName + ", status=" + status + ", device=" + deviceManufacturer + " " + deviceModel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.portName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComPortStatus other = (ComPortStatus) obj;
        if (!Objects.equals(this.portName, other.portName)) {
            return false;
        }
        return true;
    }

}
