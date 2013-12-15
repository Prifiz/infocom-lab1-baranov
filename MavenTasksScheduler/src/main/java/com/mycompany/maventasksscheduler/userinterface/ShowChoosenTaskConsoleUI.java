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
    
    public void showFileMenu(){
        System.out.println("\n You look through a task:"
                    + "\n1. Back to"
                    + "\n2. Edit task"
                    + "\n3. Remove task\n");
    }
    
    public void taskRemoved(){
        System.out.println("Task removed\n");
    }
    
}
