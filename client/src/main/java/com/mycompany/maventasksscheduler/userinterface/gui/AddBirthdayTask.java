/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import com.mycompany.maventasksscheduler.logmodel.Task;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class AddBirthdayTask extends JDialog {

    private JLabel contactNameLabel;
    private JLabel contactPhoneLabel;
    private JLabel contactEMailLabel;
    private JLabel dateNotif;
    private JLabel timeNotif;
    private JLabel priority;
    private JTextField contactNameTextField;
    private JTextField contactPhoneTextField;
    private JTextField contactEMailTextField;
    private Date now;
    private JSpinner spinnerDate;
    private JSpinner spinnerTime;
    private JButton okButton;
    private JButton cancelButton;
    private MyFlag addFlag;
    private JComboBox comboBox;

    public AddBirthdayTask(MyFlag addFlag, final Task task) {
        super(new JFrame(), "Add birthday task", true);
        setPreferredSize(new Dimension(350, 320));
        setResizable(false);
        this.addFlag = addFlag;
        now = new Date();
        contactNameLabel = new JLabel("Enter contact's name: ");
        contactPhoneLabel = new JLabel("Enter contact's phone number: ");
        contactEMailLabel = new JLabel("Enter contact's email: ");
        dateNotif = new JLabel("Choose date notification: ");
        timeNotif = new JLabel("Choose time notification: ");
        priority = new JLabel("Choose priority: ");
        contactNameTextField = new JTextField(100);
        contactPhoneTextField = new JTextField(100);
        contactEMailTextField = new JTextField(100);
        SpinnerDateModel sdm = new SpinnerDateModel(now, null, null,
                Calendar.DAY_OF_MONTH);
        SpinnerDateModel sdm1 = new SpinnerDateModel(now, null, null,
                Calendar.DAY_OF_MONTH);
        spinnerTime = new JSpinner(sdm);
        spinnerTime.setEditor(new JSpinner.DateEditor(spinnerTime, "HH:mm"));
        spinnerDate = new JSpinner(sdm1);
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, "dd.MM.yyyy"));

        comboBox = new JComboBox();
        comboBox.addItem("URGENT_IMPORTANT");
        comboBox.addItem("URGENT");
        comboBox.addItem("IMPORTANT");

        okButton = new JButton("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //проверка на правильность введённого имени
                if (!contactNameTextField.getText().equals("")) {
                    task.setContact(contactNameTextField.getText(),
                            contactPhoneTextField.getText(),
                            contactEMailTextField.getText());
                    task.setDate(buildDateTime());
                    if (comboBox.getSelectedItem().equals("URGENT_IMPORTANT")) {
                        task.setPriority(Task.Priority.URGENT_IMPORTANT);
                    } else if (comboBox.getSelectedItem().equals("URGENT")) {
                        task.setPriority(Task.Priority.URGENT);
                    } else if (comboBox.getSelectedItem().equals("IMPORTANT")) {
                        task.setPriority(Task.Priority.IMPORTANT);
                    }
                    setFlag(true);
                    dispose();
                }
            }
        });
        cancelButton = new JButton("cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setFlag(false);
                dispose();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        packaging();
    }

    private void packaging() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(contactNameLabel)
                .addGap(68, 68, 68)
                .addComponent(contactNameTextField, 90, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(contactPhoneLabel)
                .addGap(18, 18, 18)
                .addComponent(contactPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(contactEMailLabel)
                .addGap(68, 68, 68)
                .addComponent(contactEMailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(dateNotif)
                .addGap(50, 50, 50)
                .addComponent(spinnerDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(timeNotif)
                .addGap(50, 50, 50)
                .addComponent(spinnerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(priority)
                .addGap(40, 40, 40)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(okButton)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)))
                .addContainerGap(226, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(contactNameLabel)
                .addComponent(contactNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(contactPhoneLabel)
                .addComponent(contactPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(contactEMailLabel)
                .addComponent(contactEMailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dateNotif)
                .addComponent(spinnerDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(timeNotif)
                .addComponent(spinnerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(priority)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(okButton)
                .addComponent(cancelButton))
                .addContainerGap(86, Short.MAX_VALUE)));
        pack();
    }

    private DateTime buildDateTime() {
        String date = spinnerDate.getValue().toString();
        String[] dateAr = date.split(" ");
        String time = spinnerTime.getValue().toString();
        String[] timeAr = time.split(" ");
        String[] hourAndMinute = timeAr[3].split(":");
        return new DateTime(Integer.parseInt(dateAr[5]),
                getMonthNumber(dateAr[1]),
                Integer.parseInt(dateAr[2]),
                Integer.parseInt(hourAndMinute[0]),
                Integer.parseInt(hourAndMinute[1]));
    }

    private int getMonthNumber(String month) {
        int monthNumber = 0;
        if (month.equals("Jan")) {
            monthNumber = 1;
        } else if (month.equals("Feb")) {
            monthNumber = 2;
        } else if (month.equals("Mar")) {
            monthNumber = 3;
        } else if (month.equals("Apr")) {
            monthNumber = 4;
        } else if (month.equals("May")) {
            monthNumber = 5;
        } else if (month.equals("Jun")) {
            monthNumber = 6;
        } else if (month.equals("Jul")) {
            monthNumber = 7;
        } else if (month.equals("Aug")) {
            monthNumber = 8;
        } else if (month.equals("Sep")) {
            monthNumber = 9;
        } else if (month.equals("Oct")) {
            monthNumber = 10;
        } else if (month.equals("Nov")) {
            monthNumber = 11;
        } else if (month.equals("Dec")) {
            monthNumber = 12;
        }
        return monthNumber;
    }

    private void setFlag(boolean addFlag) {
        this.addFlag.setFlag(addFlag);
    }
}
