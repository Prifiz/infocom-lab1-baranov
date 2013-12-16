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
import com.mycompany.maventasksscheduler.logmodel.Task.Priority;
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
                    String taskName;
                    DateTime date;
                    String description;
                    Priority priority;
                    int read;
                    switch (sc.nextInt()) {
                        case 1:
                            date = control.createDate(
                                                control.controlDate(), 
                                                control.controlTime());
                            contact = control.controlContact();
                            priority = control.controlPriority();
                            logModel.add(new BirthdayTask(date, contact, priority));
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
                                        taskName = control.controlTaskName();
                                        date = control.createDate(
                                                control.controlDate(), 
                                                control.controlTime());
                                        priority = control.controlPriority();
                                        logModel.add(new BusinessTask(
                                                taskName, date, priority));
                                        break nextMenu;
                                    case 2:
                                        taskName = control.controlTaskName();
                                        description = control.controlDescription();
                                        date = control.createDate(
                                                control.controlDate(), 
                                                control.controlTime());
                                        priority = control.controlPriority();
                                        logModel.add(new BusinessTask(
                                                taskName, description, date,
                                                priority));
                                        break nextMenu;
                                    case 3:
                                        taskName = control.controlTaskName();
                                        description = control.controlDescription();
                                        date = control.createDate(
                                                control.controlDate(), 
                                                control.controlTime());
                                        contact = control.controlContact();
                                        priority = control.controlPriority();
                                        logModel.add(new BusinessTask(
                                                taskName,description, date,
                                                contact,priority));
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
