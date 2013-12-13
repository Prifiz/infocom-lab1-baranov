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
public class BirthdayTask implements Task, Cloneable{
    
    private int id; 
    private DateTime timeNotification;
    private Contact contact;
    private short status;
    private int priority;
   
    /**
     * This constructor creates object Birthday with id, date, contact, priority.
     * @param date Birthday's time notification.
     * @param contact Birthday of the contact.
     * @param priority Birthday's priority.
     */
    public BirthdayTask(DateTime timeNotification, Contact contact, int priority){
        this.timeNotification = timeNotification;
        this.contact = contact;
        status = 10;
        this.priority = priority;
    }
       
    public int getId() {
        return id;
    }
    
    public DateTime getDate(){
        return timeNotification;
    }
    
    public Contact getContact(){
        return contact;
    }
    
    @Override
    public short getStatus() {
        return status;
    }

    @Override
    public int getPriority() {
        return priority;
    }
    
   @Override
    public void setId(int id) {
       this.id = id;
    }
    
    public void setDate(DateTime d){
        timeNotification = d;
    }
    
    public void setContact(Contact c){
        contact = c;
    }
    
    public void setContact(String name){
        contact = new Contact(name);
    }
    
    public void setContact(String name, int phoneNumber){
        contact = new Contact(name,phoneNumber);
    }
    
    public void setContact(String name, int phoneNumber, String email){
        contact = new Contact(name, phoneNumber, email);
    }

   @Override
    public void setStatus(short status) {
       this.status = status;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    /**
     * Returns information about Birthday.
     * @return id and contact and timeNotification
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if(! (timeNotification.equals(new DateTime()) && contact.equals(new Contact())))
            return sb.append("Birthday of "+ contact.toString() +" will be "+
                   timeNotification.getYear()+"-"+timeNotification.getMonthOfYear()+
                    "-"+timeNotification.getDayOfMonth()+ ", time notification - "+
                    timeNotification.getHourOfDay()+":"+ timeNotification.getMinuteOfHour()).toString();
                    
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
        return o instanceof BirthdayTask && this.id == ((BirthdayTask) o).id && 
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
        return id + timeNotification.hashCode() + contact.hashCode() + status + priority;
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
