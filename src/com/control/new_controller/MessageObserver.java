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
package com.control.new_controller;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Kasun Amarasena
 */
public class MessageObserver implements Observer {

    private static String tempMessage="";

    @Override
    public void update(Observable o, Object arg) {
        String message = (String) arg;
        if (!tempMessage.equals(message)) {            
            System.out.println("messge: " + message);
            tempMessage = message;
        }
    }

}
