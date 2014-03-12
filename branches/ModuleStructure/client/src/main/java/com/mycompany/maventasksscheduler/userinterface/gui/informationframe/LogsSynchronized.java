/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui.informationframe;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Сергей
 */
public class LogsSynchronized extends JFrame {

    private JPanel panel;

    public LogsSynchronized() {
        super("Logs are synchronized");
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Logs were are synchronized"));
        getContentPane().add(panel);
    }
}
