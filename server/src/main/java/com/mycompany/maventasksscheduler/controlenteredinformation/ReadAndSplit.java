/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.controlenteredinformation;

import java.util.Scanner;

/**
 *
 * @author Сергей
 */
public class ReadAndSplit {

    public static int[] date() {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        int[] intSplitDate = new int[3];
        if (date.length() < 7) {
            return intSplitDate;
        }
        String[] splitDate = new String[3];
        if (date.contains(".") && !date.contains("-") && !date.contains("/")) {
            splitDate = date.split("\\.");
        } else if (date.contains("-") && !date.contains(".")
                && !date.contains("/")) {
            splitDate = date.split("-");
        } else if (date.contains("/") && !date.contains(".")
                && !date.contains("-")) {
            splitDate = date.split("/");
        } else {
            return intSplitDate;
        }
        if (Integer.parseInt(splitDate[1]) < 2013
                && Integer.parseInt(splitDate[1]) <= 0
                && Integer.parseInt(splitDate[1]) >= 13
                && Integer.parseInt(splitDate[0]) <= 0
                && Integer.parseInt(splitDate[0]) > 31) {
            return intSplitDate;
        } else {
            for (int i = 0; i < splitDate.length; i++) {
                intSplitDate[i] = Integer.parseInt(splitDate[i]);
            }
            return intSplitDate;
        }
    }

    public static int[] time() {
        Scanner sc = new Scanner(System.in);
        int[] intSplitTime = {-1, -1};
        String time = sc.nextLine();
        if (time.length() < 3) {
            return intSplitTime;
        }
        String[] splitTime = {"-1", "-1"};
        if (time.contains(":")) {
            splitTime = time.split(":");
        }
        for (int i = 0; i < splitTime.length; i++) {
            intSplitTime[i] = Integer.parseInt(splitTime[i]);
        }
        return intSplitTime;
    }
}
