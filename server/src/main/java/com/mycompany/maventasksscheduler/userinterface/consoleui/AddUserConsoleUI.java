/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.consoleui;

/**
 *
 * @author Сергей
 */
public class AddUserConsoleUI {

    public void showAddMenu() {
        System.out.println("\nYou in add menu, choose menu point:"
                + "\n1. Back to main menu"
                + "\n2. Add user\n");
    }

    public void enterUserName() {
        System.out.println("Enter user's name");
    }

    public void userAdded() {
        System.out.println("The user is added");
    }
}
