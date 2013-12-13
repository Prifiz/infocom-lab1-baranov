/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.exceptions;

/**
 *
 * @author Сергей
 */
public class BadEnteredDate extends IllegalArgumentException{
    public BadEnteredDate(){
        super("date or time isn't correctly entered");
    }  
}
