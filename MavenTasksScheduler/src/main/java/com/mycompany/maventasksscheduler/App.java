package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.controller.Controller;
import java.util.Scanner;

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
                    + "\n4. Edit all task's data"
                    + "\n5. Remove task"
                    + "\n0. Exit\n");
            switch(sc.nextInt()){
                case 1:
                    c.add();
                    break;
                case 2:
                    c.sortTaslList();
                    c.showAll();
                    break;
                case 3:
                    c.searchTask();
                    break;
                case 4:
                    c.editAllDataTask();
                    break;
                case 5:
                    c.remove();
                    break;
                case 0:
                   break menu; 
            }
        }
    
    }
}
