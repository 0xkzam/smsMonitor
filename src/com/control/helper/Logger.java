/*
 * Copyright (C) 2014 Kasun Amarasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.control.helper;

import com.control.controller.ProcessRunner;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Kasun Amarasena
 */
public class Logger {

    private static LogManager logManager = LogManager.getLogManager();
    private static FileHandler file = getFile();

    /**
     * INFO level Logger using java.util.Logger
     * @param className String class name
     * @param method String method name
     * @param msg String message
     */
    public static void printInfo(String className, String method, String msg) {
        if (file != null) {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
            logManager.addLogger(logger);
            logger.addHandler(file);
            logger.logp(Level.INFO, className, method, msg);
        } else {
            System.err.println("NULL log file handler");
        }
    }    

    /**
     * SEVERE level logger using java.util.Logger
     * @param className String class name
     * @param method String method name
     * @param err String err
     */
    public static void printError(String className, String method, String err) {
        if (file != null) {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
            logManager.addLogger(logger);
            logger.addHandler(file);
            logger.logp(Level.SEVERE, className, method, err);
        } else {
            System.err.println("NULL log file handler");
        }
    }
    
    /**
     * Creates a text file in logger/log_xx_xxxx.txt where xx and xxxx means the month and year
     * @return FileHandler object 
     */
    private static FileHandler getFile() {
        try {
            String absPath = Paths.get("").toAbsolutePath().toString();
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            FileHandler fileHandler = new FileHandler(absPath + "\\logger\\log_" + year + "_" + month + ".txt");
            fileHandler.setFormatter(new SimpleFormatter());
            return fileHandler;
        } catch (IOException | SecurityException ex) {
            System.err.println("Log FileHandler error.");
        }
        return null;
    }
}
