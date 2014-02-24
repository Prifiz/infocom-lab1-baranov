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
public class Server implements Runnable {

    public void run() {
        try {
            int i = 1;
            ServerSocket s = new ServerSocket(8189);
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable run = new ProcessingClientThread(incoming);
                Thread t = new Thread(run);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
