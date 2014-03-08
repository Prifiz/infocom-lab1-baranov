/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.consoleui;

/**
 *
 * @author Сергей
 */
public class ChooseUserConsoleUI {
    
    
    public void showAddMenu() {
        System.out.println("\nYou in add menu, choose menu point:"
                + "\n1. Back to main menu"
                + "\n2. Choose user\n");
    }
    
    public void usersNotExist() {
        System.out.println("Users doesn't exist");
    }

    public void enterUserNumber() {
        System.out.println("Enter user's number");
    }

    public void userNotExist() {
        System.out.println("User with this name not exist");
    }
    
}
