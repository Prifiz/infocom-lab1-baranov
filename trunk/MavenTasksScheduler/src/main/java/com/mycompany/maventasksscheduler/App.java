package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.controller.MainController;
import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        XMLStorage xml = new XMLStorage();
        //xml.saveData(null);
        MainController c = new MainController();
        c.start();
    }
}
