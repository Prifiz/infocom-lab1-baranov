/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
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
       
        
    
    public FileController(LogImpl logModel){
        this.logModel = logModel;
        fileUI = new FileConsoleUI();
        userInterface = new MainConsoleUI();
        xml = new XMLStorage();
    }
    
    public LogImpl start(){
        Scanner sc = new Scanner(System.in);
        menu:
        for(;;){
            fileUI.showFileMenu();
            switch(sc.nextInt()){
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
                    logModel.removeAll();
                    fileUI.allTaskRemoved();
                    break;
                case 0:
                   System.exit(0);
                default:
                    userInterface.chooseCorrectly();
            }
        }
        return logModel;
    }
    
}
