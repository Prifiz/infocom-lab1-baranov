/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class Client {
    
    public static void main(String[] argv){
        try{
            Socket s = new Socket(InetAddress.getLocalHost(),8189);
            try{
                InputStream inStream = s.getInputStream();//получает, что отправляет сервер
                OutputStream outStream = s.getOutputStream();//отправляет то, что получает сервер
                PrintWriter out = new PrintWriter(outStream, true);
                
                Scanner sc = new Scanner(inStream);
                Scanner scanner = new Scanner(System.in);
                while(sc.hasNextLine()){
                    String line = sc.nextLine();
                    System.out.println(line);
                    out.println(scanner.nextLine());
                }
            }
            finally{
                s.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
