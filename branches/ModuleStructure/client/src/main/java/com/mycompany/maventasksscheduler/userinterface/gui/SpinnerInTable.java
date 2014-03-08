/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Сергей
 */
/**
 * @version 1.0 11/09/98
 */
class SpinnerInTable extends AbstractCellEditor implements TableCellEditor {

    //остаётся прицепить дату и время, которые загружаются из файла
    private JSpinner spinner;
    private int clickCountToStart;
    private Date now;
    private String columnName;

    protected SpinnerInTable(String columnName) {
        clickCountToStart = 2;
        now = new Date();
        this.columnName = columnName;
        SpinnerDateModel sdm = new SpinnerDateModel(now, null, null,
                Calendar.DAY_OF_MONTH);
        spinner = new JSpinner(sdm);
        if (columnName.equals("Time")) {
            spinner.setEditor(new JSpinner.DateEditor(spinner, "HH:mm"));
        } else if (columnName.equals("Date")) {
            spinner.setEditor(new JSpinner.DateEditor(spinner, "dd.MM.yyyy"));
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        // System.out.println(value.toString());
        // spinner.setValue(now); //парисить value, доставать дату и время
        return spinner;
    }

    public Object getCellEditorValue() {
        String s = spinner.getValue().toString();
        String[] ss;
        ss = s.split(" ");
        if (columnName.equals("Time")) {
            return ss[3];
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(ss[2]).append(".").append(ss[1]).append(".").append(ss[5]);
            return sb.toString();
        }
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
        }
        return true;
    }
}
