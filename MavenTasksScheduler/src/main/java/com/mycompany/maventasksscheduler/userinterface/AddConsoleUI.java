/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface;

/**
 *
 * @author Сергей
 */
public class AddConsoleUI {
    
     public void showAddMenu(){
        System.out.println("\nYou in add menu, choose menu point:"
                    + "\n1. Back to main menu"
                    + "\n2. Choose task type\n");
     }
     
     public void showTasksType(){
         System.out.println("Choose task type:\n1. Birthday\n2. Business task");
     }
     
     public void enterCorrectlyDate(){
         System.out.println("Enter correctly date, example:\n9.12.2013 or "
                 + "9-12-2013, or 9/12/2013");
     }
     
     public void enterCorrectlyTime(){
         System.out.println("Enter correctly time description, example:\n 20:44");
     }
     
     public void taskAdded(){
         System.out.println("Task is added");
     }
     
     public void enterContactName(){
         System.out.println("Enter name:");
     }
     
     public void enterContactPhoneNumber(){
         System.out.println("Enter phone number:");
     }
     
     public void enterContactMail(){
         System.out.println("Enter email:");
     }
             
            
}
