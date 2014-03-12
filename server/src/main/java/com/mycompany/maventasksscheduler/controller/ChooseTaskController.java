/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.controlenteredinformation.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.consoleui.ChooseTaskConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.io.IOException;
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
    private ControlEnteredInformation control;

    public ChooseTaskController(LogImpl logModel) {
        this.logModel = logModel;
        userInterface = new MainConsoleUI();
        choosetask = new ChooseTaskConsoleUI();
        this.foundTasks = new LinkedList();
        control = new ControlEnteredInformation(logModel);

    }

    public ChooseTaskController(LogImpl logModel, List<Task> foundTasks) {
        this.logModel = logModel;
        this.foundTasks = foundTasks;
        userInterface = new MainConsoleUI();
        choosetask = new ChooseTaskConsoleUI();
        control = new ControlEnteredInformation(logModel);
    }

    public void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            choosetask.showFileMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    int number = 0;
                    number = control.chooseTaskId();
                    showChoosenTask = new ShowChoosenTaskController(
                            logModel, logModel.get(number), number);
                    showChoosenTask.start();
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
            if (foundTasks.isEmpty()) {
                userInterface.showAll(logModel);
            } else if (foundTasks.size() > 0) {
                userInterface.foundTasks(foundTasks);
            }
        }
    }
}
