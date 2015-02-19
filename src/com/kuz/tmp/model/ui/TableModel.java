package com.kuz.tmp.model.ui;

import com.kuz.tmp.model.bean.Message;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Custom Table Model Implementation
 *
 * @author Kasun Amarasena
 */
public class TableModel extends AbstractTableModel {

    private List<Message> messageList;
    private List<String> columns;

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
            case 1:
                return message.getNumber();
            case 2:
                return message.getContents();
            case 3:
                return message.getSentDate();
            case 4:
                return message.getSentDate();
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

}
