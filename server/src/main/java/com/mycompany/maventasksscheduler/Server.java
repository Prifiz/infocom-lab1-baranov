/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.datastorage.XMLStorage;
import com.mycompany.maventasksscheduler.logmodel.LogImpl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Сергей
 */
public class Server implements Runnable {

    private XMLStorage xml;
    private LogImpl logModel;

    public Server(XMLStorage xml, LogImpl logModel) {
        this.xml = xml;
        this.logModel = logModel;
    }

    public void run() {
        try {
            ServerSocket s = new ServerSocket(8189);
            while (true) {
                Socket incoming = s.accept();
                Runnable run = new ProcessingClientThread(incoming, xml, logModel);
                Thread t = new Thread(run);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
