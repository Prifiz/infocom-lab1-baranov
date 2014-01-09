/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class MainController {
    
    private LogImpl logModel;
    private MainConsoleUI userInterface;
    private FileController fileController;
    private HelpController helpController;
    private AddController addController;
    private ControlEnteredInformation control;
    private ChooseTaskController chooseTask;
    private XMLStorage xml;
    private NotificationController notificationController;
    
    public MainController(){
        xml = new XMLStorage();
        logModel = xml.uploadData();
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation(logModel);
        notificationController = new NotificationController();
    }
    
    public MainController(LogImpl logModel){
        this.logModel = logModel;
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation(this.logModel);
        xml = new XMLStorage();
        notificationController = new NotificationController();
    }
    
    public void start(){
        notificationController.start();
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for(;;){
            userInterface.showMainMenu();
            enteringString = sc.nextLine();
            if(control.checkString(enteringString))
                key = Integer.parseInt(enteringString);
            switch(key){
                case 1:
                    helpController = new HelpController();
                    helpController.start();
                    break;
                case 2:
                    fileController = new FileController(logModel);
                    logModel = fileController.start();
                    break;
                case 3:
                    if(logModel.getSize() == 0){
                        userInterface.logIsEmpty();
                        break;
                    }
                    logModel.sortByDate();
                    userInterface.showAll(logModel);
                    chooseTask = new ChooseTaskController(logModel);
                    chooseTask.start();
                    break;
                case 4:
                    if(logModel.getSize() == 0){
                        userInterface.logIsEmpty();
                        break;
                    }
                    List <Task> foundTasks = logModel.search(
                            control.createDate(control.controlDate()));
                    userInterface.foundTasks(foundTasks);
                    chooseTask = new ChooseTaskController(logModel, foundTasks);
                    chooseTask.start();
                    break;
                case 5:
                    addController = new AddController(logModel);
                    addController.start();
                    break;
                case 0:
                    xml.saveData(logModel);
                    System.exit(0);
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
    public LogImpl getLog(){
        return logModel;
    }
}
