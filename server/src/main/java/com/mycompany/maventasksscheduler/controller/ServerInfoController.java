/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.ServerInfoConsoleUI;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class ServerInfoController {
    
    private Thread thread;
    private boolean flag;
    private ControlEnteredInformation control;
    private ServerInfoConsoleUI serverInfoConsoleUI;
    private MainConsoleUI userInterface;
    
    public ServerInfoController(Thread thread){
        flag = true;
        this.thread = thread;
        control = new ControlEnteredInformation();
        serverInfoConsoleUI = new ServerInfoConsoleUI();
        userInterface = new MainConsoleUI();
    }
    
    public void start() throws IOException {        
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            serverInfoConsoleUI.showServerInfoMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    serverInfoConsoleUI.infAboutProgram();
                    System.out.println(thread.getThreadGroup().activeGroupCount());
                    break;
                case 3:
                    
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
