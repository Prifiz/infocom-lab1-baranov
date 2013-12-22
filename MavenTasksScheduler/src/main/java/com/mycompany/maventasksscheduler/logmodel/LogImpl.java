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
    
    public LogImpl(List<Task> log) {
        this.log = log;
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
        if (taskNumber < 0 || taskNumber >= log.size()) 
            throw new TaskIndexOutOfBoundsException();
        else
            log.remove(taskNumber);
    }
    
    public void removeAll(){
        System.out.println(log.size());
        for(int i = 0; i < log.size(); i++){
            log.remove(i);
        }
            
    }

    @Override
    public List<Task> search(DateTime date) {
        if (log.isEmpty())
            return log;
        else {
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
        else
            for (int i = 0; i < log.get(taskId).getFieldCount(); i++) 
                editSomeFieldOfTask(taskId, i + 1);
    }

    @Override
    public void editSomeFieldOfTask(int taskId, int fieldNumber) {
        if (taskId < 0 || taskId > log.size()) 
            throw new TaskIndexOutOfBoundsException();
        else if (fieldNumber < 1 || fieldNumber > log.get(taskId).getFieldCount())
            throw new TaskFieldIndexOutOfBoundsException();
        else if (log.get(taskId) instanceof BirthdayTask)
            setEditSomeFieldOfBirthday(taskId, fieldNumber);
        else if (log.get(taskId) instanceof BusinessTask)
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
            default: 
        }
    }

}