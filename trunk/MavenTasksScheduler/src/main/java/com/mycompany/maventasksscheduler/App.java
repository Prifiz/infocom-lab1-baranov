package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.logmodel.Birthday;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.Contact;
import com.mycompany.maventasksscheduler.logmodel.LogModel;
import com.mycompany.maventasksscheduler.logmodel.Taskable;
import java.util.LinkedList;
import org.joda.time.DateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //для проверки работы сортировки, вывода и поиска
        LogModel lm = new LogModel();
        Contact c1 = new Contact("raf");
        Contact c2 = new Contact("max");
        Contact c3 = new Contact("serg");
        DateTime dt1 = new DateTime(2000,10,10, 21,10);
        DateTime dt2 = new DateTime(2009,10,10, 11,10);
        DateTime dt3 = new DateTime(2009,10,10, 21,10);
        DateTime dt4 = new DateTime(3009,10,10, 21,10);
        DateTime dt5 = new DateTime(1009,10,10, 21,10);
        Birthday b1 = new Birthday(dt2,c1,5);
        Birthday b2 = new Birthday(dt3,c2,5);
        Birthday b3 = new Birthday(dt1,c3,5);
        BusinessTask bt1 = new BusinessTask("kursach", dt3, 5);
        BusinessTask bt2 = new BusinessTask("laba", dt5, 5);
        BusinessTask bt3 = new BusinessTask("final basketball games", dt4, 5);
        lm.add(b1);
        lm.add(b2);
        lm.add(b3);
        lm.add(bt1);
        lm.add(bt2);
        lm.add(bt3);
        lm.showAll();
        lm.sortByDate();
        System.out.println("отсортированные задачи");
        lm.showAll();
        System.out.println("поиск задач");
        LinkedList<Taskable> ll = lm.search(dt2);
        for(int i = 0; i < ll.size(); i++)
            System.out.println(ll.get(i).toString());
        
    }
}
