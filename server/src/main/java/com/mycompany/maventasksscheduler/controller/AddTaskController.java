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
import com.mycompany.maventasksscheduler.logmodel.Task.Priority;
import com.mycompany.maventasksscheduler.userinterface.consoleui.AddTaskConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.MainConsoleUI;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class AddTaskController {

    private LogImpl logModel;
    private MainConsoleUI userInterface;
    private AddTaskConsoleUI addConsoleUI;
    private ControlEnteredInformation control;

    public AddTaskController(LogImpl logModel) {
        this.logModel = logModel;
        addConsoleUI = new AddTaskConsoleUI();
        userInterface = new MainConsoleUI();
        control = new ControlEnteredInformation(logModel);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        menu:
        for (;;) {
            addConsoleUI.showAddMenu();
            enteringString = sc.nextLine();
            if (control.checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    break menu;
                case 2:
                    menu2:
                    for (;;) {
                        addConsoleUI.showTasksType();
                        Contact contact;
                        String taskName;
                        DateTime date;
                        String description;
                        Priority priority;
                        int read;
                        key = 33;
                        enteringString = sc.nextLine();
                        if (control.checkString(enteringString)) {
                            key = Integer.parseInt(enteringString);
                        }
                        switch (key) {
                            case 1:
                                date = control.createDate(
                                        control.controlDate(),
                                        control.controlTime());
                                contact = control.controlContact();
                                priority = control.controlPriority();
                                logModel.add(new BirthdayTask(date, contact, priority));
                                addConsoleUI.taskAdded();
                                break menu2;
                            case 2:
                                int backTo = 33;

                                nextMenu:
                                for (;;) {
                                    addConsoleUI.chooseBusinessType();
                                    enteringString = sc.nextLine();
                                    if (control.checkString(enteringString)) {
                                        backTo = Integer.parseInt(enteringString);
                                    }
                                    switch (backTo) {
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
                                                    taskName, description, date,
                                                    contact, priority));
                                            break nextMenu;
                                        default:
                                            userInterface.chooseCorrectly();
                                    }
                                }
                                addConsoleUI.taskAdded();
                                break menu2;
                            default:
                                System.out.println("Choose correctly task type ");
                        }
                    }
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
    }
}
