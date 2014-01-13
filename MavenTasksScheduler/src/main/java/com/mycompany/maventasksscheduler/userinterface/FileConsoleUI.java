/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface;

/**
 *
 * @author Сергей
 */
public class FileConsoleUI {

    public void showFileMenu() {
        System.out.println("\nYou in file menu, choose menu point:"
                + "\n1. Back to main menu"
                + "\n2. Save tasks"
                + "\n3. Load tasks"
                + "\n4. Remove all"
                + "\n0. Exit program\n");
    }

    public void taskSaved() {
        System.out.println("Tasks saved.");
    }

    public void taskLoaded() {
        System.out.println("Tasks loaded.");
    }

    public void allTaskRemoved() {
        System.out.println("Tasks removed.");
    }
}
