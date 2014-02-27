/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.ProcessingClientThread;
import com.mycompany.maventasksscheduler.Server;
import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.io.IOException;
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
    private AddTaskController addController;
    private ControlEnteredInformation control;
    private ChooseTaskController chooseTask;
    private XMLStorage xml;
    private UserOSController userOSController;
    private boolean flag;
    private Runnable run;
    private Thread thread;
    private ServerInfoController serverInfoController;

    public MainController() {
        xml = new XMLStorage();
        logModel = xml.uploadData("");
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation(logModel);
        userOSController = new UserOSController();
        flag = true;
        run = new Server(xml, logModel);
        thread = new Thread(run);
    }

    public MainController(LogImpl logModel) {
        this.logModel = logModel;
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation(this.logModel);
        xml = new XMLStorage();
        userOSController = new UserOSController();
        flag = true;
        run = new Server(xml, logModel);
        thread = new Thread(run);
    }

    public void start() throws IOException {
        thread.start();
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
                    //add user
                    break;
                case 3:
                    //choose user
                    break;
                case 4:
                    //remove user
                    break;
                case 5:
                    serverInfoController = new ServerInfoController(thread);
                    serverInfoController.start();
                    //get info about server
                    break;
//                case 2:
//                    fileController = new FileController(logModel);
//                    logModel = fileController.start();
//                    flag = false;
//                    break;
//                case 3:
//                    if (logModel.getSize() == 0) {
//                        userInterface.logIsEmpty();
//                        flag = true;
//                        break;
//                    }
//                    logModel.sortByDate();
//                    userInterface.showAll(logModel);
//                    chooseTask = new ChooseTaskController(logModel);
//                    chooseTask.start();
//                    flag = false;
//                    break;
//                case 4:
//                    if (logModel.getSize() == 0) {
//                        userInterface.logIsEmpty();
//                        flag = true;
//                        break;
//                    }
//                    List<Task> foundTasks = logModel.search(
//                            control.createDate(control.controlDate()));
//                    userInterface.foundTasks(foundTasks);
//                    chooseTask = new ChooseTaskController(logModel, foundTasks);
//                    chooseTask.start();
//                    flag = false;
//                    break;
//                case 5:
//                    addController = new AddTaskController(logModel);
//                    addController.start();
//                    flag = false;
//                    break;
                case 0:
                    // xml.saveData(logModel);
                    System.exit(0);
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }

    public LogImpl getLog() {
        return logModel;
    }
}
