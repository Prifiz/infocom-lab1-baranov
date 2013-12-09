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
public class Birthday implements Taskable, Cloneable{
    
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
    public Birthday(DateTime date, Contact contact, int priority){
        timeNotification = date;
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
            return sb.append("id - "+id+", "+"Birthday of "+ contact.toString() +" will be "+
                   timeNotification.getYear()+"-"+timeNotification.getMonthOfYear()+
                    "-"+timeNotification.getDayOfMonth()).toString();
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
        return o instanceof Birthday && this.id == ((Birthday) o).id && 
                 this.timeNotification.equals(((Birthday) o).timeNotification) &&
                 this.contact.equals(((Birthday) o).contact) && 
                 this.status == ((Birthday) o).status &&
                 this.priority == ((Birthday) o).priority;
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
        Birthday result = null;
         try {
             result = (Birthday) super.clone();
             result.contact = (Contact) contact.clone();
         } catch (CloneNotSupportedException ex) {
         }
         return result;
    }

}
