/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.userinterface.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Сергей
 */
public class EditingTaskDialog extends JDialog {

    private JPanel panel;

    public EditingTaskDialog() {
        super(new JFrame(), "Editing task", true);
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        DigitalClockLabel clock = new DigitalClockLabel();
        clock.setFont(new Font("Algerian", 1, 20));
        setBounds(200, 100, 200, 200);

        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(clock);
        panel.add(new JSlider());
        panel.add(new JLabel("some components"));
        getContentPane().add(panel);
    }
}
