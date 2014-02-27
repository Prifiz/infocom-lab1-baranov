/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.consoleui;

/**
 *
 * @author Сергей
 */
public class ServerInfoConsoleUI {

    public void showServerInfoMenu() {
        System.out.println("\nYou in help menu, choose menu point:"
                + "\n1. Back to main menu"
                + "\n2. Get active threads count"
                + "\n3. About program\n");
    }

    public void infAboutProgram() {
        System.out.println("Active threads count");
    }
}
