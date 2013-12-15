/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.userinterface.AddConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class AddController {
    
    
    private LogImpl logModel;
    private MainConsoleUI userInterface;
    private AddConsoleUI addConsoleUI;
    
    public AddController(LogImpl logModel){
        this.logModel = logModel;
        addConsoleUI = new AddConsoleUI();
    }
    
    public void start(){
        Scanner sc = new Scanner(System.in);
        menu:
        for(;;){
            addConsoleUI.showAddMenu();
            switch(sc.nextInt()){
                case 1:
                    break menu;
                case 2:
                    logModel.createTask();
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
