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

package com.viewer;

import com.model.DatabaseConnection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class HelperTest {
    
    public HelperTest() {
    }



    /**
     * Test of isFound method, of class Helper.
     */
    @Test
    public void testIsFound() {
        System.out.println("Testing searchFolder()");        
        
        boolean b = Helper.isFound("src");
        assertTrue("Found:",b);
        
        b = Helper.isFound("SRC");
        assertFalse("Found:",b);
    }
    
}
