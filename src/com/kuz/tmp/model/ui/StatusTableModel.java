 
package com.kuz.tmp.model.ui;

import com.kuz.tmp.model.bean.ComPortStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * Custom table model to show Comport status
 *
 * @author Kasun Amarasena
 */


public class StatusTableModel extends AbstractTableModel {

    private List<ComPortStatus> statusList = new ArrayList<>(5);
    private List<String> columns  = new ArrayList<>(4);
    

    public StatusTableModel() {    
        columns.add("Serial Port");
        columns.add("Status");            
    }    

    @Override
    public int getRowCount() {
        return statusList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > statusList.size()) {
            return null;
        }
        ComPortStatus status = statusList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return status.getPortName();
            case 1:
                return status.getStatus().toString();    
            case 2:
                return (status.getDeviceManufacturer()+" "+status.getDeviceModel());
            default:
                return null;
        }
    }


    public void addColumn(String columnName) {
        columns.add(columnName);
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col);
    }
    
    public void addAll(List<ComPortStatus> statusList){      
        this.statusList.clear();
        Collections.sort(statusList);
        this.statusList.addAll(statusList);
        fireTableDataChanged();
    } 
    
}
