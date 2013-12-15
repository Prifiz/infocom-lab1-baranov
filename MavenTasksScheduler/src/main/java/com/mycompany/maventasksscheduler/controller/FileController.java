/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

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
    
    public FileController(LogImpl logModel){
        this.logModel = logModel;
        fileUI = new FileConsoleUI();
    }
    
    public void start(){
        Scanner sc = new Scanner(System.in);
        menu:
        for(;;){
            fileUI.showFileMenu();
            switch(sc.nextInt()){
                case 1:
                    break menu;
                case 2:
                    fileUI.taskSaved();
                    break;
                case 3:
                    fileUI.taskLoaded();
                    break;
                case 4:
                    logModel.removeAll();
                    fileUI.allTaskRemoved();
                    break;
                case 0://реализовать закрытие программы
                   break menu; 
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
