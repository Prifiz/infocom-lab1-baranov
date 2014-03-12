/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.serviceinfo.ClearConsole;
import java.io.IOException;

/**
 *
 * @author Сергей
 */
public class UserOSController {

    public void start() throws IOException {
        ClearConsole.clearConsole();
    }
}
