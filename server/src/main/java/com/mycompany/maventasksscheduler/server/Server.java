/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.server;

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
        this.setDaemon(true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (active) {
                    Socket incoming = serverSocket.accept();
                    Thread t = new ProcessingClientThread(incoming);
                    t.setDaemon(active);
                    t.start();
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void setActive(boolean state) {
        active = state;
    }
    private ServerSocket serverSocket;
    private volatile boolean active;
}
