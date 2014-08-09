/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.viewer;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Utility class for coloring a row of a JTable
 *
 * @author Kasun Amarasena
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    private Color color;
    private List<Integer> rows;

    MyTableCellRenderer(List<Integer> ls, Color color) {
        this.color = color;
        rows = ls;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground(null);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (rows.contains(row)) {
            setBackground(color);
            setForeground(Color.BLACK);
        }
        return this;
    }
}
