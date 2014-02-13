package com.mycompany.maventasksscheduler;

import com.mycompany.maventasksscheduler.controller.MainController;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("\nWelcome to your scheduler of tasks");
        MainController c = new MainController();
        c.start();
    }
}
