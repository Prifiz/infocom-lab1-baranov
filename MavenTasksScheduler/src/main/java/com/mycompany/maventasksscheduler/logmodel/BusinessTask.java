/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import org.joda.time.DateTime;

/**
 * This class describes BusinessTask.
 *
 * @author Сергей остаётся конструкторы
 */
public class BusinessTask implements Task, Cloneable {

    private int id;
    private String taskName;
    private String description;
    private DateTime timeNotification;
    private Contact contact;
    private short status;
    private int priority;

    /**
     * This constructor creates BusinessTask with id, taskName, date, priority.
     *
     * @param taskName BusinessTask's name.
     * @param date BusinessTask's time notification.
     * @param priority BusinessTask's priority.
     */
    public BusinessTask(String taskName, DateTime date, int priority) {
        this.taskName = taskName;
        description = null;
        timeNotification = date;
        contact = null;
        status = 10;
        this.priority = priority;
    }

    /**
     * This constructor creates BusinessTask with id, taskName, description,
     * date, priority.
     *
     * @param taskName BusinessTask's name.
     * @param description BusinessTask's description.
     * @param date BusinessTask's time notification.
     * @param priority BusinessTask's priority.
     */
    public BusinessTask(String taskName, String description, DateTime date, int priority) {
        this.taskName = taskName;
        this.description = description;
        timeNotification = date;
        contact = null;
        status = 10;
        this.priority = priority;
    }

    /**
     * This constructor creates BusinessTask with id, taskName, description,
     * date, contact, priority.
     *
     * @param taskName BusinessTask's name.
     * @param description BusinessTask's description.
     * @param date BusinessTask's time notification.
     * @param contact BusinessTask's contact.
     * @param priority BusinessTask's priority.
     */
    public BusinessTask(String taskName, String description, DateTime date,
            Contact contact, int priority) {
        this.taskName = taskName;
        this.description = description;
        timeNotification = date;
        this.contact = contact;
        status = 10;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns task's name.
     *
     * @return A String containing the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns task's description.
     *
     * @return A String containing the task's description.
     */
    public String getDescription() {
        return description;
    }

    public DateTime getDate() {
        return timeNotification;
    }

    public Contact getContact() {
        return contact;
    }

    public short getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method set new task's name.
     *
     * @param name The name of task.
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * This method set new task's description.
     *
     * @param description The description of task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(DateTime date) {
        timeNotification = date;
    }

    public void setContact(Contact c) {
        contact = c;
    }

    public void setContact(String name) {
        contact = new Contact(name);
    }

    public void setContact(String name, int phoneNumber) {
        contact = new Contact(name, phoneNumber);
    }

    public void setContact(String name, int phoneNumber, String email) {
        contact = new Contact(name, phoneNumber, email);
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns information about BusinessTask.
     *
     * @return id and taskName and timeNotification or id and taskName and
     * description and timeNotification or id and taskName and description and
     * contact and timeNotification
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (taskName.length() > 0 && !timeNotification.equals(new DateTime())) {
            return sb.append("Your task - " + taskName
                    + ", time notification - " + timeNotification.getYear() + "-"
                    + timeNotification.getMonthOfYear() + "-"
                    + timeNotification.getDayOfMonth() + ", time notification - "
                    + timeNotification.getHourOfDay() + ":"
                    + timeNotification.getMinuteOfHour()).toString();
        }
        else if (taskName.length() > 0 && description.length() > 0
                && !timeNotification.equals(new DateTime())) {
            return sb.append("Your task - " + taskName
                    + ", description - " + description + ", time notification - "
                    + timeNotification.getYear() + "-"
                    + timeNotification.getMonthOfYear() + "-"
                    + timeNotification.getDayOfMonth() + ", time notification - "
                    + timeNotification.getHourOfDay() + ":"
                    + timeNotification.getMinuteOfHour()).toString();
        }
        else if (taskName.length() > 0 && description.length() > 0
                && !timeNotification.equals(new DateTime())
                && !contact.equals(new Contact())) {
            return sb.append("Your task - " + taskName
                    + ", description - " + description
                    + ", it is connected with " + contact.toString()
                    + ", time notification - " + timeNotification.getYear() + "-"
                    + timeNotification.getMonthOfYear() + "-"
                    + timeNotification.getDayOfMonth() + ", time notification - "
                    + timeNotification.getHourOfDay() + ":"
                    + timeNotification.getMinuteOfHour()).toString();
        }
        return sb.append("null").toString();
    }

    /**
     * Compares this object with the specified object.
     *
     * @param o object for check.
     * @return true if objects are equal and both belongs BusinessTask, false -
     * in all other cases.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof BusinessTask && this.id == ((BusinessTask) o).id
                && this.taskName.equals(((BusinessTask) o).taskName)
                && this.description.equals(((BusinessTask) o).description)
                && this.timeNotification.equals(((BusinessTask) o).timeNotification)
                && this.contact.equals(((BusinessTask) o).contact)
                && this.status == ((BusinessTask) o).status
                && this.priority == ((BusinessTask) o).priority;
    }

    /**
     * Generate hashCode for this object
     *
     * @return hashCode which generate for this object.
     */
    @Override
    public int hashCode() {
        return id + (taskName == null ? 0 : taskName.hashCode())
                + (description == null ? 0 : description.hashCode()) + timeNotification.hashCode()
                + contact.hashCode() + status + priority;
    }

    /**
     * Returns the object copy.
     *
     * @return the object copy
     */
    @Override
    public Object clone() {
        BusinessTask result = null;
        try {
            result = (BusinessTask) super.clone();
            result.contact = (Contact) contact.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return result;
    }
}
