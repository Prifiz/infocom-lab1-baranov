/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.logmodel;

import com.mycompany.maventasksscheduler.exceptions.TaskFieldIndexOutOfBoundsException;
import com.mycompany.maventasksscheduler.exceptions.TaskIndexOutOfBoundsException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class LogModel implements WorkWithLogModelable, Cloneable{
    
    private LinkedList <Taskable> log;
    private int id;
    
    public LogModel(){
        log = new LinkedList();
        id = 0;
    }
    
    /**
     * Returns count task in LogModel
     * @return int - count task
     */
    public int getId(){
        return id;
    }
   
    @Override
    public void showAll() {
        for(int i = 0; i < id; i++)
             System.out.println("id - "+i+", "+log.get(i).toString());
    }

    @Override
    public void add(Taskable task) {
        task.setId(id);
        log.add(task);
        id++;
    }

    @Override
    public void remove(int taskNumber) {
        if(taskNumber < 0 || taskNumber >= id)
            throw new TaskIndexOutOfBoundsException();
        log.remove(taskNumber);
        id--;
    }

    @Override
    public LinkedList<Taskable> search(DateTime date) {
        if(log.size() == 0)
            return null;
        else{
            //Iterator<Taskable> iter = log.iterator();
            LinkedList<Taskable> foundTasks = new LinkedList();
//            while(iter.hasNext()){ ищет не все даты, когда использую iterator
//                if(iter.next().getDate() == date)
//                    foundTasks.add(iter.next());
//            }
            for(int i = 0; i < log.size(); i++)
                if(log.get(i).getDate().getYear() == date.getYear() &&
                        log.get(i).getDate().getMonthOfYear() == date.getMonthOfYear() &&
                        log.get(i).getDate().getDayOfMonth() == date.getDayOfMonth())
                    foundTasks.add(log.get(i));
            return foundTasks;
        }
    }


    @Override
    public void sortByDate() {
        Comparator<Taskable> comparator = new Comparator<Taskable>() {
            public int compare(Taskable c1, Taskable c2) {
                return c2.getDate().compareTo(c1.getDate());
            }
        };
        Collections.sort(log, comparator);
    }

    /**
     * Causes necessary number of times editSomeFieldOfTask.
     * @param taskId Id of an edited task.
     */
    @Override
    public void editAllDataTask(int taskId) {
        if(taskId < 0 || taskId > id)
            throw new TaskIndexOutOfBoundsException();
        
        if(log.get(taskId) instanceof Birthday){
            for(int i = 0; i < 4; i++)
                editSomeFieldOfTask(taskId, i+1);
        }
        if(log.get(taskId) instanceof BusinessTask){
             for(int i = 0; i < 6; i++)
                editSomeFieldOfTask(taskId, i+1);
        }
    }

    @Override
    public void editSomeFieldOfTask(int taskId, int fieldNumber) {
        if(taskId < 0 || taskId > id)
            throw new TaskIndexOutOfBoundsException();
        if(log.get(taskId) instanceof Birthday){
            if(fieldNumber < 1 || fieldNumber > 4)
                throw new TaskFieldIndexOutOfBoundsException();
            setEditSomeFieldOfBirthday(taskId, fieldNumber);
        }
        if(log.get(taskId) instanceof BusinessTask){
            if(fieldNumber < 1 || fieldNumber > 6)
                throw new TaskFieldIndexOutOfBoundsException();
            setEditSomeFieldOfBusinessTask(taskId, fieldNumber);
        }
    }
    
    /*
     * module for edit some field of Birthday
     */
    private void setEditSomeFieldOfBirthday(int taskId, int fieldNumber){
        Scanner sc = new Scanner(System.in);
        switch(fieldNumber){
                case 1:
                    log.get(taskId).setDate(createDate());
                    break;
                case 2:
                    log.get(taskId).setContact(setContactInfo());
                    break;
                case 3:
                    System.out.println("Введите новый статус:");
                    log.get(taskId).setStatus(sc.nextShort());
                    break;
                case 4:
                    System.out.println("Введите новый приоритет:");
                    log.get(taskId).setPriority(sc.nextInt());
                    break;
        }
    }
    
    /*
     * module for edit some field of BusinessTask
     */
    private void setEditSomeFieldOfBusinessTask(int taskId, int fieldNumber){
        Scanner sc = new Scanner(System.in);
        switch(fieldNumber){
                case 1:
                    System.out.println("Введите новое название задачи:");
                    BusinessTask bt = (BusinessTask) log.get(taskId);
                    bt.setTaskName(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Введите новое описание задачи:");
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
                    System.out.println("Введите новый статус:");
                    log.get(taskId).setStatus(sc.nextShort());
                    break;
                case 6:
                    System.out.println("Введите новый приоритет:");
                    log.get(taskId).setPriority(sc.nextInt());
                    break;
            }
    }
    
    /*
     * for not to duplicate general part in setEditSomeFieldOfBusinessTask and 
     * setEditSomeFieldOfBirthday took out in a separate method
     */
    public DateTime createDate(){
        System.out.println("Введите дату, например:\n9.12.2013 или 9-12-2013, или 9/12/2013");
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        String [] splitDate = new String[3];
        if(date.contains(""))
            splitDate = date.split("\\.");
        if(date.contains("-"))
            splitDate = date.split("-");
        if(date.contains("/"))
            splitDate = date.split("/");
        
        System.out.println("Введите время оповещения, например:\n 20:44");
        date = sc.nextLine();
        String [] splitTime = new String[2];
        if(date.contains(":"))
            splitTime = date.split(":");
        return new DateTime(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), 
                Integer.parseInt(splitDate[0]), Integer.parseInt(splitTime[0]),Integer.parseInt(splitTime[1]));
    }
    
    /*
     * for not to duplicate general part in setEditSomeFieldOfBusinessTask and 
     * setEditSomeFieldOfBirthday took out in a separate method
     */
    private Contact setContactInfo(){
        Scanner sc = new Scanner(System.in);
        Contact contact = null;
        System.out.println("Какую информацию о контакте хотите ввести?");
                    System.out.println("1. Только имя.");
                    System.out.println("2. Имя и номер телефона.");
                    System.out.println("3. Имя, номер телефона и email.");
                    String name;
                    int phoneNumber;
                    String email;
                    int read = sc.nextInt();
                    sc.nextLine();
                    switch(read){
                        case 1:
                            System.out.println("Введите имя:");
                            name = sc.nextLine();
                            contact = new Contact(name);
                            break;
                        case 2:
                            System.out.println("Введите имя:");
                            name = sc.nextLine();
                            System.out.println("Введите телефон:");
                            phoneNumber = sc.nextInt();
                            contact = new Contact(name, phoneNumber);
                            break;
                        case 3:
                            System.out.println("Введите имя:");
                            name = sc.nextLine();
                            System.out.println("Введите телефон:");
                            phoneNumber = Integer.parseInt(sc.nextLine());
                            System.out.println("Введите email:");
                            email = sc.nextLine();
                            contact = new Contact(name, phoneNumber, email);
                   }
    return contact;
    }
    
    /**
     * This method create new task and add it in log.
     */
    public void createTask(){
        System.out.println("Choose task type:\n1. Birthday\n2. Business task");
        Scanner sc = new Scanner(System.in);
        switch(sc.nextInt()){
            case 1:
                sc.nextLine();
                System.out.println("Введите приоритет:");
                int priority = sc.nextInt();
                add(new Birthday(createDate(), setContactInfo(), priority));
                break;
            case 2:
                System.out.println("создание бизнес задачи");
                break;
            default:
                System.out.println("Choose correctly task type ");
        }
    }
    
}