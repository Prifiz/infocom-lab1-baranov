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

    private Document createDocument() {
        Element tasks = new Element("tasks");
        return new Document(tasks);
    }

    private void outPutXML(Document tasks, String path) {
        try {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(tasks, new FileWriter(path));
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    private void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            File dir = file.getParentFile();
            dir.mkdirs();
            outPutXML(createDocument(), path);
        }
    }

    public LogImpl uploadData(String login) {
        LogImpl log = new LogImpl();
        List<Task> task = new LinkedList();
        if (login == null || login.equals("")) {
        }
        else if (login.equals("my tasks")) {
            createDir(login + "\\birthdays\\birthdayTasks.xml");
            createDir(login + "\\business\\businessTasks.xml");
            task.addAll(openAndReadXML(login
                    + "\\birthdays\\birthdayTasks.xml", "birthdaytask"));
            task.addAll(openAndReadXML(login
                    + "\\business\\businessTasks.xml", "businesstask"));
        }  else {
            createDir("users\\" + login + "\\birthdays\\birthdayTasks.xml");
            createDir("users\\" + login + "\\business\\businessTasks.xml");
            task.addAll(openAndReadXML("users\\" + login
                    + "\\birthdays\\birthdayTasks.xml", "birthdaytask"));
            task.addAll(openAndReadXML("users\\" + login
                    + "\\business\\businessTasks.xml", "businesstask"));
        }

        if (!task.isEmpty()) {
            log = new LogImpl(task);
        }
        return log;
    }

    public void saveData(LogImpl logModel, String user) {
        Document businessTask = createDocument();
        Document birthdayTask = createDocument();
        Integer birthdayTaskCount = 0;
        Integer businessTaskCount = 0;
        for (int i = 0; i < logModel.getSize(); i++) {
            if (logModel.get(i) instanceof BirthdayTask) {
                birthdayTask.getRootElement().addContent(
                        createBirthdayTask(logModel.get(i), birthdayTaskCount));
                birthdayTaskCount++;
            }
            if (logModel.get(i) instanceof BusinessTask) {
                businessTask.getRootElement().addContent(
                        createBusinessTask(logModel.get(i), businessTaskCount));
                businessTaskCount++;
            }
        }
        if (user.equals("my tasks\\")) {
            outPutXML(birthdayTask, user + "birthdays\\birthdayTasks.xml");
            outPutXML(businessTask, user + "business\\businessTasks.xml");
        } else {
            outPutXML(birthdayTask, "users\\" + user + "\\birthdays\\birthdayTasks.xml");
            outPutXML(businessTask, "users\\" + user + "\\business\\businessTasks.xml");
        }
//        outPutXML(birthdayTask, "my tasks\\birthdays\\birthdayTasks.xml");
//        outPutXML(businessTask, "my tasks\\business\\businessTasks.xml");
    }

    private Element createBirthdayTask(Task task, Integer i) {
        StringBuilder sb = new StringBuilder();
        Element element = new Element(task.getClass().getSimpleName().
                toLowerCase());
        element.setAttribute(new Attribute("id", i.toString()));
        BirthdayTask birthday = (BirthdayTask) task;
        sb.append(birthday.getDate().getDayOfMonth()).append("-").
                append(birthday.getDate().getMonthOfYear()).
                append("-").append(birthday.getDate().getYear());
        element.addContent(new Element("date").setText(sb.toString()));
        sb.delete(0, sb.length());
        sb.append(birthday.getDate().getHourOfDay()).append(":").
                append(birthday.getDate().getMinuteOfHour());
        element.addContent(new Element("time").setText(sb.toString()));
        sb.delete(0, sb.length());
        Element contact = new Element("contact");
        contact.addContent(new Element("name").setText(
                birthday.getContact().getName()));
        contact.addContent(new Element("phoneNumber").setText(
                birthday.getContact().getPhoneNumber()));
        contact.addContent(new Element("email").setText(
                birthday.getContact().getMail()));
        element.addContent(contact);
        sb.append(birthday.getPriority());
        element.addContent(new Element("priority").
                setText(sb.toString()));
        sb.delete(0, sb.length());
        return element;
    }

    private Element createBusinessTask(Task task, Integer i) {
        StringBuilder sb = new StringBuilder();
        Element element = new Element(task.getClass().getSimpleName().
                toLowerCase());
        element.setAttribute(new Attribute("id", i.toString()));
        BusinessTask business = (BusinessTask) task;
        element.addContent(new Element("taskName").
                setText(business.getTaskName()));
        element.addContent(new Element("description").
                setText(business.getDescription()));
        sb.append(business.getDate().getDayOfMonth()).append("-").
                append(business.getDate().getMonthOfYear()).
                append("-").append(business.getDate().getYear());
        element.addContent(new Element("date").setText(sb.toString()));
        sb.delete(0, sb.length());
        sb.append(business.getDate().getHourOfDay()).append(":").
                append(business.getDate().getMinuteOfHour());
        element.addContent(new Element("time").setText(sb.toString()));
        sb.delete(0, sb.length());
        Element contact = new Element("contact");
        contact.addContent(new Element("name").setText(
                business.getContact().getName()));
        contact.addContent(new Element("phoneNumber").setText(
                business.getContact().getPhoneNumber()));
        contact.addContent(new Element("email").setText(
                business.getContact().getMail()));
        element.addContent(contact);
        sb.append(business.getPriority());
        element.addContent(new Element("priority").setText(sb.toString()));
        sb.delete(0, sb.length());
        return element;
    }

    private List<Task> openAndReadXML(String path, String children) {
        SAXBuilder saxBuilder = new SAXBuilder();
        File fileBirthdays = new File(path);
        List<Task> task = new LinkedList();
        try {
            Document birthdayTask = saxBuilder.build(fileBirthdays);
            Element rootNode = birthdayTask.getRootElement();
            List birthdays = rootNode.getChildren(children);
            for (int i = 0; i <= birthdays.size() - 1; i++) {
                Element element = (Element) birthdays.get(i);
                if (element.getName().equals("birthdaytask")) {
                    task.add(parseBirthdayTasks(element));
                } else if (element.getName().equals("businesstask")) {
                    task.add(parseBusinessTask(element));
                }
            }
        } catch (JDOMException e) {
            // e.printStackTrace();  
        } catch (IOException e) {
            //e.printStackTrace();  
        }
        return task;
    }

    private DateTime parseDateAndTime(Element element) {
        String[] date;
        String[] time;
        date = element.getChildText("date").split("-");
        time = element.getChildText("time").split(":");
        return new DateTime(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[0]),
                Integer.parseInt(time[0]),
                Integer.parseInt(time[1]));
    }

    private Contact parseContact(Element element) {
        Element elementContact = element.getChild("contact");
        Contact contact = new Contact(elementContact.getChildText("name"),
                elementContact.getChildText("phoneNumber"),
                elementContact.getChildText("email"));
        return contact;
    }

    private Priority parsePriority(Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append("Priority").append(element.getChildText("priority"));
        Priority priority = Priority.URGENT_IMPORTANT;
        if (sb.toString().equals("Priority.URGENT_IMPORTANT")) {
            priority = Priority.URGENT_IMPORTANT;
        } else if (sb.toString().equals("Priority.URGENT")) {
            priority = Priority.URGENT;
        } else if (sb.toString().equals("Priority.IMPORTANT")) {
            priority = Priority.IMPORTANT;
        }
        return priority;
    }

    private BirthdayTask parseBirthdayTasks(Element element) {
        return new BirthdayTask(parseDateAndTime(
                element), parseContact(element), parsePriority(element));
    }

    private String parseTaskName(Element element) {
        return element.getChildText("taskName");
    }

    private String parseDescription(Element element) {
        String description = "";
        if (element.getChildText("description") != null) {
            description = element.getChildText("description");
        }
        return description;
    }

    private BusinessTask parseBusinessTask(Element element) {
        String taskName = parseTaskName(element);
        String description = parseDescription(element);
        DateTime dateTime = parseDateAndTime(element);
        Contact contact = parseContact(element);
        Priority priority = parsePriority(element);
        if (description.equals("")) {
            return new BusinessTask(taskName, dateTime, priority);
        } else {
            return new BusinessTask(
                    taskName, description, dateTime, contact, priority);
        }
    }
}
