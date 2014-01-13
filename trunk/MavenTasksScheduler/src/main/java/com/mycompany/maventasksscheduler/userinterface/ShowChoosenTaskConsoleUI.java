/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface;

/**
 *
 * @author Сергей
 */
public class ShowChoosenTaskConsoleUI {

    public void showFileMenu() {
        System.out.println("\n You look through a task:"
                + "\n1. Back to"
                + "\n2. Edit task"
                + "\n3. Remove task\n");
    }

    public void showBackTO() {
        System.out.println("\nBack to:"
                + "\n1. Main menu"
                + "\n2. The previous point of the menu\n");
    }

    public void chooseEditType() {
        System.out.println("\n Choose edit type:"
                + "\n1. edit all data task"
                + "\n2. edit some field of task\n");
    }

    public void taskRemoved() {
        System.out.println("Task removed\n");
    }
}
