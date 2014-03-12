/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.server;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Сергей
 */
public class ProcessingClientThread extends Thread {

    public ProcessingClientThread(Socket s) {
        incoming = s;
        serverLogModel = new LogImpl();
        xml = new XMLStorage();
    }

    public boolean userExists(String login) {
        File file = new File("users\\" + login);
        return file.exists();
    }

    private Date lastModified(String login) {
        File file1 = new File("users\\" + login
                + "\\birthdays\\birthdayTasks.xml");
        File file2 = new File("users\\" + login
                + "\\business\\businessTasks.xml");
        Date date1 = new Date(file1.lastModified());
        Date date2 = new Date(file2.lastModified());
        if (date1.compareTo(date2) >= 0) {
            return date1;
        } else {
            return date2;
        }
    }

    private void synchronizeLogs() {
        System.out.println("User - " + login + " want synchronize logs");
        if (userLastModified.compareTo(serverLastModified) > 0) {
            System.out.println("user have last modified \n");
            serverLogModel = userLogModel;
            xml.saveData(serverLogModel, login);
        } else if (userLastModified.compareTo(serverLastModified) <= 0) {
            System.out.println("server has last modified \n");
            userLogModel = serverLogModel;
        }
    }

    @Override
    public void run() {
        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(
                        incoming.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(
                        incoming.getOutputStream());
                Object o = null;
                boolean userExist;
                login = "";
                while (!incoming.isClosed()) {
                    try {
                        o = ois.readObject();
                    } catch (ClassNotFoundException ex) {
                    }
                    if (o instanceof String) {
                        userExist = userExists((String) o);
                        oos.writeObject(userExist);
                        if (userExist) {
                            login = (String) o;
                        }
                        oos.flush();
                    } else if (o instanceof Date) {
                        userLastModified = (Date) o;
                        serverLastModified = lastModified(login);
                    } else if (o instanceof LogImpl) {
                        userLogModel = (LogImpl) o;
                        serverLogModel = new XMLStorage().uploadData(login);
                        //если журналы не равны, вызываем метод синхронизации
                        if (!serverLogModel.equals(userLogModel)) {
                            synchronizeLogs();
                        }
                        oos.writeObject(userLogModel);
                        oos.flush();
                    }
                }
            } finally {
                login = "";
                incoming.close();
            }
        } catch (IOException e) {
        }
    }
    private Socket incoming;
    private LogImpl userLogModel;
    private LogImpl serverLogModel;
    private XMLStorage xml;
    private String login;
    private Date serverLastModified;
    private Date userLastModified;
}
