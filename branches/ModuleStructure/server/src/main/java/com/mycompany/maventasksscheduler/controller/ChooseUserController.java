/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.ManipulationsOverUsers;
import com.mycompany.maventasksscheduler.userinterface.consoleui.ChooseUserConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class ChooseUserController {

    private ChooseUserConsoleUI chooseUserUI;
    private ControlEnteredInformation control;
    private MainConsoleUI userInterface;
    private ManipulationsOverUsers manipulationOverUser;
    private UserMainController userMainController;

    public ChooseUserController() {
        control = new ControlEnteredInformation();
        userInterface = new MainConsoleUI();
        manipulationOverUser = new ManipulationsOverUsers();
        chooseUserUI = new ChooseUserConsoleUI();
    }

    public void start() throws IOException {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        int usersCount = 0;
        menu:
        for (;;) {
            chooseUserUI.showAddMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    usersCount = manipulationOverUser.showUsers();
                    if (usersCount > 0) {
                        chooseUserUI.enterUserNumber();
                        enteringString = sc.nextLine();
                        if (manipulationOverUser.userExists("users\\"
                                + enteringString)) {
                            //если существует пользователь с таким именем,
                            //тогда переходим к редактированию его журнала
                            userMainController = new UserMainController(
                                    enteringString);
                            userMainController.start();
                        } else {
                            chooseUserUI.userNotExist();
                        }
                    } else {
                        chooseUserUI.usersNotExist();
                    }
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
}
