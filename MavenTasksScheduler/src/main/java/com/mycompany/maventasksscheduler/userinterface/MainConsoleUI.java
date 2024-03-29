/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import java.util.List;

/**
 *
 * @author Сергей
 */
public class MainConsoleUI {

    public MainConsoleUI() {
    }

    public void showMainMenu() {
        System.out.println("\nYou in main menu, choose menu point:"
                + "\n1. Help"
                + "\n2. File"
                + "\n3. Show tasks"
                + "\n4. Search task"
                + "\n5. Add task"
                + "\n0. Exit\n");
    }

    public void chooseCorrectly() {
        System.out.println("Please, choose correctly menu point");
    }

    public void showAll(LogImpl log) {
        for (int i = 0; i < log.getSize(); i++) {
            System.out.println("No " + i + " | " + log.get(i).toString());
        }
    }

    public void chooseTaskId() {
        System.out.println("\nChoose correctly task's number");
    }

    public void contactInfo() {
        System.out.println("What information on contact you want to enter?"
                + "\n1. Only name."
                + "\n2. Name and phone number."
                + "\n3. Name, phone number and email.\n");
    }

    public void priority() {
        System.out.println("Choose new priority:"
                + "\n 1. URGENT_IMPORTANT."
                + "\n 2. URGENT."
                + "\n 3. IMPORTANT.");
    }

    public void status() {
        System.out.println("Choose new priority:"
                + "\n 1. ACTIVE."
                + "\n 2. POSTPONED."
                + "\n 3. COMPLETE.");
    }

    public void taskName() {
        System.out.println("Enter the new task's name:");
    }

    public void taskDescription() {
        System.out.println("Enter the new task's description:");
    }

    public void foundTasks(List<Task> foundTasks) {
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println("No - " + i + ", " + foundTasks.get(i).toString());
        }
    }

    public void logIsEmpty() {
        System.out.println("In the magazine there is no task");
    }
}
