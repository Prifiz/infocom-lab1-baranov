/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.controlenteredinformation.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.server.ManipulationsOverUsers;
import com.mycompany.maventasksscheduler.userinterface.consoleui.ChooseUserConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.io.File;
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
                    chooseUserUI.enterUserNumber();
                    listOfFiles(manipulationOverUser.showUsers());
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
        }
    }

    private void listOfFiles(File[] listOfFiles) throws IOException {
        String enteringString = "";
        Scanner sc = new Scanner(System.in);
        if (listOfFiles.length > 0) {
            int menuPoint = -1;
            while (menuPoint < 0 || menuPoint >= listOfFiles.length) {
                enteringString = sc.nextLine();
                if (control.checkString(enteringString)) {
                    menuPoint = Integer.parseInt(enteringString);
                    if (menuPoint < 0 || menuPoint >= listOfFiles.length) {
                        userInterface.chooseCorrectly();
                        manipulationOverUser.showUsers();
                    }
                } else {
                    userInterface.chooseCorrectly();
                    manipulationOverUser.showUsers();
                }
            }
            if (manipulationOverUser.userExists("users\\"
                    + listOfFiles[menuPoint].getName())) {
                //если существует пользователь с таким именем,
                //тогда переходим к редактированию его журнала
                userMainController = new UserMainController(
                        listOfFiles[menuPoint].getName());
                userMainController.start();
            } else {
                chooseUserUI.userNotExist();
            }
        } else {
            chooseUserUI.usersNotExist();
        }
    }
}
