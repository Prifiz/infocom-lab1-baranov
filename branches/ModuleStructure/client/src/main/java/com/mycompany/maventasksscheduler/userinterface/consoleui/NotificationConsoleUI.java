/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.consoleui;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;

/**
 *
 * @author Сергей
 */
public class NotificationConsoleUI {

    public void tasksPlannedForToday() {
        System.out.println("Tasks planned for today:");
    }

    public void noTaskForToday() {
        System.out.println("For today there are no tasks");
    }

    public void showPlannedTasks(LogImpl tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(tasks.get(i).toString());
        }
    }
}
