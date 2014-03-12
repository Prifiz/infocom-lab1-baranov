/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class DayOfWeek extends JLabel implements ActionListener {

    private DateTime date;
    private StringBuilder strDate;
    private String str;

    public DayOfWeek() {
        date = new DateTime();
        strDate = new StringBuilder();
        setDayOfWeekName();
        strDate.append("Today - ").append(str).append(". ");
        setMonthName();
        strDate.append(date.getDayOfMonth()).append(" ").append(str);
        setText(strDate.toString());
        strDate.delete(0, strDate.length());
        Timer t = new Timer(86400000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent e) {
        date = new DateTime();
        setDayOfWeekName();
        strDate.append("Today - ").append(str).append(". ");
        setMonthName();
        strDate.append(date.getDayOfMonth()).append(" ").append(str);
        setText(strDate.toString());
        strDate.delete(0, strDate.length());
    }

    private void setMonthName() {
        switch (date.getMonthOfYear()) {
            case 1:
                str = "January";
                break;
            case 2:
                str = "February";
                break;
            case 3:
                str = "March";
                break;
            case 4:
                str = "April";
                break;
            case 5:
                str = "May";
                break;
            case 6:
                str = "June";
                break;
            case 7:
                str = "July";
                break;
            case 8:
                str = "August";
                break;
            case 9:
                str = "September";
                break;
            case 10:
                str = "October";
                break;
            case 11:
                str = "November";
                break;
            case 12:
                str = "December";
                break;
            default:
                str = "";
        }
    }

    private void setDayOfWeekName() {
        switch (date.getDayOfWeek()) {
            case 1:
                str = "Monday";
                break;
            case 2:
                str = "Tuesday";
                break;
            case 3:
                str = "Wednesday";
                break;
            case 4:
                str = "Thursday";
                break;
            case 5:
                str = "Friday";
                break;
            case 6:
                str = "Saturday";
                break;
            case 7:
                str = "Sunday";
                break;
            default:
                str = "";
        }
    }
}
