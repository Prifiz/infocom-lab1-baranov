/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.ManipulationsOverUsers;
import com.mycompany.maventasksscheduler.userinterface.consoleui.AddUserConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class AddUserController {

    private AddUserConsoleUI addUserUI;
    private ControlEnteredInformation control;
    private MainConsoleUI userInterface;
    private ManipulationsOverUsers manipulationOverUser;

    public AddUserController() {
        addUserUI = new AddUserConsoleUI();
        control = new ControlEnteredInformation();
        userInterface = new MainConsoleUI();
        manipulationOverUser = new ManipulationsOverUsers();
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
                    if (!manipulationOverUser.userExists(enteringString)) {
                        manipulationOverUser.createUser(enteringString);
                        addUserUI.userAdded();
                    } else {
                        addUserUI.userExist();
                    }
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
        }
    }
}
