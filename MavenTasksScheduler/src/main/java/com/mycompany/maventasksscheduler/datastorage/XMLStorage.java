/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.datastorage;

import com.mycompany.maventasksscheduler.logmodel.BirthdayTask;
import com.mycompany.maventasksscheduler.logmodel.BusinessTask;
import com.mycompany.maventasksscheduler.logmodel.Contact;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import com.mycompany.maventasksscheduler.logmodel.Task;
import com.mycompany.maventasksscheduler.logmodel.Task.Priority;
import java.io.FileWriter;  
import java.io.IOException;  
import org.jdom2.Attribute;  
import org.jdom2.Document;  
import org.jdom2.Element;  
import org.jdom2.output.Format;  
import org.jdom2.output.XMLOutputter;  

import java.io.File; 
import java.util.LinkedList;
import java.util.List; 
import org.jdom2.JDOMException;  
import org.jdom2.input.SAXBuilder; 
import org.joda.time.DateTime;

/**
 *
 * @author Сергей
 */

public class XMLStorage implements Storage {

    public void saveData(LogImpl logModel) {
        try {  
            Element businessTasks = new Element("tasks");  
            Element birthdayTasks = new Element("tasks");  
            Document businessTask = new Document(businessTasks);
            Document birthdayTask = new Document(birthdayTasks);
            Integer i;
            StringBuilder sb = new StringBuilder();
            for(i = 0; i < logModel.getSize(); i++){
                Element task = new Element(logModel.get(i).getClass().
                        getSimpleName().toLowerCase());  
                task.setAttribute(new Attribute("id", i.toString()));
                
                if(logModel.get(i) instanceof BirthdayTask){
                    BirthdayTask birthday = (BirthdayTask)logModel.get(i);
                    sb.append(birthday.getDate().getDayOfMonth()).append("-").
                            append(birthday.getDate().getMonthOfYear()).
                            append("-").append(birthday.getDate().getYear());
                    task.addContent(new Element("date").setText(sb.toString()));
                    sb.delete(0, sb.length());
                    sb.append(birthday.getDate().getHourOfDay()).append(":").
                            append(birthday.getDate().getMinuteOfHour());
                    task.addContent(new Element("time").setText(sb.toString()));
                    sb.delete(0, sb.length());
                    
                    if(birthday.getContact().getName().equals("") && 
                            birthday.getContact().getPhoneNumber() == 0 &&
                            birthday.getContact().getMail().equals(""))
                        sb.append("");
                    
                    else if (birthday.getContact().getPhoneNumber() == 0 &&
                            birthday.getContact().getMail().equals(""))
                        sb.append(birthday.getContact().getName());
                    
                    else if (birthday.getContact().getPhoneNumber() != 0 &&
                            birthday.getContact().getMail().equals(""))
                        sb.append(birthday.getContact().getName()).append(",").
                            append(birthday.getContact().getPhoneNumber());
                    else
                        sb.append(birthday.getContact().getName()).append(",").
                            append(birthday.getContact().getPhoneNumber()).
                            append(",").append(birthday.getContact().getMail());
                    
                    task.addContent(new Element("contact").
                            setText(sb.toString()));
                    sb.delete(0, sb.length());   
                    
                    sb.append(birthday.getPriority());
                    task.addContent(new Element("priority").
                            setText(sb.toString()));
                    sb.delete(0, sb.length());
                    birthdayTask.getRootElement().addContent(task);
                }
                else if(logModel.get(i) instanceof BusinessTask){
                    BusinessTask business = (BusinessTask)logModel.get(i);
                    task.addContent(new Element("taskName").
                            setText(business.getTaskName()));
                    task.addContent(new Element("description").
                            setText(business.getDescription()));
                    sb.append(business.getDate().getDayOfMonth()).append("-").
                            append(business.getDate().getMonthOfYear()).
                            append("-").append(business.getDate().getYear());
                    task.addContent(new Element("date").setText(sb.toString()));
                    sb.delete(0, sb.length());
                    sb.append(business.getDate().getHourOfDay()).append(":").
                            append(business.getDate().getMinuteOfHour());
                    task.addContent(new Element("time").setText(sb.toString()));
                    sb.delete(0, sb.length());
                    
                    if(business.getContact().getName().equals("") && 
                            business.getContact().getPhoneNumber() == 0 &&
                            business.getContact().getMail().equals(""))
                        sb.append("");
                    
                    else if (business.getContact().getPhoneNumber() == 0 &&
                            business.getContact().getMail().equals(""))
                        sb.append(business.getContact().getName());
                    
                    else if (business.getContact().getPhoneNumber() != 0 &&
                            business.getContact().getMail().equals(""))
                        sb.append(business.getContact().getName()).append(",").
                            append(business.getContact().getPhoneNumber());
                    else
                        sb.append(business.getContact().getName()).append(",").
                            append(business.getContact().getPhoneNumber()).
                            append(",").append(business.getContact().getMail());
                    
                    task.addContent(new Element("contact").
                            setText(sb.toString()));
                    sb.delete(0, sb.length());                    
                    sb.append(business.getPriority());
                    task.addContent(new Element("priority").
                            setText(sb.toString()));
                    sb.delete(0, sb.length());
                    businessTask.getRootElement().addContent(task);
                }
            }
            XMLOutputter xmlOutput = new XMLOutputter();  
            xmlOutput.output(birthdayTask, System.out);  
            xmlOutput.setFormat(Format.getPrettyFormat());  
            xmlOutput.output(birthdayTask, new FileWriter(
                    "target\\distributive\\birthdays\\birthdayTasks.xml"));  
            xmlOutput.output(businessTask, System.out);  
            xmlOutput.setFormat(Format.getPrettyFormat());  
            xmlOutput.output(businessTask, new FileWriter(
                    "target\\distributive\\business\\businessTasks.xml"));  
           } catch (IOException io) {  
            System.out.println(io.getMessage());  
           }  
    }
        
    

