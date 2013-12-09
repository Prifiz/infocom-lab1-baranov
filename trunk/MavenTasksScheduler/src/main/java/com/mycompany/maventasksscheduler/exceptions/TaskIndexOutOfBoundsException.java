/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.exceptions;

/**
 *
 * @author Сергей
 */
public class TaskIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public TaskIndexOutOfBoundsException(){
        super("Задачи с таким номером не существует");
    }
}
