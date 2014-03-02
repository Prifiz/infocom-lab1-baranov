/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Сергей
 */
public class Server extends Thread {

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.active = true;
    }

    public void run() {
        try {
            while (active) {
                Socket incoming = serverSocket.accept();
                Runnable run = new ProcessingClientThread(incoming);
                Thread t = new Thread(run);
                t.start();
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void setActive(boolean state) {
        active = state;
    }
    private ServerSocket serverSocket;
    private boolean active;
}
