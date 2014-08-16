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

import java.io.IOException;
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;

/**
 *
 * @author Kasun Amarasena
 */
public class Test {

    public static void main(String[] args) {
        ComPortReader cr = null;
        try {
            cr = new ComPortReader("COM12");
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            System.out.println(ex);
        }
        
        MessageObserver observer = new MessageObserver();
        cr.addObserver(observer);
        Thread t = new Thread(cr);
        t.start();        
        
        ComPortReader cr2 = null;
        try {
            cr2 = new ComPortReader("COM13");
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            System.out.println(ex);
        }
        
        MessageObserver observer2 = new MessageObserver();
        cr2.addObserver(observer2);
        Thread t2 = new Thread(cr2);
        t2.start();   
    }
}
