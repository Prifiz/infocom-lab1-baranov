/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.ChooseTaskConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.ShowChoosenTaskConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class ShowChoosenTaskController {
    
    private LogImpl logModel;
    private Task task;
    private MainConsoleUI userInterface;
    private ShowChoosenTaskConsoleUI choosetask;
    private int taskNumber;
    
    
    public ShowChoosenTaskController(LogImpl logModel, Task task, int taskNumber){
        this.logModel = logModel;
        this.task = task;
        userInterface = new MainConsoleUI();
        choosetask = new ShowChoosenTaskConsoleUI();
        this.taskNumber = taskNumber;
    }
    
    public void start(){
        Scanner sc = new Scanner(System.in);
        menu:
        for(;;){
           choosetask.showFileMenu();
           switch(sc.nextInt()){
                case 1:
                    break menu;
                case 2:
                    
                    break;
                case 3:
                    logModel.remove(taskNumber);
                    choosetask.taskRemoved();
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
