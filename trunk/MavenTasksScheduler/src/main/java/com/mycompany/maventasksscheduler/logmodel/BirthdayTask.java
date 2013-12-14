/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import org.joda.time.DateTime;



/**
 * This class describes task - birthday.
 * @author Сергей осталось конструкторы
 */
public class BirthdayTask extends Task implements Cloneable{
    
   
    /**
     * This constructor creates object Birthday with date, contact, priority.
     * @param date Birthday's time notification.
     * @param contact Birthday of the contact.
     * @param priority Birthday's priority.
     */
    public BirthdayTask(DateTime timeNotification, Contact contact, Priority priority){
        this.timeNotification = timeNotification;
        this.contact = contact;
        this.status = Status.ACTIVE;
        this.priority = priority;
    }
  
    /**
     * Returns information about Birthday.
     * @return contact and timeNotification
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(! (timeNotification.equals(new DateTime()) && contact.equals(new Contact())))
            return sb.append("Birthday of ").append(contact.toString()).
                    append(" will be ").
                    append(timeNotification.getDayOfMonth()).append("-").
                    append(timeNotification.getMonthOfYear()).append("-").
                    append(timeNotification.getYear()).
                    append(", time notification - ").
                    append(timeNotification.getHourOfDay()).append(":").
                    append(timeNotification.getMinuteOfHour()).toString();              
        return sb.append("null").toString();
    }

    /**
     * Compares this object with the specified object.
     * @param o object for check.
     * @return true if objects are equal and both belongs Birthday,
     * false - in all other cases.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof BirthdayTask && 
                 this.timeNotification.equals(((BirthdayTask) o).timeNotification) &&
                 this.contact.equals(((BirthdayTask) o).contact) && 
                 this.status == ((BirthdayTask) o).status &&
                 this.priority == ((BirthdayTask) o).priority;
    }

    /**
     * Generate hashCode for this object
     * @return hashCode which generate for this object.
     */
    @Override
    public int hashCode() {
        return timeNotification.hashCode() + contact.hashCode();
    }

    /**
     * Returns the object copy.
     * @return the object copy
     */
    @Override
    public Object clone() {
        BirthdayTask result = null;
         try {
             result = (BirthdayTask) super.clone();
             result.contact = (Contact) contact.clone();
         } catch (CloneNotSupportedException ex) {
         }
         return result;
    }

}
