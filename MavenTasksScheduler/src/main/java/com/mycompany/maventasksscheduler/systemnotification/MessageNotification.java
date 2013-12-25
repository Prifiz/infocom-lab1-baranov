/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.systemnotification;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.userinterface.MainConsoleUI;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */
public class MessageNotification implements SystemNotification {
    
    private LogImpl task;
    private LogImpl logModel;
    private DateTime dateTime;
    private XMLStorage xml;
    private MainConsoleUI userInterface;
    
    
    public MessageNotification(){
        xml = new XMLStorage(); 
        this.logModel = xml.uploadData();
        task = new LogImpl();
        dateTime = new DateTime();
        userInterface = new MainConsoleUI();
    }

    public LogImpl notification() {
        for(int i = 0; i < logModel.getSize(); i++){
            if(logModel.get(i).getDate().getYear() == dateTime.getYear() &&
                    logModel.get(i).getDate().getMonthOfYear() == 
                    dateTime.getMonthOfYear() && 
                    logModel.get(i).getDate().getDayOfMonth() == 
                    dateTime.getDayOfMonth()){
                task.add(logModel.get(i));
            }
        }
        return task;
    }
    
    
}
