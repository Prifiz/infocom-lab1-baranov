/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventasksscheduler.serviceinfo;

import com.mycompany.maventasksscheduler.serviceinfo.View.CLibrary;
import com.sun.jna.Library;
import java.io.IOException;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 *
 * @author Сергей
 */
public class ClearConsole {

    public static void clearConsole() throws IOException {
        if (UserOS.isWindows()) {
            CLibrary.INSTANCE.system("cls");
        } else if (UserOS.isMac()) {
            System.out.println("I don't use mac, so sorry");
        } else if (UserOS.isUnix()) {
            Runtime.getRuntime().exec("clear");
        } else {
            System.out.println("This is unknown OS");
        }
    }
}

abstract class View {

    public interface CLibrary extends Library {

        CLibrary INSTANCE = (CLibrary) Native.loadLibrary(
                (Platform.isWindows() ? "msvcrt" : "c"), CLibrary.class);

        void system(String command);
    }
}