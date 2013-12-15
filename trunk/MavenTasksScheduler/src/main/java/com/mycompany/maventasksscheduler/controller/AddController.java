/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.BirthdayTask;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.Contact;
import com.mycompany.maventasksscheduler.logmodel.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.AddConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class AddController {
    
    
    private LogImpl logModel;
    private MainConsoleUI userInterface;
    private AddConsoleUI addConsoleUI;
    private ControlEnteredInformation control;
    
    public AddController(LogImpl logModel){
        this.logModel = logModel;
        addConsoleUI = new AddConsoleUI();
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation(logModel);
    }
    
    public void start(){
        Scanner sc = new Scanner(System.in);
        menu:
        for(;;){
            addConsoleUI.showAddMenu();
            switch(sc.nextInt()){
                case 1:
                    break menu;
                case 2:
                    addConsoleUI.showTasksType();
                    Contact contact;
                    int read;
                    switch (sc.nextInt()) {
                        case 1:
                            logModel.add(new BirthdayTask(control.createDate(
                        control.controlDate(), control.controlTime()), 
                                    control.controlContact(), control.controlPriority()));
                            addConsoleUI.taskAdded();
                            break;
                        case 2:
                            int backTo = 0;
                            
                            nextMenu:
                            for(;;){
                                addConsoleUI.chooseBusinessType();
                                sc.nextLine();
                                backTo = sc.nextInt();
                                switch(backTo){
                                    case 1:
                                        logModel.add(new BusinessTask(
                                                control.controlTaskName(),
                                                control.createDate(
                                                control.controlDate(), 
                                                control.controlTime()),
                                                control.controlPriority()));
                                        break nextMenu;
                                    case 2:
                                        logModel.add(new BusinessTask(
                                                control.controlTaskName(),
                                                control.controlDescription(),
                                                control.createDate(
                                                control.controlDate(), 
                                                control.controlTime()),
                                                control.controlPriority()));
                                        break nextMenu;
                                    case 3:
                                        logModel.add(new BusinessTask(
                                                control.controlTaskName(),
                                                control.controlDescription(),
                                                control.createDate(
                                                control.controlDate(), 
                                                control.controlTime()),
                                                control.controlContact(),
                                                control.controlPriority()));
                                        break nextMenu;
                                    default:
                                        userInterface.chooseCorrectly();
                                }
                            }
                            addConsoleUI.taskAdded();
                            break;
                        default:
                            System.out.println("Choose correctly task type ");
                    }
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
    
}