    public LogImpl uploadData() {
        SAXBuilder saxBuilder = new SAXBuilder();  
        LogImpl log = new LogImpl();
        File fileBirthdays = new File("target\\distributive\\birthdays\\"
                + "birthdayTasks.xml"); 
        File fileBusiness = new File("target\\distributive\\business\\"
                + "businessTasks.xml"); 
        try {  
           Document birthdayTask = saxBuilder.build(fileBirthdays);
           Document businessTask = saxBuilder.build(fileBusiness);
           Element rootNode = birthdayTask.getRootElement();
           List<Task> task = new LinkedList();
           List birthdays = rootNode.getChildren("birthdaytask");  
           String[] date;
           String[] time;
           String[] contacts;
           StringBuilder sb = new StringBuilder();
           Contact contact = new Contact();
           DateTime dt;
           for(int i = 0;i <= birthdays.size()-1; i++){  
               Element element = (Element)birthdays.get(i);  
               date = element.getChildText("date").split("-");
               time = element.getChildText("time").split(":");
               contacts = element.getChildText("contact").split(",");
               sb.append("Priority").append(element.getChildText("priority"));
               dt = new DateTime(Integer.parseInt(date[2]), 
                       Integer.parseInt(date[1]),
                       Integer.parseInt(date[0]),
                       Integer.parseInt(time[0]),
                       Integer.parseInt(time[1]));
               if(contacts.length == 0 && element.getChildText("contact").length() > 0)
                   contact = new Contact(contacts[0]);
               else if(contacts.length == 2)
                   contact = new Contact(contacts[0], Long.parseLong(contacts[1]));
               else if(contacts.length == 3)
                   contact = new Contact(contacts[0], Long.parseLong(contacts[1]), contacts[2]);
               Priority priority = Priority.URGENT_IMPORTANT;
               if(sb.toString().equals("Priority.URGENT_IMPORTANT"))
                   priority = Priority.URGENT_IMPORTANT;
               else if(sb.toString().equals("Priority.URGENT"))
                   priority = Priority.URGENT;
               else if(sb.toString().equals("Priority.IMPORTANT"))
                   priority = Priority.IMPORTANT;
               BirthdayTask birthday = new BirthdayTask(dt, contact, priority);
               sb.delete(0, sb.length());
               task.add(birthday);
           } 
           
           rootNode = businessTask.getRootElement();
           List business = rootNode.getChildren("businesstask");  
           String taskName;
           String description = "";
           contact = new Contact();
           for(int i = 0;i <= business.size()-1; i++){  
               Element element = (Element)business.get(i); 
               taskName = element.getChildText("taskName");
               if(element.getChildText("description") != null)
                   description = element.getChildText("description");
               date = element.getChildText("date").split("-");
               time = element.getChildText("time").split(":");
               if(element.getChildText("contact") != null)
                    contacts = element.getChildText("contact").split(",");
               else 
                   contacts = null;
               sb.append("Priority").append(element.getChildText("priority"));
               dt = new DateTime(Integer.parseInt(date[2]), 
                       Integer.parseInt(date[1]),
                       Integer.parseInt(date[0]),
                       Integer.parseInt(time[0]),
                       Integer.parseInt(time[1]));
               if(contacts == null);
               else if (contacts.length == 1)
                   contact = new Contact(contacts[0]);
               else if(contacts.length == 2)
                   contact = new Contact(contacts[0], Long.parseLong(contacts[1]));
               else if(contacts.length == 3)
                   contact = new Contact(contacts[0], Long.parseLong(contacts[0]), contacts[2]);
               Priority priority = Priority.URGENT_IMPORTANT;
               if(sb.toString().equals("Priority.URGENT_IMPORTANT"))
                   priority = Priority.URGENT_IMPORTANT;
               else if(sb.toString().equals("Priority.URGENT"))
                   priority = Priority.URGENT;
               else if(sb.toString().equals("Priority.IMPORTANT"))
                   priority = Priority.IMPORTANT;
               BusinessTask businessTasks;
               if(description.equals("") && contacts == null)
                    businessTasks = new BusinessTask(taskName, dt, priority);
               else if(contacts == null)
                   businessTasks = new BusinessTask(taskName, description, dt, 
                            priority);
               else
                    businessTasks = new BusinessTask(taskName, description, dt, 
                            contact, priority);
               sb.delete(0, sb.length());
               task.add(businessTasks);
           }
           if(!task.isEmpty())
                log = new LogImpl(task);
           } catch (JDOMException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
          } catch (IOException e) {  
           // TODO Auto-generated catch block  
           e.printStackTrace();  
          }    
        return log;
    }   
}
