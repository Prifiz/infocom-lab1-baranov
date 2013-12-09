/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import org.joda.time.DateTime;

/**
 * This interface describes tasks.
 * @author Сергей
 */
public interface Taskable {
    
    /**
     * Returns task's id.
     * @return int - task's id.
     */
    int getId();
    
    /**
     * Returns date and time notification about task.
     * @return A Date notification.
     */
    DateTime getDate();
    
    /**
     * Returns contact with whom the task is connected.
     * @return A Contact.
     */
    Contact getContact();
    
    /**
     * Returns task's status: 
     * 10 - active,
     * 20 - postponed,
     * 30 - complete.
     * @return A short containing 10|20|30.
     */
    short getStatus();
    
    /**
     * Returns task's Priority.
     * @return A int containing task's priority.
     */
    int getPriority();
    
    /**
     * This method set task's id.
     * @param id Id of task.
     */
    void setId(int id);
    
    /**
     * This method set task's date notification.
     * @param date Date notification about task.
     */
    void setDate(DateTime date);
    
    /**
     * This method edits contact with whom the task is connected.
     * @param contact Task's contact.
     */
    void setContact(Contact contact);
    
    /**
     * This method edits contact with whom the task is connected. 
     * The constructor - Contact(name) is called.
     * @param name Contact's name.
     */
    void setContact(String name);
    
    /**
     * This method edits contact with whom the task is connected.
     * The constructor - Contact(name, phoneNumber) is called.
     * @param name Contact's name.
     * @param phoneNumber Contact's phone number.
     */
    void setContact(String name, int phoneNumber);
    
    /**
     * This method edits contact with whom the task is connected.
     * The constructor - Contact(name, phoneNumber, email) is called.
     * @param name Contact's name.
     * @param phoneNumber Contact's phone number.
     * @param email Contact's email.
     */
    void setContact(String name, int phoneNumber, String email);
    
    /**
     * This method set task's status:
     * 10 - active,
     * 20 - postponed,
     * 30 - complete.
     * @param Status New task's status.
     */
    void setStatus(short Status);
    
    /**
     * This method set task's priority.
     * @param priority New task's priority.
     */
    void setPriority(int priority);
    
}
