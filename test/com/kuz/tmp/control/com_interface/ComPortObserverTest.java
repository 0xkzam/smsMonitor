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
package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.control.com_interface.ComPortObserverImpl;
import java.util.Observable;
import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class ComPortObserverTest {

    public ComPortObserverTest() {
    }

    /**
     * Test of update method, of class ComPortObserver.
     */
    @Test
    public void testUpdate_Observable_Object() {
        System.out.println("Testing update");

        String s = " fahda gda gd agd";
        byte[] b = s.getBytes();
        
        Object o = b;
        boolean check = o instanceof byte[];
        System.out.println("intanceof:"+check);
        byte[] b2 = (byte[])o;
        
        System.out.println("Testing MyComportObserver.update()");
        new ComPortObserverImpl().update(new Observable(), b2);
    }

}
