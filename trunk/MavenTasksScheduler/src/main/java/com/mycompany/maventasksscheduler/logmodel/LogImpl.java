/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import com.mycompany.maventasksscheduler.exceptions.BadEnteredDate;
import com.mycompany.maventasksscheduler.exceptions.TaskFieldIndexOutOfBoundsException;
import com.mycompany.maventasksscheduler.exceptions.TaskIndexOutOfBoundsException;
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

    public LogImpl() {
        log = new LinkedList();
    }

    /**
     * Returns count task in LogModel
     *
     * @return int - count task
     */
    public int getSize() {
        return log.size();
    }

    
    public void showAll() {
        for (int i = 0; i < log.size(); i++) {
            System.out.println("№ - " + i + ", " + log.get(i).toString());
        }
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
                return 0;// c2.getPriority().compareTo(c1.getPriority());
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
        if (taskId < 0 || taskId > log.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        if (log.get(taskId) instanceof BirthdayTask) {
            for (int i = 0; i < 4; i++) {
                editSomeFieldOfTask(taskId, i + 1);
            }
        }
        if (log.get(taskId) instanceof BusinessTask) {
            for (int i = 0; i < 6; i++) {
                editSomeFieldOfTask(taskId, i + 1);
            }
        }
    }

    @Override
    public void editSomeFieldOfTask(int taskId, int fieldNumber) {
        if (taskId < 0 || taskId > log.size()) {
            throw new TaskIndexOutOfBoundsException();
        }
        if (log.get(taskId) instanceof BirthdayTask) {
            if (fieldNumber < 1 || fieldNumber > 4) {
                throw new TaskFieldIndexOutOfBoundsException();
            }
            setEditSomeFieldOfBirthday(taskId, fieldNumber);
        }
        if (log.get(taskId) instanceof BusinessTask) {
            if (fieldNumber < 1 || fieldNumber > 6) {
                throw new TaskFieldIndexOutOfBoundsException();
            }
            setEditSomeFieldOfBusinessTask(taskId, fieldNumber);
        }
    }

    /*
     * module for edit some field of Birthday
     */
    private void setEditSomeFieldOfBirthday(int taskId, int fieldNumber) {
        Scanner sc = new Scanner(System.in);
        switch (fieldNumber) {
            case 1:
                log.get(taskId).setDate(createDate());
                break;
            case 2:
                log.get(taskId).setContact(setContactInfo());
                break;
            case 3:
                System.out.println("Enter the new status:");
                log.get(taskId).setStatus(sc.nextShort());
                break;
            case 4:
                System.out.println("Enter the new priority:");
                log.get(taskId).setPriority(sc.nextInt());
                break;
                default:
        }
    }

    /*
     * module for edit some field of BusinessTask
     */
    private void setEditSomeFieldOfBusinessTask(int taskId, int fieldNumber) {
        Scanner sc = new Scanner(System.in);
        switch (fieldNumber) {
            case 1:
                System.out.println("Enter the new task's name:");
                BusinessTask bt = (BusinessTask) log.get(taskId);
                bt.setTaskName(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter the new task's description:");
                BusinessTask bt1 = (BusinessTask) log.get(taskId);
                bt1.setDescription(sc.nextLine());
                break;
            case 3:
                log.get(taskId).setDate(createDate());
                break;
            case 4:
                log.get(taskId).setContact(setContactInfo());
                break;
            case 5:
                System.out.println("Enter the new status:");
                log.get(taskId).setStatus(sc.nextShort());
                break;
            case 6:
                System.out.println("Enter the new priority:");
                log.get(taskId).setPriority(sc.nextInt());
                break;
        }
    }

    /*
     * for not to duplicate general part in setEditSomeFieldOfBusinessTask and 
     * setEditSomeFieldOfBirthday took out in a separate method
     */
    public DateTime createDate() throws BadEnteredDate {
        Scanner sc = new Scanner(System.in);
        String date;
        String[] splitDate = new String[3];
        while (true) {
            System.out.println("Enter correctly date, example:\n9.12.2013 or 9-12-2013, or 9/12/2013");
            date = sc.nextLine();
            if (date.contains("")) {
                splitDate = date.split("\\.");
            }
            else if (date.contains("-")) {
                splitDate = date.split("-");
            }
            else if (date.contains("/")) {
                splitDate = date.split("/");
            }
            if (Integer.parseInt(splitDate[2]) >= 2013 && Integer.parseInt(splitDate[1]) > 0
                    && Integer.parseInt(splitDate[1]) < 13 && Integer.parseInt(splitDate[0]) > 0
                    && Integer.parseInt(splitDate[0]) < 32) {
                break;
            }
        }
        String[] splitTime = new String[2];
        while (true) {
            System.out.println("Enter correctly time description, example:\n 20:44");
            date = sc.nextLine();
            if (date.contains(":")) {
                splitTime = date.split(":");
            }
            if (Integer.parseInt(splitTime[0]) >= 0 && Integer.parseInt(splitTime[0]) < 25
                    && Integer.parseInt(splitTime[1]) >= 0 && Integer.parseInt(splitTime[1]) < 60) {
                break;
            }
        }
        return new DateTime(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]),
                Integer.parseInt(splitDate[0]), Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
    }

    /*
     * for not to duplicate general part in setEditSomeFieldOfBusinessTask and 
     * setEditSomeFieldOfBirthday took out in a separate method
     */
    private Contact setContactInfo() {
        Scanner sc = new Scanner(System.in);
        Contact contact = null;
        System.out.println("What information on contact you want to enter?");
        System.out.println("1. Only name.");
        System.out.println("2. Name and phone number.");
        System.out.println("3. Name, phone number and email.");
        String name;
        long phoneNumber;
        String email;
        int read = sc.nextInt();
        sc.nextLine();
        boolean flag = true;
        switch (read) {
            case 1:
                System.out.println("Enter name:");
                name = sc.nextLine();
                contact = new Contact(name);
                flag = false;
                break;
            case 2:
                System.out.println("Enter name:");
                name = sc.nextLine();
                System.out.println("Enter phone number:");
                phoneNumber = sc.nextLong();
                contact = new Contact(name, phoneNumber);
                flag = false;
                break;
            case 3:
                System.out.println("Enter name:");
                name = sc.nextLine();
                System.out.println("Enter phone number:");
                phoneNumber = Long.parseLong(sc.nextLine());
                System.out.println("Enter email:");
                email = sc.nextLine();
                contact = new Contact(name, phoneNumber, email);
                flag = false;
                break;
        }
        return contact;
    }

    /**
     * This method create new task and add it in log.
     */
    public void createTask() {
        System.out.println("Choose task type:\n1. Birthday\n2. Business task");
        Scanner sc = new Scanner(System.in);
        switch (sc.nextInt()) {
            case 1:
                sc.nextLine();
                System.out.println("Enter priority:");
                int priority = sc.nextInt();
                add(new BirthdayTask(createDate(), setContactInfo(), priority));
                break;
            case 2:
                System.out.println("Create business task");
                break;
            default:
                System.out.println("Choose correctly task type ");
        }
    }
}