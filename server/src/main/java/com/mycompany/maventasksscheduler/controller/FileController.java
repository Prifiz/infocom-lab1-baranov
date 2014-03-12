/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.controlenteredinformation.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.userinterface.consoleui.FileConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class FileController {

    private LogImpl logModel;
    private FileConsoleUI fileUI;
    private MainConsoleUI userInterface;
    private XMLStorage xml;
    private ControlEnteredInformation control;
    private String userName;

    public FileController(LogImpl logModel, String userName) {
        this.userName = userName;
        this.logModel = logModel;
        fileUI = new FileConsoleUI();
        userInterface = new MainConsoleUI();
        xml = new XMLStorage();
        control = new ControlEnteredInformation(logModel);
    }

    public LogImpl start() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            fileUI.showFileMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    xml.saveData(logModel, userName);
                    fileUI.taskSaved();
                    break;
                case 3:
                    logModel = xml.uploadData(userName);
                    fileUI.taskLoaded();
                    break;
                case 4:
                    while (logModel.getSize() > 0) {
                        logModel.removeAll();
                    }
                    fileUI.allTaskRemoved();
                    break;
//                case 0:
//                    xml.saveData(logModel, userName);
//                    System.exit(0);
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
        }
        return logModel;
    }
}
