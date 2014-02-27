/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
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

    public ProcessingClientThread(Socket s, XMLStorage xml, LogImpl logModel) {
        incoming = s;
        this.xml = xml;
        serverLogModel = xml.uploadData("");
    }

    private boolean checkingLogin(String login) {
        return false;
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
                while (!incoming.isClosed()) {
                    try {
                        o = ois.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ProcessingClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (o instanceof String) {
                        //метод проверки существования такого логина
                        //checkingLogin((String)o);
                        oos.writeObject(checkingLogin((String) o));
                        oos.flush();
                    } else if (o instanceof LogImpl) {
                        userLogModel = (LogImpl) o;
                        //если журналы не равны, вызываем метод синхронизации
                        if (!serverLogModel.equals(userLogModel)) {
                            synchronizeLogs();
                        }
                        if (userLogModel.getSize() > 0) {//просто для наглядности, работы запросов
                            userLogModel.remove(0);
                        }
                        oos.writeObject(userLogModel);
                        oos.flush();
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Socket incoming;
    private LogImpl userLogModel;
    private LogImpl serverLogModel;
    private XMLStorage xml;
}
