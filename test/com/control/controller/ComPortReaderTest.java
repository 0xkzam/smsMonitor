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

package com.control.controller;

import javax.comm.SerialPortEvent;
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
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        String comPort = "";
        ComPortReader expResult = null;
        ComPortReader result = ComPortReader.getInstance(comPort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMsgBuffer method, of class ComPortReader.
     */
    @Test
    public void testGetMsgBuffer() {
        System.out.println("getMsgBuffer");
        ComPortReader instance = null;
        String expResult = "";
        String result = instance.getMsgBuffer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of send method, of class ComPortReader.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
        String command = "";
        ComPortReader instance = null;
        instance.send(command);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class ComPortReader.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        ComPortReader instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of serialEvent method, of class ComPortReader.
     */
    @Test
    public void testSerialEvent() {
        System.out.println("serialEvent");
        SerialPortEvent event = null;
        ComPortReader instance = null;
        instance.serialEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class ComPortReader.
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        String portName = "";
        ComPortReader instance = null;
        instance.register(portName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHasNext method, of class ComPortReader.
     */
    @Test
    public void testIsHasNext() {
        System.out.println("isHasNext");
        ComPortReader instance = null;
        boolean expResult = false;
        boolean result = instance.isHasNext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComport method, of class ComPortReader.
     */
    @Test
    public void testGetComport() {
        System.out.println("getComport");
        String expResult = "";
        String result = ComPortReader.getComport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
