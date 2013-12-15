/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import com.mycompany.maventasksscheduler.exceptions.BadEnteredDate;
import com.mycompany.maventasksscheduler.exceptions.TaskFieldIndexOutOfBoundsException;
import com.mycompany.maventasksscheduler.exceptions.TaskIndexOutOfBoundsException;
import com.mycompany.maventasksscheduler.logmodel.Task.Priority;
import com.mycompany.maventasksscheduler.logmodel.Task.Status;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class LogImpl implements Log, Cloneable {

    private List<Task> log;
    private ControlEnteredInformation control;
    
    public LogImpl() {
        log = new LinkedList();
        control = new ControlEnteredInformation(this);
    }

    /**
     * Returns count task in LogModel
     *
     * @return int - count task
     */
    public int getSize() {
        return log.size();
    }

    public void setLog(LinkedList<Task> log){
        this.log = log;
    }
    
    public Task get(int i) {
        return log.get(i);
    }
   
    @Override
    public void add(Task task) {
        log.add(task);
    }

    @Override
    public void remove(int taskNumber) {
        if (taskNumber < 0 || taskNumber >= log.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        log.remove(taskNumber);
    }
    
    public void removeAll(){
        for(int i = 0; i < log.size(); i++)
            log.remove(i);
    }

    @Override
    public LinkedList<Task> search(DateTime date) {
        if (log.isEmpty()) {
            return null;
        } else {
            //Iterator<Taskable> iter = log.iterator();
            LinkedList<Task> foundTasks = new LinkedList();
            //for(Taskable task : foundTasks)
//            while(iter.hasNext()){ ищет не все даты, когда использую iterator
//                if(iter.next().getDate() == date)
//                    foundTasks.add(iter.next());
//            }
            for (int i = 0; i < log.size(); i++) {
                if (log.get(i).getDate().getYear() == date.getYear()
                        && log.get(i).getDate().getMonthOfYear() == date.getMonthOfYear()
                        && log.get(i).getDate().getDayOfMonth() == date.getDayOfMonth()) {
                    foundTasks.add(log.get(i));
                }
            }
            return foundTasks;
        }
    }

    @Override
    public void sortByDate() {
        Comparator<Task> comparator = new Comparator<Task>() {
            public int compare(Task c1, Task c2) {
                return c2.getDate().compareTo(c1.getDate());
            }
        };
        Collections.sort(log, comparator);
    }
    
    @Override
    public void sortByPriority() {
        Comparator<Task> comparator = new Comparator<Task>() {
            public int compare(Task c1, Task c2) {
                return c1.getPriority().compareTo(c2.getPriority());
            }
        };
        Collections.sort(log, comparator);
    }
    

    /**
     * Causes necessary number of times editSomeFieldOfTask.
     *
     * @param taskId Id of an edited task.
     */
    @Override
    public void editAllDataTask(int taskId) {
        if (taskId < 0 || taskId > log.size()) 
            throw new TaskIndexOutOfBoundsException();
        for (int i = 0; i < log.get(taskId).getFieldCount(); i++) 
                editSomeFieldOfTask(taskId, i + 1);
    }

    @Override
    public void editSomeFieldOfTask(int taskId, int fieldNumber) {
        if (taskId < 0 || taskId > log.size()) 
            throw new TaskIndexOutOfBoundsException();
        if (fieldNumber < 1 || fieldNumber > log.get(taskId).getFieldCount()) 
                throw new TaskFieldIndexOutOfBoundsException();
        if (log.get(taskId) instanceof BirthdayTask)
            setEditSomeFieldOfBirthday(taskId, fieldNumber);
        if (log.get(taskId) instanceof BusinessTask)
            setEditSomeFieldOfBusinessTask(taskId, fieldNumber);

    }

    /*
     * module for edit some field of Birthday
     */
    public void setEditSomeFieldOfBirthday(int taskId, int fieldNumber) {
        switch (fieldNumber) {
            case 1:
                log.get(taskId).setDate(control.createDate(
                        control.controlDate(), control.controlTime()));
                break;
            case 2:
                log.get(taskId).setContact(control.controlContact());
                break;
            case 3:
                log.get(taskId).setStatus(control.controlStatus());
                break;
            case 4:
                log.get(taskId).setPriority(control.controlPriority());
                break;
            default: 
        }
    }

    /*
     * module for edit some field of BusinessTask
     */
    public void setEditSomeFieldOfBusinessTask(int taskId, int fieldNumber) {
        Scanner sc = new Scanner(System.in);
        switch (fieldNumber) {
            case 1:
                control.setTaskName(taskId, control.controlTaskName());
                break;
            case 2:
                control.setDescription(taskId, control.controlDescription());
                break;
            case 3:
                log.get(taskId).setDate(control.createDate(
                        control.controlDate(), control.controlTime()));
                break;
            case 4:
                log.get(taskId).setContact(control.controlContact());
                break;
            case 5:
                log.get(taskId).setStatus(control.controlStatus());
                break;
            case 6:
                log.get(taskId).setPriority(control.controlPriority());
                break;
        }
    }

    /*
     * for not to duplicate general part in setEditSomeFieldOfBusinessTask and 
     * setEditSomeFieldOfBirthday took out in a separate method
     */
//    public DateTime setDate() throws BadEnteredDate {
//        Scanner sc = new Scanner(System.in);
//        String date;
//        String[] splitDate = new String[3];
//        while (true) {
//            System.out.println("Enter correctly date, example:\n9.12.2013 or 9-12-2013, or 9/12/2013");
//            date = sc.nextLine();
//            if (date.contains("")) {
//                splitDate = date.split("\\.");
//            }
//            else if (date.contains("-")) {
//                splitDate = date.split("-");
//            }
//            else if (date.contains("/")) {
//                splitDate = date.split("/");
//            }
//            if (Integer.parseInt(splitDate[2]) >= 2013 && Integer.parseInt(splitDate[1]) > 0
//                    && Integer.parseInt(splitDate[1]) < 13 && Integer.parseInt(splitDate[0]) > 0
//                    && Integer.parseInt(splitDate[0]) < 32) {
//                break;
//            }
//        }
//        String[] splitTime = new String[2];
//        while (true) {
//            System.out.println("Enter correctly time description, example:\n 20:44");
//            date = sc.nextLine();
//            if (date.contains(":")) {
//                splitTime = date.split(":");
//            }
//            if (Integer.parseInt(splitTime[0]) >= 0 && Integer.parseInt(splitTime[0]) < 25
//                    && Integer.parseInt(splitTime[1]) >= 0 && Integer.parseInt(splitTime[1]) < 60) {
//                break;
//            }
//        }
//        return new DateTime(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
//                Integer.parseInt(splitDate[0]), Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
//    }
    
    

    
    
    /*
     * for not to duplicate general part in setEditSomeFieldOfBusinessTask and 
     * setEditSomeFieldOfBirthday took out in a separate method
     */
//    public Contact setContactInfo(int read) {
//        Scanner sc = new Scanner(System.in);
//        Contact contact;
//        String name;
//        long phoneNumber;
//        String email;
//        
//        sc.nextLine();
//        switch (read) {
//            case 1:
//                System.out.println("Enter name:");
//                name = sc.nextLine();
//                contact = new Contact(name);
//                break;
//            case 2:
//                System.out.println("Enter name:");
//                name = sc.nextLine();
//                System.out.println("Enter phone number:");
//                phoneNumber = sc.nextLong();
//                contact = new Contact(name, phoneNumber);
//                break;
//            case 3:
//                System.out.println("Enter name:");
//                name = sc.nextLine();
//                System.out.println("Enter phone number:");
//                phoneNumber = Long.parseLong(sc.nextLine());
//                System.out.println("Enter email:");
//                email = sc.nextLine();
//                contact = new Contact(name, phoneNumber, email);
//                break;
//            default:
//                contact = new Contact();
//        }
//        return contact;
//    }
    
    


    
    
}