/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.controlenteredinformation.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.consoleui.UserMainConsoleUI;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class UserMainController {

    private LogImpl logModel;
    private UserMainConsoleUI userInterface;
    private FileController fileController;
    private HelpController helpController;
    private AddTaskController addController;
    private ControlEnteredInformation control;
    private ChooseTaskController chooseTask;
    private XMLStorage xml;
    private UserOSController userOSController;
    private boolean flag;
    private String userName;

    public UserMainController(String user) {
        userName = user;
        xml = new XMLStorage();
        logModel = xml.uploadData(userName);
        userInterface = new UserMainConsoleUI();
        control = new ControlEnteredInformation(logModel);
        userOSController = new UserOSController();
        flag = true;
    }

    public UserMainController(LogImpl logModel) {
        this.logModel = logModel;
        userInterface = new UserMainConsoleUI();
        control = new ControlEnteredInformation(this.logModel);
        xml = new XMLStorage();
        userOSController = new UserOSController();
        flag = true;
    }

    public void start() throws IOException {
        userOSController.start();
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            if (!flag) {
                userOSController.start();
            }
            userInterface.showMainMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    helpController = new HelpController();
                    helpController.start();
                    flag = false;
                    break;
                case 2:
                    fileController = new FileController(logModel, userName);
                    logModel = fileController.start();
                    flag = false;
                    break;
                case 3:
                    if (logModel.getSize() == 0) {
                        userInterface.logIsEmpty();
                        flag = true;
                        break;
                    }
                    logModel.sortByDate();
                    userInterface.showAll(logModel);
                    chooseTask = new ChooseTaskController(logModel);
                    chooseTask.start();
                    flag = false;
                    break;
                case 4:
                    if (logModel.getSize() == 0) {
                        userInterface.logIsEmpty();
                        flag = true;
                        break;
                    }
                    List<Task> foundTasks = logModel.search(
                            control.createDate(control.controlDate()));
                    userInterface.foundTasks(foundTasks);
                    chooseTask = new ChooseTaskController(logModel, foundTasks);
                    chooseTask.start();
                    flag = false;
                    break;
                case 5:
                    addController = new AddTaskController(logModel);
                    addController.start();
                    flag = false;
                    break;
                case 0:
                    xml.saveData(logModel, userName);
                    break menu;
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
        }
    }

    public LogImpl getLog() {
        return logModel;
    }
}
