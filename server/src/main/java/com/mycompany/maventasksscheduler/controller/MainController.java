/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.Server;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Сергей
 */
public class MainController {

    private MainConsoleUI userInterface;
    private HelpController helpController;
    private ControlEnteredInformation control;
    private UserOSController userOSController;
    private boolean flag;
    private ServerInfoController serverInfoController;
    private AddUserController addUserController;
    private RemoveUserController removeUserController;
    private ChooseUserController chooseUserController;
    private ServerSocket serverSocket;
    private Server server;

    public MainController() {
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation();
        userOSController = new UserOSController();
        helpController = new HelpController();
        flag = true;
        try {
            serverSocket = new ServerSocket(8189);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        server = new Server(serverSocket);
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
                    helpController.start();
                    flag = false;
                    break;
                case 2:
                    addUserController = new AddUserController();
                    addUserController.start();
                    break;
                case 3:
                    chooseUserController = new ChooseUserController();
                    chooseUserController.start();
                    break;
                case 4:
//                    removeUserController = new RemoveUserController();
//                    removeUserController.start();
                    //remove user
                    break;
                case 5:
                    if (!server.isAlive()) {
                        server.start();
                    }
                    userInterface.serverIsLaunched();
                    //start server
                    break;
                case 6:
                    if (server.isAlive()) {
                        server.setActive(false);//почему-то не останавливает
                        System.out.println("server stop");
                        //остановка сервера через определённое время
                        //с предварительным оповещением пользователей
                    } else {
                        userInterface.serverIsNotLaunched();
                    }
                    //stop server
                    break;
                case 7:
//                    serverInfoController = new ServerInfoController(thread);
//                    serverInfoController.start();
                    //get info about server
                    break;

                case 0:
                    System.exit(0);
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }

}
