/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.datastorage;

import com.mycompany.maventasksscheduler.logmodel.LogImpl;

/**
 *
 * @author Сергей
 */
public interface Storage {

    /**
     * This method keeps LogModel on a hard disk.
     *
     * @param logModel The kept LogModel.
     */
    void saveData(LogImpl logModel);

    /**
     * Returns LogModel from a hard disk.
     *
     * @return A LogModel containing on a hard disk.
     */
    LogImpl uploadData();
}
