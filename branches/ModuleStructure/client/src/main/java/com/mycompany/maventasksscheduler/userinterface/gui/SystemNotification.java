/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import java.util.Timer;
import java.util.TimerTask;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class SystemNotification implements Runnable {

    private LogImpl tasks;
    private LogImpl logModel;
    private DateTime dateTime;
    private Timer timer;
    private NotificationFrame notificationFrame;

    public SystemNotification(LogImpl logModel) {
        this.logModel = logModel;
        tasks = new LogImpl();
        dateTime = new DateTime();
    }

    public void run() {
        tasks = notification();
    }

    public LogImpl notification() {
        for (int i = 0; i < logModel.getSize(); i++) {
            if (logModel.get(i).getDate().getYear() == dateTime.getYear()
                    && logModel.get(i).getDate().getMonthOfYear()
                    == dateTime.getMonthOfYear()
                    && logModel.get(i).getDate().getDayOfMonth()
                    == dateTime.getDayOfMonth()) {
                tasks.add(logModel.get(i));
                long timeNotif = 3600000 * logModel.get(i).getDate().getHourOfDay()
                        + 60000 * logModel.get(i).getDate().getMinuteOfHour();
                long timeRightNow = 3600000 * dateTime.getHourOfDay()
                        + 60000 * dateTime.getMinuteOfHour();
                long toTheNotif = timeNotif - timeRightNow;
                if (toTheNotif > 0) {
                    timer = new Timer();
                    timer.schedule(new RemindTask(logModel.get(i)), toTheNotif);
                }
            }
        }
        return tasks;
    }

    class RemindTask extends TimerTask {

        private Task task;

        RemindTask(Task task) {
            this.task = task;
        }

        public void run() {
            notificationFrame = new NotificationFrame(task);
            notificationFrame.setVisible(true);
            timer.cancel(); //Terminate the timer thread
        }
    }
}
