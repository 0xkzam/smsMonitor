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
package com.kuz.tmp.old.controller;


import com.kuz.tmp.old.controller.ComPortReader;
import com.kuz.tmp.control.com_interface.ComPortConnector;
import java.io.IOException;
import java.util.List;
import java.util.TooManyListenersException;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.rmi.CORBA.Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortReaderTest {

    public ComPortReaderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class ComPortReader.
     */
    @Test
    public void testGetInstance() {
        System.out.println("Running getInstance()");

        ComPortReader result;

        String comPort = "";
        try {
            result = ComPortReader.getInstance(comPort);
            assertFalse("No such Port", true);
        } catch (NoSuchPortException e) {
            System.out.println("No such Port-Connected \"" + comPort + "\"");
        } catch (PortInUseException | IOException | TooManyListenersException ex) {
            assertFalse(ex.toString(), true);
        }

        List<String> ls = ComPortConnector.getInstance().getAvailableComports();
        comPort = ls.iterator().next();
        System.out.println("Connecting to " + comPort);

        try {
            result = ComPortReader.getInstance(comPort);
            System.out.println("Connected to " + comPort);
        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            assertFalse(ex.toString(), true);
        }

        System.out.println("Reconnecting to " + comPort);
        try {
            result = ComPortReader.getInstance(comPort);
            assertFalse("Port in use-Reconnected", true);
        } catch (PortInUseException e) {
            System.out.println("Port in use:" + comPort);
        } catch (NoSuchPortException | IOException | TooManyListenersException ex) {
            assertFalse(ex.toString(), true);
        }

    }

    public static void test1() {
        try {
            final ComPortReader com = ComPortReader.getInstance("COM10");
            Thread t = new Thread(com);

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("" + ex);
                    }
                    System.out.println(com.getMsgBuffer());
                }
            });
            t.start();
            t2.start();

        } catch (NoSuchPortException | PortInUseException | IOException | TooManyListenersException ex) {
            System.out.println(ex);
        }

    }
}
