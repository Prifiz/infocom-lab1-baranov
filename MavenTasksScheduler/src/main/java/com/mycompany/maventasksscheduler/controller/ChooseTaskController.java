/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.ChooseTaskConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.FileConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class ChooseTaskController {
    
    private LogImpl logModel;
    private MainConsoleUI userInterface;
    private ChooseTaskConsoleUI choosetask;
    private ShowChoosenTaskController showChoosenTask;
    private List<Task> foundTasks;
    
    public ChooseTaskController(LogImpl logModel){
        this.logModel = logModel;
        userInterface = new MainConsoleUI();
        choosetask = new ChooseTaskConsoleUI();
    }
    
    public ChooseTaskController(List<Task> foundTasks){
        this.foundTasks = foundTasks;
        userInterface = new MainConsoleUI();
        choosetask = new ChooseTaskConsoleUI();
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
                    int number = 0;
                    number = userInterface.chooseTaskId();
                    showChoosenTask = new ShowChoosenTaskController(
                            logModel, logModel.get(number), number);
                    showChoosenTask.start();
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
