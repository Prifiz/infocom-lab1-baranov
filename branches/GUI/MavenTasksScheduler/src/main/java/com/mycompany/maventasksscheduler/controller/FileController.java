/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.userinterface.FileConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
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

    public FileController(LogImpl logModel) {
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
                    xml.saveData(logModel);
                    fileUI.taskSaved();
                    break;
                case 3:
                    logModel = xml.uploadData();
                    fileUI.taskLoaded();
                    break;
                case 4:
                    while (logModel.getSize() > 0) {
                        logModel.removeAll();
                    }
                    fileUI.allTaskRemoved();
                    break;
                case 0:
                    xml.saveData(logModel);
                    System.exit(0);
                default:
                    userInterface.chooseCorrectly();
            }
        }
        return logModel;
    }
}
