/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class Controller {
    
    private LogImpl logModel;
    
    public Controller(){
        logModel = new LogImpl();
    }
    
    public void add(){
        logModel.createTask();
    }
    
    public void remove(){
        if(logModel.getId() == 0)
            return;
        logModel.showAll();
        System.out.println("\nChoose task's id");
        Scanner sc = new Scanner(System.in);
        logModel.remove(sc.nextInt());
    }
    
    public void editAllDataTask(){
        if(logModel.getId() == 0)
            return;
        logModel.showAll();
        System.out.println("\nChoose task's id");
        Scanner sc = new Scanner(System.in);
        logModel.editAllDataTask(sc.nextInt());
    }
    
    public void showAll() {
        logModel.showAll();
    }
    
    public void sortTaslList(){
        logModel.sortByDate();
    }
    
    public void searchTask(){
        LinkedList<Task> foundTasks = logModel.search(logModel.createDate());
        for(int i = 0; i < foundTasks.size(); i++)
                System.out.println(foundTasks.get(i).toString());
    }
    
    
}
