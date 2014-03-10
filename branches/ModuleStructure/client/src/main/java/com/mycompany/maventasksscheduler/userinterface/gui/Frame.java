/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import com.mycompany.maventasksscheduler.logmodel.BirthdayTask;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
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
import javax.swing.table.DefaultTableModel;
import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import static java.awt.SystemTray.getSystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import javax.swing.JComboBox;

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
        tray();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents() {
        xml = new XMLStorage();
        logModel = xml.uploadData("my tasks");
        notification = new SystemNotification(logModel);
        dayOfWeek = new DayOfWeek();
        dayOfWeek.setFont(new Font("Algerian", 1, 20));
        clock = new DigitalClockLabel();
        clock.setFont(new Font("Algerian", 1, 20));
        submitButton = new JButton();
        addButton = new JButton();
        addFlag = new MyFlag();
        connectWithServerButton = new JButton();
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
                        return Object.class;
                    case 3:
                        return Integer.class;
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
                        return Integer.class;
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

        connectWithServerButton.setText("Connect with server");
        connectWithServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                connectWithServerButtonActionPerformed();
            }
        });

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                //connectWithServer();
                notification.run();
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

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("URGENT_IMPORTANT");
        comboBox.addItem("URGENT");
        comboBox.addItem("IMPORTANT");
        birthdayTable.getColumn("Priority").setCellEditor(
                new DefaultCellEditor(comboBox));


        birthdayTable.getColumn("Time").setCellEditor(new SpinnerInTable("Time"));
        birthdayTable.getColumn("Date").setCellEditor(new SpinnerInTable("Date"));

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

        businessTable.getColumn("Priority").setCellEditor(
                new DefaultCellEditor(comboBox));

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

        //connectWithServer();
        initializeTables(); // инициализация таблицы

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
                .addComponent(connectWithServerButton, javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addGap(15, 15, 15)
                .addComponent(connectWithServerButton)
                .addContainerGap(64, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>   

    private void connectWithServer() {
        try {
            String login = JOptionPane.showInputDialog(null,
                    "Enter your login", "Connect with server",
                    JOptionPane.OK_CANCEL_OPTION);
            while (login.equals("")) {
                login = JOptionPane.showInputDialog(null,
                        "Enter correctly your login",
                        "Connect with server",
                        JOptionPane.OK_CANCEL_OPTION);
                if (login == null) {
                    break;
                }
            }
            if (login != null) {
                Socket s = new Socket(InetAddress.getLocalHost(), 8189);
                s.setSoTimeout(5000);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(
                            s.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(
                            s.getInputStream());
                    oos.writeObject(login);
                    oos.flush();
                    try {
                        Object o;
                        o = ois.readObject();
                        if (o instanceof Boolean && (Boolean) o.equals(false)) {
                            new UserNotExist("user with login - " + login
                                    + ", does not exist").setVisible(true);
                        } else {
                            oos.writeObject(lastModified());
                            oos.writeObject(logModel);
                            oos.flush();
                            try {
                                o = ois.readObject();
                                if (o instanceof LogImpl) {
                                    int taskCount = modBirthdayTask.getRowCount();
                                    for (int i = 0; i < taskCount; i++) {
                                        modBirthdayTask.removeRow(0);
                                    }
                                    taskCount = modBusinessTask.getRowCount();
                                    for (int i = 0; i < taskCount; i++) {
                                        modBusinessTask.removeRow(0);
                                    }
                                    while (logModel.getSize() > 0) {
                                        logModel.removeAll();
                                    }
                                    LogImpl log = (LogImpl) o;
                                    for (int i = 0; i < log.getSize(); i++) {
                                        logModel.add(log.get(i));
                                    }
                                    notification.addTaskForNotification(logModel);
                                    initializeTables();
                                    new LogsSynchronized().setVisible(true);
                                }
                            } catch (ClassNotFoundException ex) {
                                //Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        // Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } finally {
                    s.close();
                }
            }
        } catch (ConnectException e) {
            new ErrorConnectFrame("The server is switched off").setVisible(true);
        } catch (IOException e) {
            new ErrorConnectFrame("The server doesn't respond").setVisible(true);
        }

    }

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
        newRow.add(birthday.getContact().getName());
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
        newRow.add(((BusinessTask) business).getTaskName());
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
                xml.saveData(logModel, "my tasks\\");
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
                while (logModel.getSize() > 0) {
                    logModel.removeAll();
                }
                LogImpl log = xml.uploadData("my tasks");
                for (int i = 0; i < log.getSize(); i++) {
                    logModel.add(log.get(i));
                }
                notification.addTaskForNotification(logModel);
                initializeTables();
            }
        };
        SwingUtilities.invokeLater(uploadData);
    }

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        xml.saveData(logModel, "my tasks\\");
        System.exit(0);
    }

    private void manualMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        new ManualFrame().setVisible(true);
        // TODO add your handling code here:
    }

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        new AboutProgramFrame().setVisible(true);
        // TODO add your handling code here:
    }

    private void addButtonActionPerformed() {
        Runnable addRow = new Runnable() {
            public void run() {
                Task task;
                if (jTabbedPane2.getSelectedIndex() == 0) {
                    task = new BirthdayTask();
                    new AddBirthdayTask(addFlag, task).setVisible(true);
                    if (addFlag.getFlag()) {
                        Vector<String> newRow = new Vector<String>();
                        newRow.add(task.getContact().getName());
                        newRow.add(task.getDate().getDayOfMonth() + "."
                                + task.getDate().getMonthOfYear() + "."
                                + task.getDate().getYear());
                        newRow.add(task.getDate().getHourOfDay() + ":"
                                + task.getDate().getMinuteOfHour());
                        newRow.add(task.getPriority().toString());
                        modBirthdayTask.addRow(newRow);
                        logModel.add(modBirthdayTask.getRowCount() - 1,
                                task);
                        addFlag.setFlag(false);
                    }
                } else if (jTabbedPane2.getSelectedIndex() == 1) {
                    task = new BusinessTask();
                    new AddBusinessTask(addFlag, task).setVisible(true);
                    if (addFlag.getFlag()) {
                        Vector<String> newRow = new Vector<String>();
                        newRow.add(((BusinessTask) task).getTaskName());
                        newRow.add(task.getDate().getDayOfMonth() + "."
                                + task.getDate().getMonthOfYear() + "."
                                + task.getDate().getYear());
                        newRow.add(task.getDate().getHourOfDay() + ":"
                                + task.getDate().getMinuteOfHour());
                        newRow.add(task.getPriority().toString());
                        modBusinessTask.addRow(newRow);
                        logModel.add(task);
                        addFlag.setFlag(false);
                    }
                }
                //revalidate();
            }
        };
        SwingUtilities.invokeLater(addRow);
    }

    private void submitButtonActionPerformed() {
    }

    private void connectWithServerButtonActionPerformed() {
        connectWithServer();
    }

    private Date lastModified() {
        File file1 = new File("my tasks\\birthdays\\birthdayTasks.xml");
        File file2 = new File("my tasks\\business\\businessTasks.xml");
        Date date1 = new Date(file1.lastModified());
        Date date2 = new Date(file2.lastModified());
        if (date1.compareTo(date2) >= 0) {
            return date1;
        } else {
            return date2;
        }
    }

    private void tray() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("123.jpg");
        setIconImage(image);
        if (SystemTray.isSupported()) {
            PopupMenu menu = new PopupMenu();
            MenuItem messageItem = new MenuItem("Show Message");
            messageItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Task scheduler with tray");
                }
            });
            menu.add(messageItem);

            MenuItem closeItem = new MenuItem("Exit");
            closeItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    xml.saveData(logModel, "my tasks\\");
                    System.exit(0);
                }
            });
            menu.add(closeItem);

            final TrayIcon icon = new TrayIcon(image, "Task scheduler", menu);
            icon.setImageAutoSize(true);
            icon.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Frame.this.setVisible(true);
                    Frame.this.setExtendedState(Frame.NORMAL);
                    getSystemTray().remove(icon);
                }
            });
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    Frame.this.setVisible(false);
                    try {
                        getSystemTray().add(icon);
                    } catch (AWTException e1) {
                        // e1.printStackTrace();
                    }
                }
            });
        }
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
            // java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private JButton connectWithServerButton;
    private JButton addButton;
    private MyFlag addFlag;
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
    private SystemNotification notification;
}

class MyFlag {

    private boolean flag;

    MyFlag() {
        flag = false;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}