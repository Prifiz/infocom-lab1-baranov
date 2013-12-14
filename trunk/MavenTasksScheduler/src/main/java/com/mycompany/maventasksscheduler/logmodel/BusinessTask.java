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
public class BusinessTask extends Task  implements Cloneable {

    private String taskName;
    private String description;
  
    
    /**
     * This constructor creates BusinessTask with taskName, date, priority.
     *
     * @param taskName BusinessTask's name.
     * @param timeNotification BusinessTask's time notification.
     * @param priority BusinessTask's priority.
     */
    public BusinessTask(String taskName, DateTime timeNotification, 
            Priority priority) {
        this.taskName = taskName;
        this.description = "";
        this.timeNotification = timeNotification;
        this.contact = new Contact();
        this.status = Status.ACTIVE;
        this.priority = priority;
    }

    /**
     * This constructor creates BusinessTask with taskName, description,
     * date, priority.
     *
     * @param taskName BusinessTask's name.
     * @param description BusinessTask's description.
     * @param timeNotification BusinessTask's time notification.
     * @param priority BusinessTask's priority.
     */
    public BusinessTask(String taskName, String description, 
            DateTime timeNotification, Priority priority) {
        this.taskName = taskName;
        this.description = description;
        this.timeNotification = timeNotification;
        this.contact = new Contact();
        this.status = Status.ACTIVE;
        this.priority = priority;
    }

    /**
     * This constructor creates BusinessTask with taskName, description,
     * date, contact, priority.
     *
     * @param taskName BusinessTask's name.
     * @param description BusinessTask's description.
     * @param timeNotification BusinessTask's time notification.
     * @param contact BusinessTask's contact.
     * @param priority BusinessTask's priority.
     */
    public BusinessTask(String taskName, String description, 
            DateTime timeNotification, Contact contact, Priority priority) {
        this.taskName = taskName;
        this.description = description;
        this.timeNotification = timeNotification;
        this.contact = contact;
        this.status = Status.ACTIVE;
        this.priority = priority;
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

    /**
     * Returns information about BusinessTask.
     *
     * @return taskName and timeNotification or taskName and
     * description and timeNotification or taskName and description and
     * contact and timeNotification
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (taskName.length() > 0 && !timeNotification.equals(new DateTime())) {
            return sb.append("Your task - ").append(taskName).
                    append(", date notification - ").
                    append(timeNotification.getDayOfMonth()).append("-").
                    append(timeNotification.getMonthOfYear()).append("-").
                    append(timeNotification.getYear()).
                    append(", time notification - ").
                    append(timeNotification.getHourOfDay()).append(":").
                    append(timeNotification.getMinuteOfHour()).toString();
        }
        else if (taskName.length() > 0 && description.length() > 0
                && !timeNotification.equals(new DateTime())) {
            return sb.append("Your task - ").append(taskName).
                    append(", description - ").append(description).
                    append(", date notification - ").
                    append(timeNotification.getDayOfMonth()).append("-").
                    append(timeNotification.getMonthOfYear()).append("-").
                    append(timeNotification.getYear()).
                    append(", time notification - ").
                    append(timeNotification.getHourOfDay()).append(":").
                    append(timeNotification.getMinuteOfHour()).toString();
        }
        else if (taskName.length() > 0 && description.length() > 0
                && !timeNotification.equals(new DateTime())
                && !contact.equals(new Contact())) {
            return sb.append("Your task - ").append(taskName).
                    append(", description - ").append(description).
                    append(", it is connected with ").append(contact.toString()).
                    append(", date notification - ").
                    append(timeNotification.getDayOfMonth()).append("-").
                    append(timeNotification.getMonthOfYear()).append("-").
                    append(timeNotification.getYear()).
                    append(", time notification - ").
                    append(timeNotification.getHourOfDay()).append(":").
                    append(timeNotification.getMinuteOfHour()).toString();
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
        return o instanceof BusinessTask && 
                this.taskName.equals(((BusinessTask) o).taskName)
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
        return (taskName == null ? 0 : taskName.hashCode())
                + (description == null ? 0 : description.hashCode()) + timeNotification.hashCode()
                + contact.hashCode();
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
