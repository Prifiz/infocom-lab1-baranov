/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
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
    private ControlEnteredInformation control;

    public ShowChoosenTaskController(LogImpl logModel, Task task, int taskNumber) {
        this.logModel = logModel;
        this.task = task;
        userInterface = new MainConsoleUI();
        choosetask = new ShowChoosenTaskConsoleUI();
        this.taskNumber = taskNumber;
        mainController = new MainController(this.logModel);
        control = new ControlEnteredInformation(logModel);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int backTo = 0;
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            System.out.println("\nChoosen task:\n"
                    + logModel.get(taskNumber).toString());
            choosetask.showFileMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    nextMenu:
                    for (;;) {
                        choosetask.showBackTO();
                        enteringString = sc.nextLine();
                        if (control.checkString(enteringString)) {
                            backTo = Integer.parseInt(enteringString);
                        }
                        switch (backTo) {
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
                    for (;;) {
                        choosetask.chooseEditType();
                        enteringString = sc.nextLine();
                        if (control.checkString(enteringString)) {
                            backTo = Integer.parseInt(enteringString);
                        }
                        switch (backTo) {
                            case 1:
                                logModel.editAllDataTask(taskNumber);
                                break nextMenu;
                            case 2:
                                System.out.println("This function in this "
                                        + "version doesn't work");
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
                    for (;;) {
                        choosetask.showBackTO();
                        enteringString = sc.nextLine();
                        if (control.checkString(enteringString)) {
                            backTo = Integer.parseInt(enteringString);
                        }
                        switch (backTo) {
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
        if (backTo == 1) {
            mainController.start();
        }
    }
}
