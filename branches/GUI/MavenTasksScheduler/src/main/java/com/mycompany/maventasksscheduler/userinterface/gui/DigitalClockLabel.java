/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class DigitalClockLabel extends JLabel implements ActionListener {

    private DateTime time;
    private StringBuilder strTime;

    public DigitalClockLabel() {
        time = new DateTime();
        strTime = new StringBuilder();
        strTime.append(time.getHourOfDay()).append(":").append(redactMinuts());
        setText(strTime.toString());
        strTime.delete(0, strTime.length());
        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae) {
        time = new DateTime();
        strTime.append(time.getHourOfDay()).append(":").append(redactMinuts());
        setText(strTime.toString());
        strTime.delete(0, strTime.length());
    }

    private String redactMinuts() {
        if (time.getMinuteOfHour() < 10) {
            return new StringBuilder().append("0").append(
                    time.getMinuteOfHour()).toString();
        }
        return new StringBuilder().append(time.getMinuteOfHour()).toString();
    }
}
