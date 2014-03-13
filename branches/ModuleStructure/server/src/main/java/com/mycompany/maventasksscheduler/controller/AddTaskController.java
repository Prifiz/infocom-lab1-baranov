/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.controlenteredinformation.ControlEnteredInformation;
import com.mycompany.maventasksscheduler.logmodel.BirthdayTask;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.Contact;
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
                        enteringString = sc.nextLine();
                        if (control.checkString(enteringString)) {
                            if (addTask(Integer.parseInt(enteringString)) == 1){
                                break menu2;
                            }
                        }
                    }
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
            key = 33;
        }
    }

    private int addTask(int type) {
        Scanner sc = new Scanner(System.in);
        String enteringString = "";
        Contact contact;
        DateTime date;
        Priority priority;
        switch (type) {
            case 1:
                date = control.createDate(
                        control.controlDate(),
                        control.controlTime());
                contact = control.controlContact();
                priority = control.controlPriority();
                logModel.add(new BirthdayTask(
                        date, contact, priority));
                addConsoleUI.taskAdded();
                return 1;
            case 2:
                int taskType = 33;
                nextMenu:
                for (;;) {
                    addConsoleUI.chooseBusinessType();
                    enteringString = sc.nextLine();
                    if (control.checkString(enteringString)) {
                        taskType = Integer.parseInt(enteringString);
                    }
                    if (addOneOfTypeBusinessTask(taskType) == 1) {
                        break nextMenu;
                    }
                }
                addConsoleUI.taskAdded();
                return 1;
            default:
                System.out.println("Choose correctly task type ");
                return 0;
        }
    }

    private int addOneOfTypeBusinessTask(int typeOfBusinessTask) {
        Contact contact;
        String taskName;
        DateTime date;
        String description;
        Priority priority;
        switch (typeOfBusinessTask) {
            case 1:
                taskName = control.controlTaskName();
                date = control.createDate(
                        control.controlDate(), control.controlTime());
                priority = control.controlPriority();
                logModel.add(new BusinessTask(taskName, date, priority));
                return 1;
            case 2:
                taskName = control.controlTaskName();
                description = control.controlDescription();
                date = control.createDate(control.controlDate(),
                        control.controlTime());
                priority = control.controlPriority();
                logModel.add(new BusinessTask(
                        taskName, description, date, priority));
                return 1;
            case 3:
                taskName = control.controlTaskName();
                description = control.controlDescription();
                date = control.createDate(
                        control.controlDate(), control.controlTime());
                contact = control.controlContact();
                priority = control.controlPriority();
                logModel.add(new BusinessTask(taskName, description, date,
                        contact, priority));
                return 1;
            default:
                userInterface.chooseCorrectly();
                return 0;
        }
    }
}
