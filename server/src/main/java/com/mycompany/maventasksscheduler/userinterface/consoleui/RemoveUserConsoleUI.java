/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.consoleui;

/**
 *
 * @author Сергей
 */
public class RemoveUserConsoleUI {

    public void showAddMenu() {
        System.out.println("\nYou in add menu, choose menu point:"
                + "\n1. Back to main menu"
                + "\n2. Remove user\n");
    }

    public void enterUserNumber() {
        System.out.println("Enter user's number");
    }

    public void userRemoved() {
        System.out.println("The user is removed");
    }

    public void usersRemoved() {
        System.out.println("The users are removed");
    }

    public void removeAll() {
        System.out.println("Do you want remove all users?");
    }

    public void userNotExist() {
        System.out.println("User with this name not exist");
    }
}