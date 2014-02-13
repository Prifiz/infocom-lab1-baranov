/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import com.mycompany.maventasksscheduler.logmodel.Task;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Сергей
 */
public class BrowsingTaskDialog extends JDialog {

    private JPanel panel;

    public BrowsingTaskDialog(Task task) {
        super(new JFrame(), "Browsing task", true);
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DigitalClockLabel clock = new DigitalClockLabel();
        clock.setFont(new Font("Algerian", 1, 20));
        setBounds(200, 100, 200, 200);

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel(task.toString()));
        getContentPane().add(panel);
    }
}
