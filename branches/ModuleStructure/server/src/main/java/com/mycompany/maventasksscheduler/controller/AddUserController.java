/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.userinterface.consoleui.AddUserConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class AddUserController {

    private XMLStorage xml;
    private AddUserConsoleUI addUserUI;
    private ControlEnteredInformation control;
        private MainConsoleUI userInterface;

    public AddUserController() {
        xml = new XMLStorage();
        addUserUI = new AddUserConsoleUI();
        control = new ControlEnteredInformation();
        userInterface = new MainConsoleUI();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            addUserUI.showAddMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    addUserUI.enterUserName();
                    enteringString = sc.nextLine();
                    xml.uploadData(enteringString);
                    addUserUI.userAdded();
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
}
