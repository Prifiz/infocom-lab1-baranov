/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import com.mycompany.maventasksscheduler.controller.NotificationController;
import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.BirthdayTask;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Сергей
 */
class Frame extends JFrame {

    /**
     * Creates new form MainFrame
     */
    public Frame() {
        super("Task scheduler");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {
        xml = new XMLStorage();
        logModel = xml.uploadData();

        dayOfWeek = new DayOfWeek();
        dayOfWeek.setFont(new Font("Algerian", 1, 20));
        clock = new DigitalClockLabel();
        clock.setFont(new Font("Algerian", 1, 20));
        submitButton = new JButton();
        addButton = new JButton();
        jScrollPane1 = new JScrollPane();
        jTabbedPane2 = new JTabbedPane();
        jScrollPane3 = new JScrollPane();
        birthdayTable = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                if (col > birthdayTable.getColumnCount() - 5) {
                    return true;//разрешает нажимать на кнопки и checkbox
                }
                boolean flag = false;
                if (birthdayTable.getColumnClass(4).equals(Boolean.class)) {
                    Boolean checkBox = (Boolean) birthdayTable.getValueAt(
                            row, 4);
                    if (checkBox == null || !checkBox) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
                return flag;
            }
        };
        jScrollPane5 = new JScrollPane();
        businessTable = new JTable() {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }

            @Override
            public boolean isCellEditable(int row, int col) {
                if (col > businessTable.getColumnCount() - 5) {
                    return true;//разрешает нажимать на кнопки и checkbox
                }
                boolean flag = false;
                if (businessTable.getColumnClass(4).equals(Boolean.class)) {
                    Boolean checkBox = (Boolean) businessTable.getValueAt(
                            row, 4);
                    if (checkBox == null || !checkBox) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
                return flag;
            }
        };

        jMenuBar1 = new JMenuBar();
        fileMenu = new JMenu();
        saveMenuItem = new JMenuItem();
        loadMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        manualMenuItem = new JMenuItem();
        aboutProgMenuItem = new JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(850, 430));

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed();
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed();
            }
        });

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane2.setPreferredSize(new java.awt.Dimension(100, 90));

        Vector<String> headerBirthdayVect = new Vector<String>();
        headerBirthdayVect.add("Contact's name");
        headerBirthdayVect.add("Date");
        headerBirthdayVect.add("Time");
        headerBirthdayVect.add("Priority");
        headerBirthdayVect.add("Edit");
        headerBirthdayVect.add("Detailed viewing");
        headerBirthdayVect.add("Detailed editing");
        headerBirthdayVect.add("Remove");
        modBirthdayTask = new DefaultTableModel(headerBirthdayVect, 0);


        birthdayTable.setModel(modBirthdayTask);

        birthdayTable.getColumn("Detailed viewing").setCellRenderer(
                new ButtonRenderer());
        birthdayTable.getColumn("Detailed viewing").setCellEditor(
                new ButtonEditor(new JCheckBox(), "Detailed viewing",
                birthdayTable, businessTable, jTabbedPane2, logModel));
        birthdayTable.getColumn("Detailed editing").setCellRenderer(
                new ButtonRenderer());
        birthdayTable.getColumn("Detailed editing").setCellEditor(
                new ButtonEditor(new JCheckBox(), "Detailed editing",
                birthdayTable, businessTable, jTabbedPane2, logModel));
        birthdayTable.getColumn("Remove").setCellRenderer(
                new ButtonRenderer());
        birthdayTable.getColumn("Remove").setCellEditor(
                new ButtonEditor(new JCheckBox(), "Remove", modBirthdayTask,
                birthdayTable, businessTable, jTabbedPane2, logModel));

        birthdayTable.getTableHeader().setReorderingAllowed(false);

        jScrollPane3.setViewportView(birthdayTable);
        birthdayTable.setRowHeight(23);

        jTabbedPane2.addTab("birthdays", jScrollPane3);

        Vector<String> headerBusinessVect = new Vector<String>();
        headerBusinessVect.add("Task's name");
        headerBusinessVect.add("Date");
        headerBusinessVect.add("Time");
        headerBusinessVect.add("Priority");
        headerBusinessVect.add("Edit");
        headerBusinessVect.add("Detailed viewing");
        headerBusinessVect.add("Detailed editing");
        headerBusinessVect.add("Remove");
        modBusinessTask = new DefaultTableModel(headerBusinessVect, 0);

        businessTable.setModel(modBusinessTask);

        businessTable.getColumn("Detailed viewing").setCellRenderer(
                new ButtonRenderer());
        businessTable.getColumn("Detailed viewing").setCellEditor(
                new ButtonEditor(new JCheckBox(), "Detailed viewing",
                birthdayTable, businessTable, jTabbedPane2, logModel));
        businessTable.getColumn("Detailed editing").setCellRenderer(
                new ButtonRenderer());
        businessTable.getColumn("Detailed editing").setCellEditor(
                new ButtonEditor(new JCheckBox(), "Detailed editing",
                birthdayTable, businessTable, jTabbedPane2, logModel));
        businessTable.getColumn("Remove").setCellRenderer(
                new ButtonRenderer());
        businessTable.getColumn("Remove").setCellEditor(
                new ButtonEditor(new JCheckBox(), "Remove", modBusinessTask,
                birthdayTable, businessTable, jTabbedPane2, logModel));

        businessTable.getTableHeader().setReorderingAllowed(false);
        businessTable.setRowHeight(23);
        jScrollPane5.setViewportView(businessTable);

        jTabbedPane2.addTab("business tasks", jScrollPane5);

        jScrollPane1.setViewportView(jTabbedPane2);

        initializeTables();

        fileMenu.setText("File");

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        loadMenuItem.setText("Load");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

        manualMenuItem.setText("Manual");
        manualMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(manualMenuItem);

        aboutProgMenuItem.setText("About program");
        aboutProgMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutProgMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                .addGap(0, 459, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(submitButton, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(clock, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(dayOfWeek, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(clock)
                .addComponent(dayOfWeek)
                .addGap(15, 15, 15)
                .addComponent(addButton)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(submitButton)
                .addContainerGap(64, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>   

    private void initializeTables() {
        for (int i = 0; i < logModel.getSize(); i++) {
            if (logModel.get(i) instanceof BirthdayTask) {
                initializeBirthdaysTable(logModel.get(i));
            } else {
                initializeBusinessTable(logModel.get(i));
            }
        }
    }

    private void initializeBirthdaysTable(Task birthday) {
        Vector<String> newRow = new Vector<String>();
        newRow.add(birthday.getContact().toString());
        newRow.add(birthday.getDate().getDayOfMonth() + "."
                + birthday.getDate().getMonthOfYear() + "."
                + birthday.getDate().getYear());
        newRow.add(birthday.getDate().getHourOfDay() + ":"
                + birthday.getDate().getMinuteOfHour());
        newRow.add(birthday.getPriority().toString());
        modBirthdayTask.addRow(newRow);
    }

    private void initializeBusinessTable(Task business) {
        Vector<String> newRow = new Vector<String>();
        newRow.add(((BusinessTask) business).getTaskName().toString());
        newRow.add(business.getDate().getDayOfMonth() + "."
                + business.getDate().getMonthOfYear() + "."
                + business.getDate().getYear());
        newRow.add(business.getDate().getHourOfDay() + ":"
                + business.getDate().getMinuteOfHour());
        newRow.add(business.getPriority().toString());
        modBusinessTask.addRow(newRow);
    }

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        Runnable saveData = new Runnable() {
            public void run() {
                xml.saveData(logModel);
            }
        };
        SwingUtilities.invokeLater(saveData);
    }

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        Runnable uploadData = new Runnable() {
            public void run() {
                int taskCount = modBirthdayTask.getRowCount();
                for (int i = 0; i < taskCount; i++) {
                    modBirthdayTask.removeRow(0);
                }
                taskCount = modBusinessTask.getRowCount();
                for (int i = 0; i < taskCount; i++) {
                    modBusinessTask.removeRow(0);
                }
                logModel = xml.uploadData();
                initializeTables();
            }
        };
        SwingUtilities.invokeLater(uploadData);
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void manualMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        new JFrame("manual").setVisible(true);
        // TODO add your handling code here:
    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        new JFrame("about program").setVisible(true);
        // TODO add your handling code here:
    }

    private void addButtonActionPerformed() {
        Runnable addRow = new Runnable() {
            public void run() {
                if (jTabbedPane2.getSelectedIndex() == 0) {
                    Vector<String> newRow = new Vector<String>();
                    modBirthdayTask.addRow(newRow);
                    logModel.add(modBirthdayTask.getRowCount() - 1, new BirthdayTask());
                } else if (jTabbedPane2.getSelectedIndex() == 1) {
                    Vector<String> newRow = new Vector<String>();
                    modBusinessTask.addRow(newRow);
                    logModel.add(new BusinessTask());
                }
            }
        };
        SwingUtilities.invokeLater(addRow);
    }

    private void submitButtonActionPerformed() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }
    private DefaultTableModel modBirthdayTask;
    private DefaultTableModel modBusinessTask;
    private JButton submitButton;
    private JButton addButton;
    private DayOfWeek dayOfWeek;
    private DigitalClockLabel clock;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuBar jMenuBar1;
    private JMenuItem manualMenuItem;
    private JMenuItem aboutProgMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem exitMenuItem;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane5;
    private JTabbedPane jTabbedPane2;
    private JTable birthdayTable;
    private JTable businessTable;
    private LogImpl logModel;
    private XMLStorage xml;
    private NotificationController notificationController;
    // End of variables declaration                   
}

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

/**
 * @version 1.0 11/09/98
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
                                            + businessTable.getSelectedRow() - 1);
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

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}