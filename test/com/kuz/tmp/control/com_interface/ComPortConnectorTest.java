/*
 * Copyright (C) 2015 Kasun Amarasena
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

package com.kuz.tmp.control.com_interface;


import com.kuz.tmp.control.com_interface.ComPort;
import com.kuz.tmp.control.com_interface.ComPortConnector;
import java.io.IOException;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.comm.CommPort;
import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.rmi.CORBA.Util;
import org.junit.After;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortConnectorTest {
    
    private ComPortConnector connector = ComPortConnector.getInstance();
    
    public ComPortConnectorTest() {  }    
       
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test1() {       
        
        List<String> list = ComPortConnector.getInstance().getAvailableComports();
        System.out.println("available ports:"+list);
        try {
            CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier("COM1");
            System.out.println("owner:"+portID.getCurrentOwner());
            System.out.println("type:"+portID.getPortType());
            System.out.println("isCurrentlyOwned:"+portID.isCurrentlyOwned());     
            
        } catch (NoSuchPortException ex) {
            fail();
        }
    }
    
    @Test
    public void test2() {
        try {
            ComPort port = connector.connectTo(CommPortIdentifier.getPortIdentifier("COM1"));
        } catch (NoSuchPortException | IOException | PortInUseException | TooManyListenersException ex) {
            fail(ex.getMessage());
        }
    }
    
}
