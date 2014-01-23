/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface;

/**
 *
 * @author Сергей
 */
public class HelpConsoleUI {

    public void showHelpMenu() {
        System.out.println("\nYou in help menu, choose menu point:"
                + "\n1. Back to main menu"
                + "\n2. Program manual"
                + "\n3. About program\n");
    }

    public void infAboutProgram() {
        System.out.println("Task scheduler version 0.0.0.");
    }
}
