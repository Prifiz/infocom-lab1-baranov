/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {

        jLabel1 = new JLabel();
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
                        return Integer.class;
                    case 3:
                        return Double.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
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
                        return Integer.class;
                    case 3:
                        return Double.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
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

        jLabel1.setText("Today - monday. 28 june");

        submitButton.setText("Submit");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        birthdayTable.getTableHeader().setReorderingAllowed(false);
        
        jScrollPane3.setViewportView(birthdayTable);

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
        businessTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(businessTable);

        jTabbedPane2.addTab("business tasks", jScrollPane5);

        jScrollPane1.setViewportView(jTabbedPane2);

        fileMenu.setText("File");

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        loadMenuItem.setText("Load");
        fileMenu.add(loadMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

        manualMenuItem.setText("Manual");
        manualMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        helpMenu.add(manualMenuItem);

        aboutProgMenuItem.setText("About program");
        aboutProgMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
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
                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(addButton)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(submitButton)
                .addContainerGap(64, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>                        

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTabbedPane2.getSelectedIndex() == 0) {
            Vector<String> newRow = new Vector<String>();
            modBirthdayTask.addRow(newRow);
        }
        if (jTabbedPane2.getSelectedIndex() == 1) {
            Vector<String> newRow = new Vector<String>();
            modBusinessTask.addRow(newRow);
        }
        // TODO add your handling code here:
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
    private javax.swing.JButton submitButton;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem manualMenuItem;
    private javax.swing.JMenuItem aboutProgMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable birthdayTable;
    private javax.swing.JTable businessTable;
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

    protected JButton button;
    private String label;
    private boolean isPushed;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            // 
            // 
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
            // System.out.println(label + ": Ouch!");
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