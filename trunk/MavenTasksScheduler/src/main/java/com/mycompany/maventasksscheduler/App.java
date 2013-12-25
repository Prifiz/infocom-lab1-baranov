package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.controller.MainController;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("\nWelcome to your scheduler of tasks");
        MainController c = new MainController();
        c.start();
    }
}
