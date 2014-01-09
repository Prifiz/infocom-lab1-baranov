/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.systemnotification.MessageNotification;
import com.mycompany.maventasksscheduler.userinterface.NotificationConsoleUI;

/**
 *
 * @author Сергей
 */
public class NotificationController {
    private MessageNotification message;
    private LogImpl task;
    private NotificationConsoleUI consoleUI;
    
    public NotificationController(){
        message = new MessageNotification();
        consoleUI = new NotificationConsoleUI();
    }
    
    
    public void start(){
        task = message.notification();
        if(task.getSize() == 0)
            consoleUI.noTaskForToday();
        else{
            consoleUI.tasksPlannedForToday();
            consoleUI.showPlannedTasks(task);
        }    
    }
           
}
