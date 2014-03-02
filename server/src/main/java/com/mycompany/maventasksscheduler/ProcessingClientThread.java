/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Сергей
 */
public class ProcessingClientThread implements Runnable {

    public ProcessingClientThread(Socket s) {
        incoming = s;
        serverLogModel = new LogImpl();
        manipulationOverUser = new ManipulationsOverUsers();
    }

    public boolean userExists(String login) {
        File file = new File("users\\" + login);
        return file.exists();
    }

    private void synchronizeLogs() {
        System.out.println("SynchronizeLogs");
    }

    public void run() {
        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(incoming.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream());
                Object o = null;
                boolean userExist;
                String login = "";
                while (!incoming.isClosed()) {
                    try {
                        o = ois.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ProcessingClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (o instanceof String) {
                        //System.out.println("connect");
                        userExist = userExists((String) o);
                        oos.writeObject(userExist);
                        if (userExist) {
                            login = (String) o;
                        }
                        oos.flush();
                    } else if (o instanceof LogImpl) {
                        userLogModel = (LogImpl) o;
                        serverLogModel = new XMLStorage().uploadData(login);
                        //serverLogModel = xml.uploadData(login);
                        //если журналы не равны, вызываем метод синхронизации
                        if (!serverLogModel.equals(userLogModel)) {
                            synchronizeLogs();
                        }
                        oos.writeObject(userLogModel);
                        oos.flush();
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
        }
    }
    private Socket incoming;
    private LogImpl userLogModel;
    private LogImpl serverLogModel;
    private XMLStorage xml;
    private ManipulationsOverUsers manipulationOverUser;
}
