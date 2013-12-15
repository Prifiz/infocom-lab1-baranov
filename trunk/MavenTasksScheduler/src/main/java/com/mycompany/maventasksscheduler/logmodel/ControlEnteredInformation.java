/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import com.mycompany.maventasksscheduler.controller.AddController;
import com.mycompany.maventasksscheduler.controller.FileController;
import com.mycompany.maventasksscheduler.controller.HelpController;
import com.mycompany.maventasksscheduler.exceptions.BadEnteredDate;
import com.mycompany.maventasksscheduler.logmodel.Task.Priority;
import com.mycompany.maventasksscheduler.logmodel.Task.Status;
import com.mycompany.maventasksscheduler.userinterface.AddConsoleUI;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class ControlEnteredInformation {
    
    private LogImpl logModel;
    private MainConsoleUI userInterface;
    private FileController fileController;
    private HelpController helpController;
    private AddController addController;
    private AddConsoleUI addConsoleUI;
    
    
    public ControlEnteredInformation (LogImpl logModel){
        this.logModel = logModel;
        addConsoleUI = new AddConsoleUI();
        userInterface = new MainConsoleUI();
    }
    
    
    public int[] readAndSplitDate() {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        int [] intSplitDate = new int[3];
        if(date.length() < 7)
            return intSplitDate;
        String[] splitDate = new String[3];
        if (date.contains("")) {
            splitDate = date.split("\\.");
        }
        else if (date.contains("-")) {
            splitDate = date.split("-");
        }
        else if (date.contains("/")) {
            splitDate = date.split("/");
        }
        if (Integer.parseInt(splitDate[1]) < 2013 && Integer.parseInt(splitDate[1]) <= 0
                && Integer.parseInt(splitDate[1]) >= 13 && Integer.parseInt(splitDate[0]) <= 0
                && Integer.parseInt(splitDate[0]) > 31) {
            return intSplitDate;
        }
        else{
            for(int i = 0; i < splitDate.length; i++)
                intSplitDate[i] = Integer.parseInt(splitDate[i]);
            return intSplitDate;
        }
    }
    
    
    public int [] controlDate(){
        int [] splitDate = new int [3];
        while (true) {
            addConsoleUI.enterCorrectlyDate();
            splitDate = readAndSplitDate();
            if (splitDate[2] >= 2013 && splitDate[1] > 0
                    && splitDate[1] < 13 && splitDate[0] > 0
                    && splitDate[0] < 32) {
                break;
            }
        }
        return splitDate;
    }
    
    
        
    public int[] readAndSplitTime() {
        Scanner sc = new Scanner(System.in);
        String time = sc.nextLine();
        int [] intSplitTime = new int[3];
        if(time.length() < 3)
            return intSplitTime;
        String[] splitTime = new String[2];
        if (time.contains(":")) {
            splitTime = time.split(":");
        }
        if (Integer.parseInt(splitTime[0]) < 0 && Integer.parseInt(splitTime[0]) > 24
                && Integer.parseInt(splitTime[1]) < 0 && Integer.parseInt(splitTime[1]) > 60) {
            return intSplitTime;
        }
        for(int i = 0; i < splitTime.length; i++)
            intSplitTime[i] = Integer.parseInt(splitTime[i]);
        return intSplitTime;
    }
    
    public int [] controlTime(){
        int [] splitTime = new int [2];
        while (true) {
            addConsoleUI.enterCorrectlyTime();
            splitTime = readAndSplitTime();
            if (splitTime[0] >= 0 && splitTime[0] < 25 && 
                    splitTime[1] >= 0 && splitTime[1] < 60) {
                break;
            }
        }
        return splitTime;
    }
    
    public DateTime createDate(int [] date) throws BadEnteredDate{
        DateTime dt = new DateTime(date[2], date[1], date[0], 0, 0);
        return dt;
    }
    
    public DateTime createDate(int [] date, int [] time) throws BadEnteredDate{
        return new DateTime(date[2], date[1], date[0], time[0], time[1]);
    }
    
    public Contact controlContact(){
        Scanner sc = new Scanner(System.in);
        int read;
        String name;
        long phoneNumber;
        String email;
        Contact contact = new Contact();
        while(true){
            userInterface.contactInfo();
            read = sc.nextInt();
            if(read > 0 && read < 4)
                break;
        }
        boolean flag = true;
        while (flag) {
        switch(read){
            case 1:
                addConsoleUI.enterContactName();
                name = readContactName();
                contact = createContact(name);
                flag = false;
                break;
            case 2:
                addConsoleUI.enterContactName();
                name = readContactName();
                addConsoleUI.enterContactPhoneNumber();
                phoneNumber = readContatcPhoneNumber();
                contact = createContact(name,phoneNumber);
                flag = false;
                break;
            case 3: 
                addConsoleUI.enterContactName();
                name = readContactName();
                addConsoleUI.enterContactPhoneNumber();
                phoneNumber = readContatcPhoneNumber();
                addConsoleUI.enterContactMail();
                email = readContatcMail();
                contact = createContact(name,phoneNumber, email);
                flag = false;
                break;
            default:
                 userInterface.chooseCorrectly();

        }
        }
        return contact;
    }
    
    public Priority controlPriority(){
        Scanner sc = new Scanner(System.in);
        int read;
        Task.Priority priority = null;
        boolean flag = true;
        while (flag) {
            userInterface.priority();
            read = sc.nextInt();
            sc.nextLine();
            switch (read) {
                case 1:
                    priority = setPriority(1);
                    flag = false;
                    break ;
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
    
    public Status controlStatus(){
        Scanner sc = new Scanner(System.in);
        int read;
        Task.Status status = null;
        boolean flag = true;
        while (flag) {
            userInterface.status();
            read = sc.nextInt();
            sc.nextLine();
            switch (read) {
                case 1:
                    status = setStatus(1);
                    flag = false;
                    break ;
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
    
    public String controlTaskName(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public String controlDescription(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    
    public String readContactName(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public long readContatcPhoneNumber(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }
    
    public String readContatcMail(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public Contact createContact(String name){
        return new Contact(name);
    }
    
    public Contact createContact(String name, long phoneNumber){
        return new Contact(name, phoneNumber);
    }
    
    public Contact createContact(String name, long phoneNumber, String email){
        return new Contact(name, phoneNumber, email);
    }
    
    public Status setStatus(int read){
        Status status = null;
        switch (read) {
                case 1:
                    status = Status.ACTIVE;
                    break ;
                case 2:
                    status = Status.POSTPONED;
                    break;
                case 3:
                    status = Status.COMPLETE;
                    break;
                default:
            }
        return status;
    }
    
    public Priority setPriority(int read){
        Priority priority = null;
        switch (read) {
                case 1:
                    priority = Priority.URGENT_IMPORTANT;
                    break ;
                case 2:
                    priority = Priority.URGENT;
                    break;
                case 3:
                    priority = Priority.IMPORTANT;
                    break;
                default:
        }
        return priority;
    }
    
    public void setTaskName(int taskId, String taskName){
        if(logModel.get(taskId) instanceof BusinessTask){
            BusinessTask bt = (BusinessTask) logModel.get(taskId);
            bt.setTaskName(taskName);
        }
    }
    
    public void setDescription(int taskId, String description){
        if(logModel.get(taskId) instanceof BusinessTask){
            BusinessTask bt = (BusinessTask) logModel.get(taskId);
            bt.setTaskName(description);
        }
    }
    
}
