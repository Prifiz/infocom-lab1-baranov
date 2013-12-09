/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;


import java.util.LinkedList;
import org.joda.time.DateTime;



/**
 * This interface describes possible manipulations over log.
 * @author Сергей
 */
public interface WorkWithLogModelable {
    
    /**
     * This method show all tasks that include logModel.
     */
    void showAll();
    
    /**
     * This method add task in logModel.
     * @param task Task which has to be added.
     */
    void add(Taskable task);
    
    /**
     * This method remove chosen task.
     * @param taskNumber Task's number which will be removed.
     */
    void remove(int taskNumber);
    
    /**
     * This method looks for tasks with the specified date.
     * @param date Date by which search will be run.
     * @return A LinkedList<Taskable> containing the found dates.
     */
    LinkedList<Taskable> search(DateTime date);
    
    /**
     * This method sorts tasks of date.
     */
    void sortByDate();
    
    /**
     * This method allows to edit all fields of the chosen task.
     * @param taskNumber Number of an edited task.
     */
    void editAllDataTask(int taskNumber);
    
    /**
     * This method allows to edit a field of the chosen task.
     * @param taskNumber Number of an edited task.
     * @param fieldNumber Number of an edited field.
     */
    void editSomeFieldOfTask(int taskNumber, int fieldNumber);
    
}
