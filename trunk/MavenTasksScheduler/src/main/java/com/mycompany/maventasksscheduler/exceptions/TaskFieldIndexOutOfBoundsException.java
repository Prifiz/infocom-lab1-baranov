/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.exceptions;

/**
 *
 * @author Сергей
 */
public class TaskFieldIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public TaskFieldIndexOutOfBoundsException(){
        super("У выбранной задачи нет поля с таким номером");
    }
}
