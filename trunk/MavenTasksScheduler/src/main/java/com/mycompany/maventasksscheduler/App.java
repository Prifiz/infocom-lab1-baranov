package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.controller.Controller;
import com.mycompany.maventasksscheduler.logmodel.Birthday;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.Contact;
import com.mycompany.maventasksscheduler.logmodel.LogModel;
import com.mycompany.maventasksscheduler.logmodel.Taskable;
import java.util.LinkedList;
import java.util.Scanner;
import org.joda.time.DateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        Controller c = new Controller();
        menu:
        for(;;){
            System.out.println("\nYou in main menu, choose menu point:"
                    + "\n1. Add task"
                    + "\n2. Show tasks"
                    + "\n3. Search tasks"
                    + "\n4. Sort tasks by date"
                    + "\n5. Edit all task's data"
                    + "\n6. Remove task"
                    + "\n0. Exit\n");
            switch(sc.nextInt()){
                case 1:
                    c.add();
                    break;
                case 2:
                    c.showAll();
                    break;
                case 3:
                    c.searchTask();
                    break;
                case 4:
                    c.sortTaslList();
                    break;
                case 5:
                    c.editAllDataTask();
                    break;
                case 6:
                    c.remove();
                    break;
                case 0:
                   break menu; 
            }
        }
    
    }
}
