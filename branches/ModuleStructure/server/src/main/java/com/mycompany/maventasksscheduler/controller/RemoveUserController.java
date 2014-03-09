/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.ManipulationsOverUsers;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.RemoveUserConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class RemoveUserController {

    private RemoveUserConsoleUI delUserUI;
    private ControlEnteredInformation control;
    private MainConsoleUI userInterface;
    private ManipulationsOverUsers manipulationOverUser;

    public RemoveUserController() {
        control = new ControlEnteredInformation();
        userInterface = new MainConsoleUI();
        manipulationOverUser = new ManipulationsOverUsers();
        delUserUI = new RemoveUserConsoleUI();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            delUserUI.showAddMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    delUserUI.enterUserNumber();
                    manipulationOverUser.showUsers();
                    enteringString = sc.nextLine();
                    if (!manipulationOverUser.userExists(enteringString)) {
                        //если существует пользователь с таким именем, тогда удаляем
                        manipulationOverUser.removeUser(enteringString);
                        delUserUI.userRemoved();
                    } else {
                        delUserUI.userNotExist();
                    }
                    break;
                case 3:
                    delUserUI.removeAll();
                    //удалить всех пользователей
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
        }
    }
}
