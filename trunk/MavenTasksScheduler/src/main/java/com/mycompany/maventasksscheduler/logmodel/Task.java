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
public abstract class Task {
    
    public DateTime timeNotification;
    public Contact contact;
    public enum Status {ACTIVE, POSTPONED, COMPLETE};
    public Status status;
    public enum Priority { URGENT_IMPORTANT, URGENT, IMPORTANT};
    public Priority priority;
    public int fieldCount;
      
    
    public int getFieldCount(){
        return fieldCount;
    }
    
    /**
     * Returns date and time notification about task.
     * @return A Date notification.
     */
    public DateTime getDate(){
        return timeNotification;
    }
    /**
     * Returns contact with whom the task is connected.
     * @return A Contact.
     */
    public Contact getContact(){
        return contact;
    }
    
    /**
     * Returns task's status: 
     * active,
     * postponed,
     * complete.
     * @return A short containing 10|20|30.
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * Returns task's Priority.
     * @return A int containing task's priority.
     */
    public Priority getPriority() {
        return priority;
    }
    
    
    /**
     * This method set task's date notification.
     * @param date Date notification about task.
     */
    public void setDate(DateTime d){
        timeNotification = d;
    }
    
    /**
     * This method edits contact with whom the task is connected.
     * @param contact Task's contact.
     */
    public void setContact(Contact c){
        contact = c;
    }
    
    /**
     * This method edits contact with whom the task is connected. 
     * The constructor - Contact(name) is called.
     * @param name Contact's name.
     */
    public void setContact(String name){
        contact = new Contact(name);
    }
    
    /**
     * This method edits contact with whom the task is connected.
     * The constructor - Contact(name, phoneNumber) is called.
     * @param name Contact's name.
     * @param phoneNumber Contact's phone number.
     */
    public void setContact(String name, long phoneNumber){
        contact = new Contact(name,phoneNumber);
    }
    
    /**
     * This method edits contact with whom the task is connected.
     * The constructor - Contact(name, phoneNumber, email) is called.
     * @param name Contact's name.
     * @param phoneNumber Contact's phone number.
     * @param email Contact's email.
     */
    public void setContact(String name, long phoneNumber, String email){
        contact = new Contact(name, phoneNumber, email);
    }
    
    /**
     * This method set task's status:
     * 10 - active,
     * 20 - postponed,
     * 30 - complete.
     * @param Status New task's status.
     */
    public void setStatus(Status status) {
       this.status = status;
    }
    
    /**
     * This method set task's priority.
     * @param priority New task's priority.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    
    public int compareTo(Priority priority){
        if((this.priority == Priority.URGENT_IMPORTANT &&
            (priority == Priority.URGENT || priority == Priority.IMPORTANT))|| 
             (this.priority == Priority.URGENT && priority == Priority.IMPORTANT))
            return -1;
        else if((this.priority == Priority.IMPORTANT &&
            (priority == Priority.URGENT || priority == Priority.URGENT_IMPORTANT))|| 
             (this.priority == Priority.URGENT && priority == Priority.URGENT_IMPORTANT))
            return 1;
        return 0;
    }
      
}
