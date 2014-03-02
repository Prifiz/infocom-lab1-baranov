/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Сергей
 */
public class ManipulationsOverUsers {

    private String path = "";

    public boolean userExists(String login) {
        File file = new File(login);
        return file.exists();
    }

    public void showUsers() {
        File folder = new File("users");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(i + " " + listOfFiles[i].getName());
        }
    }

    public void removeUser(String login) {
        File directory = new File("users\\");
        //make sure directory exists
        if (!directory.exists()) {
            System.out.println("Directory does not exist.");
        } else {

                delete("user\\",true);
        }

        System.out.println("Done");
    }

    public static boolean delete(String filePath, boolean recursive) {
        File file = new File(filePath);
        if (!file.exists()) {
            return true;
        }

        if (!recursive || !file.isDirectory()) {
            return file.delete();
        }

        String[] list = file.list();
        for (int i = 0; i < list.length; i++) {
            if (!delete(filePath + File.separator + list[i], true)) {
                return false;
            }
        }

        return file.delete();
    }

public static void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {
                //list all the directory contents
                String files[] = file.list();
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i]);
                    
                }
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

    public void createUser(String login) {
        createDir("users\\" + login + "\\birthdays\\birthdayTasks.xml");
        createDir("users\\" + login + "\\business\\businessTasks.xml");
    }

    public void createDir(String path) {
        File file = new File(path);
        this.path = file.getAbsolutePath();
        if (!file.exists()) {
            File dir = file.getParentFile();
            dir.mkdirs();
            outPutXML(createDocument(), path);
        }
    }

    public Document createDocument() {
        Element tasks = new Element("tasks");
        return new Document(tasks);
    }

    public void outPutXML(Document tasks, String path) {
        try {
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(tasks, new FileWriter(path, false));
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}
