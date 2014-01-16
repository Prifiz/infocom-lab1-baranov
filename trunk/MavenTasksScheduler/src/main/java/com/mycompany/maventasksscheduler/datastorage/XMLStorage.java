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

    public Document createDocument() {
        Element tasks = new Element("tasks");
        return new Document(tasks);
    }

    public void outPutXML(Document tasks, String path) {
        try {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(tasks, new FileWriter(path));
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    public void createDir(String path) {
        File file = new File(path);
        File dir = file.getParentFile();
        if (!file.exists()) {
            dir.mkdirs();
            outPutXML(createDocument(), path);
        }
    }

    public void saveData(LogImpl logModel) {
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
        outPutXML(birthdayTask, "birthdays\\birthdayTasks.xml");
        outPutXML(businessTask, "business\\businessTasks.xml");
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

        if (birthday.getContact().getName().equals("")
                && birthday.getContact().getPhoneNumber() == 0
                && birthday.getContact().getMail().equals("")) {
            sb.append("");
        } else if (birthday.getContact().getPhoneNumber() == 0
                && birthday.getContact().getMail().equals("")) {
            sb.append(birthday.getContact().getName()).append(", ");
        } else if (birthday.getContact().getPhoneNumber() != 0
                && birthday.getContact().getMail().equals("")) {
            sb.append(birthday.getContact().getName()).append(",").
                    append(birthday.getContact().getPhoneNumber());
        } else {
            sb.append(birthday.getContact().getName()).append(",").
                    append(birthday.getContact().getPhoneNumber()).
                    append(",").append(birthday.getContact().getMail());
        }

        element.addContent(new Element("contact").
                setText(sb.toString()));
        sb.delete(0, sb.length());

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

        if (business.getContact().getName().equals("")
                && business.getContact().getPhoneNumber() == 0
                && business.getContact().getMail().equals("")) {
            sb.append("");
        } else if (business.getContact().getPhoneNumber() == 0
                && business.getContact().getMail().equals("")) {
            sb.append(business.getContact().getName()).append(", ");
        } else if (business.getContact().getPhoneNumber() != 0
                && business.getContact().getMail().equals("")) {
            sb.append(business.getContact().getName()).append(",").
                    append(business.getContact().getPhoneNumber());
        } else {
            sb.append(business.getContact().getName()).append(",").
                    append(business.getContact().getPhoneNumber()).
                    append(",").append(business.getContact().getMail());
        }

        element.addContent(new Element("contact").setText(sb.toString()));
        sb.delete(0, sb.length());
        sb.append(business.getPriority());
        element.addContent(new Element("priority").setText(sb.toString()));
        sb.delete(0, sb.length());
        return element;
    }

    public List<Task> openAndReadXML(String path, String children) {
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

    public LogImpl uploadData() {
        createDir("birthdays\\birthdayTasks.xml");
        createDir("business\\businessTasks.xml");
        LogImpl log = new LogImpl();
        List<Task> task =
                openAndReadXML("birthdays\\birthdayTasks.xml", "birthdaytask");
        task.addAll(
                openAndReadXML("business\\businessTasks.xml", "businesstask"));
        if (!task.isEmpty()) {
            log = new LogImpl(task);
        }
        return log;
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
        String[] contacts;
        Contact contact = new Contact();
        contacts = element.getChildText("contact").split(",");
        if (contacts.length == 1
                && element.getChildText("contact").length() > 0) {
            contact = new Contact(contacts[0]);
        } else if (contacts.length == 2) {
            contact = new Contact(contacts[0], Long.parseLong(contacts[1]));
        } else if (contacts.length == 3) {
            contact = new Contact(
                    contacts[0], Long.parseLong(contacts[1]), contacts[2]);
        }
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
        Priority priority = parsePriority(element);
        String[] contacts;
        Contact contact = new Contact();
        if (element.getChildText("contact") != null) {
            contacts = element.getChildText("contact").split(",");
            contact = parseContact(element);
        } else {
            contacts = null;
        }
        if (description.equals("") && contacts == null) {
            return new BusinessTask(taskName, dateTime, priority);
        } else if (contacts == null) {
            return new BusinessTask(taskName, description, dateTime, priority);
        } else {
            return new BusinessTask(
                    taskName, description, dateTime, contact, priority);
        }
    }
}
