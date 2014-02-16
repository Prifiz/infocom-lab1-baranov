/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.systemnotification;

//import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.consoleui.NotificationConsoleUI;
import org.joda.time.DateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Сергей
 */
public class MessageNotification implements SystemNotification, Runnable {

    private LogImpl tasks;
    private LogImpl logModel;
    private DateTime dateTime;
   // private XMLStorage xml;
    private NotificationConsoleUI consoleUI;
    private Timer timer;

    class RemindTask extends TimerTask {

        private Task task;

        RemindTask(Task task) {
            this.task = task;
        }

        public void run() {
            System.out.format("Notification! %s %n", task.toString());
            timer.cancel(); //Terminate the timer thread
        }
    }

    public MessageNotification() {
      //  xml = new XMLStorage();
       // this.logModel = xml.uploadData();
        tasks = new LogImpl();
        dateTime = new DateTime();
        consoleUI = new NotificationConsoleUI();
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

    public void run() {
        tasks = notification();
        if (tasks.getSize() == 0) {
            consoleUI.noTaskForToday();
        } else {
            consoleUI.tasksPlannedForToday();
            consoleUI.showPlannedTasks(tasks);
        }
    }
}
