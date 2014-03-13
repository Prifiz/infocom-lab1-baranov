/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controlenteredinformation;

import com.mycompany.maventasksscheduler.exceptions.BadEnteredDate;
import com.mycompany.maventasksscheduler.logmodel.Contact;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.consoleui.AddTaskConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.consoleui.UserMainConsoleUI;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class ControlEnteredInformation implements java.io.Serializable {

    private LogImpl logModel;
    private UserMainConsoleUI userInterface;
    private AddTaskConsoleUI addConsoleUI;

    public ControlEnteredInformation() {
        addConsoleUI = new AddTaskConsoleUI();
        userInterface = new UserMainConsoleUI();
    }

    public ControlEnteredInformation(LogImpl logModel) {
        this.logModel = logModel;
        addConsoleUI = new AddTaskConsoleUI();
        userInterface = new UserMainConsoleUI();
    }

    public int[] controlDate() {
        int[] splitDate = new int[3];
        while (true) {
            addConsoleUI.enterCorrectlyDate();
            splitDate = ReadAndSplit.date();
            if (splitDate[2] >= new DateTime().getYear() && splitDate[1] > 0
                    && splitDate[1] < 13 && splitDate[0] > 0
                    && splitDate[0] < 32) {
                break;
            }
        }
        return splitDate;
    }

    public int[] controlTime() {
        int[] splitTime = new int[2];
        while (true) {
            addConsoleUI.enterCorrectlyTime();
            splitTime = ReadAndSplit.time();
            if (splitTime[0] >= 0 && splitTime[0] < 25
                    && splitTime[1] >= 0 && splitTime[1] < 60) {
                break;
            }
        }
        return splitTime;
    }

    public DateTime createDate(int[] date) throws BadEnteredDate {
        DateTime dt = new DateTime(date[2], date[1], date[0], 0, 0);
        return dt;
    }

    public DateTime createDate(int[] date, int[] time) throws BadEnteredDate {
        return new DateTime(date[2], date[1], date[0], time[0], time[1]);
    }

    public Contact controlContact() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        String name;
        String phoneNumber;
        String email;
        Contact contact = new Contact();
        while (true) {
            userInterface.contactInfo();
            enteringString = sc.nextLine();
            if (checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            if (key > 0 && key < 4) {
                break;
            }
        }
        boolean flag = true;
        while (flag) {
            switch (key) {
                case 1:
                    name = readContactName();
                    contact = new Contact(name);
                    flag = false;
                    break;
                case 2:
                    name = readContactName();
                    phoneNumber = readContatcPhoneNumber();
                    contact = new Contact(name, phoneNumber);
                    flag = false;
                    break;
                case 3:
                    name = readContactName();
                    phoneNumber = readContatcPhoneNumber();
                    email = readContatcMail();
                    contact = new Contact(name, phoneNumber, email);
                    flag = false;
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
        return contact;
    }

    public Task.Priority controlPriority() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        Task.Priority priority = null;
        boolean flag = true;
        while (flag) {
            userInterface.priority();
            enteringString = sc.nextLine();
            if (checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    priority = setPriority(1);
                    flag = false;
                    break;
                case 2:
                    priority = setPriority(2);
                    flag = false;
                    break;
                case 3:
                    priority = setPriority(3);
                    flag = false;
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
        return priority;
    }

    public Task.Status controlStatus() {
        Scanner sc = new Scanner(System.in);
        int key = 33;
        String enteringString = "";
        Task.Status status = null;
        boolean flag = true;
        while (flag) {
            userInterface.status();
            enteringString = sc.nextLine();
            if (checkString(enteringString)) {
                key = Integer.parseInt(enteringString);
            }
            switch (key) {
                case 1:
                    status = setStatus(1);
                    flag = false;
                    break;
                case 2:
                    status = setStatus(2);
                    flag = false;
                    break;
                case 3:
                    status = setStatus(3);
                    flag = false;
                    break;
                default:
                    userInterface.chooseCorrectly();
            }
        }
        return status;
    }

    public String controlTaskName() {
        Scanner sc = new Scanner(System.in);
        String taskName = "";
        for (;;) {
            addConsoleUI.enterTaskName();
            taskName = sc.nextLine();
            if (taskName.length() > 0 && taskName.length() < 41) {
                break;
            }
        }
        return taskName;
    }

    public String controlDescription() {
        Scanner sc = new Scanner(System.in);
        String description = "";
        for (;;) {
            addConsoleUI.enterDescription();
            description = sc.nextLine();
            if (description.length() > 0 && description.length() < 150) {
                break;
            }
        }
        return description;
    }

    private String readContactName() {
        Scanner sc = new Scanner(System.in);
        StringBuilder contactName = new StringBuilder();
        String str;
        for (;;) {
            addConsoleUI.enterContactName();
            contactName.append(sc.nextLine());
            if (contactName.length() > 0 && contactName.length() < 41) {
                str = contactName.substring(0, 1);
                contactName.insert(0, str.toUpperCase());
                contactName.deleteCharAt(1);
                for (int i = 0; i < contactName.length(); i++) {
                    if (contactName.charAt(i) == ' ') {
                        str = contactName.substring(i + 1, i + 2);
                        contactName.insert(i + 1, str.toUpperCase());
                        contactName.deleteCharAt(i + 2);
                    }
                }
                break;
            }
            contactName.delete(0, contactName.length());
        }
        return contactName.toString();
    }

    private String readContatcPhoneNumber() {
        Scanner sc = new Scanner(System.in);
        String phoneNumber = "";
        String enteringString = "";
        for (;;) {
            addConsoleUI.enterContactPhoneNumber();
            enteringString = sc.nextLine();
            if (checkString(enteringString)) {
                phoneNumber = enteringString;
            }
            if (!phoneNumber.equals("") && phoneNumber.length() > 4) {
                break;
            }
        }
        return phoneNumber;
    }

    private String readContatcMail() {
        Scanner sc = new Scanner(System.in);
        StringBuilder contactMail = new StringBuilder();
        for (;;) {
            addConsoleUI.enterContactMail();
            contactMail.append(sc.nextLine());
            if (contactMail.length() > 0 && contactMail.length() < 50) {
                break;
            }
            contactMail.delete(0, contactMail.length());
        }
        return contactMail.toString();
    }

    private Task.Status setStatus(int read) {
        Task.Status status = Task.Status.ACTIVE;
        switch (read) {
            case 1:
                status = Task.Status.ACTIVE;
                break;
            case 2:
                status = Task.Status.POSTPONED;
                break;
            case 3:
                status = Task.Status.COMPLETE;
                break;
            default:
        }
        return status;
    }

    private Task.Priority setPriority(int read) {
        Task.Priority priority = Task.Priority.URGENT_IMPORTANT;
        switch (read) {
            case 1:
                priority = Task.Priority.URGENT_IMPORTANT;
                break;
            case 2:
                priority = Task.Priority.URGENT;
                break;
            case 3:
                priority = Task.Priority.IMPORTANT;
                break;
            default:
        }
        return priority;
    }

    public int chooseTaskId() {
        Scanner sc = new Scanner(System.in);
        String enteringString = "";
        int taskId = -1;
        for (;;) {
            userInterface.chooseTaskId();
            enteringString = sc.nextLine();
            if (checkString(enteringString)) {
                taskId = Integer.parseInt(enteringString);
            }
            if (taskId >= 0 && taskId < logModel.getSize()) {
                break;
            }
        }
        return taskId;
    }

    public boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
