/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

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

    public ProcessingClientThread(Socket s) {
        incoming = s;
    }

    public void run() {
        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(incoming.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(incoming.getOutputStream());
                Object o = null;

                try {
                    o = ois.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProcessingClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (o instanceof LogImpl) {
                    logModel = (LogImpl) o;
                    logModel.remove(0);
                    oos.writeObject(logModel);
                    oos.flush();
                }

            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Socket incoming;
    private LogImpl logModel;
}
