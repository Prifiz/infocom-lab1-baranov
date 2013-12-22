/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.userinterface.HelpConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class HelpController {
    
    
    private HelpConsoleUI helpUI;
    private MainConsoleUI userInterface;
    private ControlEnteredInformation control;
    
    public HelpController(){
        helpUI = new HelpConsoleUI();
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation();
    }
    
    
    public void start(){
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for(;;){
            helpUI.showHelpMenu();
            enteringString = sc.nextLine();
            if(control.checkString(enteringString))
                key = Integer.parseInt(enteringString);
            switch(key){
                case 1:
                    break menu;
                case 2:
                    // manual helpUI.taskSaved();
                    break;
                case 3:
                    helpUI.infAboutProgram();
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
