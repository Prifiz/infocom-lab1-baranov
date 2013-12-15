/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.BirthdayTask;
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
                    int [] splitDate = new int [3];
                    int [] splitTime = new int [2];
                    Task.Priority priority;
                    Contact contact;
                    int read;
                    switch (sc.nextInt()) {
                        case 1:
                            splitDate = control.controlDate();
                            splitTime = control.controlTime();
                            contact = control.controlContact();
                            priority = control.controlPriority();
                            logModel.add(new BirthdayTask(new DateTime(
                                    splitDate[2],splitDate[1], splitDate[0], 
                                    splitTime[0], splitTime[1]), 
                                    contact, priority));
                            addConsoleUI.taskAdded();
                            break;
                        case 2:
                            
                            splitDate = control.controlDate();
                            splitTime = control.controlTime();
                            contact = control.controlContact();
                            priority = control.controlPriority();
                            logModel.add(new BirthdayTask(new DateTime(
                                    splitDate[2],splitDate[1], splitDate[0], 
                                    splitTime[0], splitTime[1]), 
                                    contact, priority));
                            addConsoleUI.taskAdded();
                            System.out.println("Create business task");
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
