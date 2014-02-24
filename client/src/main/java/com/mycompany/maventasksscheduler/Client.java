/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Сергей
 */
public class Client {

//    public static void main(String[] argv) {
//        try {
//            Socket s = new Socket(InetAddress.getLocalHost(), 8189);
//            try {
//                InputStream inStream = s.getInputStream();//получает, что отправляет сервер
//                OutputStream outStream = s.getOutputStream();//отправляет то, что получает сервер
//
//                DataInputStream in = new DataInputStream(inStream);
//                DataOutputStream out = new DataOutputStream(outStream);
//
//                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//                String line = null;
//                System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
//                System.out.println();
//
//                while (!s.isInputShutdown()) {
//                    line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
//                    System.out.println("Sending this line to the server...");
//                    out.writeUTF(line); // отсылаем введенную строку текста серверу.
//                    out.flush(); // заставляем поток закончить передачу данных.
//                    line = in.readUTF(); // ждем пока сервер отошлет строку текста.
//                    System.out.println("The server was very polite. It sent me this : " + line);
//                    System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
//                    System.out.println();
//                }
//            } finally {
//                s.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
