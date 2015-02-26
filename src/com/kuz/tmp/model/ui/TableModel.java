package com.kuz.tmp.model.ui;

import com.kuz.tmp.model.bean.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Custom Table Model Implementation with the capability to Observer Messages
 *
 * @author Kasun Amarasena
 */
public class TableModel extends AbstractTableModel{

    private List<Message> messageList = new ArrayList<>(5);
    private List<String> columns  = new ArrayList<>(4);
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yy/MM/dd");

    public TableModel() {    
        columns.add("Sender");
        columns.add("Contents");
        columns.add("Sent date & time");
        columns.add("Received date & time");        
    }
    
    public TableModel(List<Message> messageList) {
        this.messageList.addAll(messageList);
    }

    @Override
    public int getRowCount() {
        return messageList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex > messageList.size()) {
            return null;
        }
        Message message = messageList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return message.getNumber();
            case 1:
                return message.getContents();
            case 2:
                return dateFormat.format(message.getSentDate());
            case 3:
                return dateFormat.format(message.getReceivedDate());
            default:
                return null;
        }
    }

    public void addProduct(Message product) {
        messageList.add(product);
        fireTableDataChanged();
    }

    public void removeProductAt(int row) {
        messageList.remove(row);
        fireTableDataChanged();
    }

    public void addColumn(String columnName) {
        columns.add(columnName);
    }

    @Override
    public String getColumnName(int col) {
        return columns.get(col);
    }
    
    public void addAll(List<Message> listOfMessages){        
        messageList.addAll(0,listOfMessages);
        fireTableDataChanged();
    }  

}
