/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.datastorage;

import com.mycompany.maventasksscheduler.logmodel.LogModel;

/**
 *
 * @author Сергей
 */
public interface DataStorageable {
    
    /**
     * This method keeps LogModel on a hard disk.
     * @param logModel The kept LogModel.
     */
    void saveData(LogModel logModel);
    
    /**
     * Returns LogModel from a hard disk.
     * @return A LogModel containing on a hard disk.
     */
    LogModel uploadData();
    
}
