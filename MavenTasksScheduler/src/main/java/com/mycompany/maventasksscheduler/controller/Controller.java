/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.exceptions.BadEnteredDate;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.ConsoleUserInterface;
import java.util.LinkedList;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class Controller {
    
    private LogImpl logModel;
    private ConsoleUserInterface userInterface;
    
    public Controller(){
        logModel = new LogImpl();
        userInterface = new ConsoleUserInterface();
    }
    
    public void add(){
        logModel.createTask();
    }
    
    public void remove(){
        if(logModel.getSize() == 0)
            return;
        userInterface.showAll(logModel);
        logModel.remove(userInterface.chooseTaskId());
    }
    
    public void editAllDataTask(){
        if(logModel.getSize() == 0)
            return;
        userInterface.showAll(logModel);
        logModel.editAllDataTask(userInterface.chooseTaskId());
    }
    
    public void showAll() {
        userInterface.showAll(logModel);
    }
    
    public void sortTaskListByDate(){
        logModel.sortByDate();
    }
    
    public void sortTaskListByPriority(){
        logModel.sortByPriority();
    }
    
    public void searchTask(){
        LinkedList<Task> foundTasks = logModel.search(createDate());
        for(int i = 0; i < foundTasks.size(); i++)
                System.out.println(foundTasks.get(i).toString());
    }
    
    
    public DateTime createDate() throws BadEnteredDate {
        Scanner sc = new Scanner(System.in);
        String date;
        String[] splitDate = new String[3];
        while (true) {
            System.out.println("Enter correctly date, example:\n9.12.2013 or 9-12-2013, or 9/12/2013");
            date = sc.nextLine();
            if (date.contains("")) {
                splitDate = date.split("\\.");
            }
            else if (date.contains("-")) {
                splitDate = date.split("-");
            }
            else if (date.contains("/")) {
                splitDate = date.split("/");
            }
            if (Integer.parseInt(splitDate[2]) >= 2013 && Integer.parseInt(splitDate[1]) > 0
                    && Integer.parseInt(splitDate[1]) < 13 && Integer.parseInt(splitDate[0]) > 0
                    && Integer.parseInt(splitDate[0]) < 32) {
                break;
            }
        }
         return new DateTime(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[0]), 0, 0);
    }
    
    
}
