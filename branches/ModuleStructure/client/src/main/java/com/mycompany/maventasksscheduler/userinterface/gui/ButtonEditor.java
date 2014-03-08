/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Сергей
 */
class ButtonEditor extends DefaultCellEditor {

    protected JButton buttonOk;
    private String label;
    private boolean isPushed;
    private String buttonName;
    private DefaultTableModel model;
    private JTable birthdayTable;
    private JTable businessTable;
    private JTabbedPane jTabbedPane;
    private LogImpl logModel;

    public ButtonEditor(JCheckBox checkBox, String buttonName,
            JTable birthdayTable, JTable businessTable, JTabbedPane jTabbedPane,
            LogImpl logModel) {
        super(checkBox);
        buttonOk = new JButton();
        buttonOk.setOpaque(true);
        this.buttonName = buttonName;
        this.model = new DefaultTableModel();
        this.birthdayTable = birthdayTable;
        this.businessTable = businessTable;
        this.logModel = logModel;
        this.jTabbedPane = jTabbedPane;
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public ButtonEditor(JCheckBox checkBox, String buttonName,
            DefaultTableModel model, JTable birthdayTable,
            JTable businessTable, JTabbedPane jTabbedPane, LogImpl logModel) {
        super(checkBox);
        buttonOk = new JButton();
        buttonOk.setOpaque(true);
        this.buttonName = buttonName;
        this.model = model;
        this.birthdayTable = birthdayTable;
        this.businessTable = businessTable;
        this.logModel = logModel;
        this.jTabbedPane = jTabbedPane;
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            buttonOk.setForeground(table.getSelectionForeground());
            buttonOk.setBackground(table.getSelectionBackground());
        } else {
            buttonOk.setForeground(table.getForeground());
            buttonOk.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        buttonOk.setText(label);
        isPushed = true;
        return buttonOk;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            BrowsingTaskDialog browsingFrame;
            EditingTaskDialog editingFrame;
            if (buttonName.equals("Detailed viewing")) {
                if (jTabbedPane.getSelectedIndex() == 0) {
                    browsingFrame = new BrowsingTaskDialog(
                            logModel.get(birthdayTable.getSelectedRow()));
                } else {
                    browsingFrame = new BrowsingTaskDialog(
                            logModel.get(birthdayTable.getRowCount()
                            + businessTable.getSelectedRow()));
                }
                browsingFrame.setVisible(true);
            } else if (buttonName.equals("Detailed editing")) {
                if (jTabbedPane.getSelectedIndex() == 0) {
                    editingFrame = new EditingTaskDialog(
                            logModel.get(birthdayTable.getSelectedRow()));
                } else {
                    editingFrame = new EditingTaskDialog(
                            logModel.get(birthdayTable.getRowCount()
                            + businessTable.getSelectedRow()));
                }
                editingFrame.setVisible(true);
            } else if (buttonName.equals("Remove")) {
                if (JOptionPane.showConfirmDialog(null,
                        "you want to remove this task?",
                        "Removing task",
                        JOptionPane.OK_CANCEL_OPTION) == 0) {
                    Runnable removeRow = new Runnable() {
                        public void run() {
                            if (jTabbedPane.getSelectedIndex() == 0) {
                                logModel.remove(birthdayTable.getSelectedRow());
                                model.removeRow(birthdayTable.getSelectedRow());
                            } else {
                                if (birthdayTable.getRowCount() > 0) {
                                    logModel.remove(birthdayTable.getRowCount()
                                            + businessTable.getSelectedRow());
                                } else {
                                    logModel.remove(businessTable.getSelectedRow());
                                }
                                model.removeRow(businessTable.getSelectedRow());
                            }
                        }
                    };
                    SwingUtilities.invokeLater(removeRow);
                }
            }
        }
        isPushed = false;
        return new String(label);
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
