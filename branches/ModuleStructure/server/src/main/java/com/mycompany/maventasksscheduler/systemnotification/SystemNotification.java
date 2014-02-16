/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.systemnotification;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;

/**
 *
 * @author Сергей
 */
public interface SystemNotification {

    /**
     * This method notifies on approach of the planned task.
     */
    LogImpl notification();
}
