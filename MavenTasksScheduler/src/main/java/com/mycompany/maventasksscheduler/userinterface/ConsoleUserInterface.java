/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class ConsoleUserInterface {
    
    
    
     public void showAll(LogImpl log) {
        for (int i = 0; i < log.getSize(); i++) {
            System.out.println("№ - " + i + ", " + log.get(i).toString());
        }
     }
     
     public int chooseTaskId(){
         System.out.println("\nChoose task's №");
         Scanner sc = new Scanner(System.in);
         return sc.nextInt();
     }
     
     public void contactInfo(){
         System.out.println("What information on contact you want to enter?\n"
                 + "1. Only name."
                 + "2. Name and phone number."
                 + "3. Name, phone number and email.");
     }
     
     
     
     
     
     
    
}
