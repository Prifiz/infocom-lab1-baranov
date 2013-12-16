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
    private MainController mainController;
    private ShowChoosenTaskConsoleUI choosetask;
    private int taskNumber;
    
    
    public ShowChoosenTaskController(LogImpl logModel, Task task, int taskNumber){
        this.logModel = logModel;
        this.task = task;
        userInterface = new MainConsoleUI();
        choosetask = new ShowChoosenTaskConsoleUI();
        this.taskNumber = taskNumber;
        mainController = new MainController(this.logModel);
    }
    
    public void start(){
        Scanner sc = new Scanner(System.in);
        int backTo = 0;
        menu:
        for(;;){
           System.out.println("\nChoosen task:\n"+logModel.get(taskNumber).toString());
           choosetask.showFileMenu();
           switch(sc.nextInt()){
                case 1:
                    nextMenu:
                    for(;;){
                        choosetask.showBackTO();
                        sc.nextLine();
                        backTo = sc.nextInt();
                        switch(backTo){
                            case 1:
                                break nextMenu;
                            case 2:
                                break nextMenu;
                            default:
                                userInterface.chooseCorrectly();
                        }
                    }
                    break menu;
                case 2:
                    nextMenu:
                    for(;;){
                        choosetask.chooseEditType();
                        sc.nextLine();
                        backTo = sc.nextInt();
                        switch(backTo){
                            case 1:
                                logModel.editAllDataTask(taskNumber);
                                break nextMenu;
                            case 2:
                                
                                break nextMenu;
                            default:
                                userInterface.chooseCorrectly();
                        }
                    }
                    break;
                case 3:
                    logModel.remove(taskNumber);
                    choosetask.taskRemoved();
                    nextMenu:
                    for(;;){
                        choosetask.showBackTO();
                        sc.nextLine();
                        backTo = sc.nextInt();
                        switch(backTo){
                            case 1:
                                break menu;
                            case 2:
                                break menu;
                            default:
                                userInterface.chooseCorrectly();
                        }
                    }
                    //break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
        if(backTo == 1)
            mainController.start();
    }
    
}
