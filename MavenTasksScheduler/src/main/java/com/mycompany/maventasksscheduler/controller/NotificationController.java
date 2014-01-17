/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controller;

import com.mycompany.maventasksscheduler.systemnotification.MessageNotification;

/**
 *
 * @author Сергей
 */
public class NotificationController {

    private MessageNotification message;

    public NotificationController() {
        message = new MessageNotification();
    }

    public void start() {
        message.run();
    }
}
