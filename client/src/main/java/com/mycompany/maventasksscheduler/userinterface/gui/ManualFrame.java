/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Сергей
 */
public class ManualFrame extends JFrame {

    private JPanel panel;

    public ManualFrame() {
        super("Manual");
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Manual"));
        getContentPane().add(panel);
    }
}
