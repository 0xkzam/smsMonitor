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

import java.io.File;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class UtilityTest {
    private String path = Paths.get("").toAbsolutePath().toString();
    
    public UtilityTest() {
    }

    /**
     * Test of isFound method, of class Utility.
     */
    @Test
    public void testIsFound() {
        System.out.println("isFound");
        String name = "";
        String directory = "";
        boolean expResult = false;
        boolean result = Utility.isFound(name, directory);
        assertEquals(expResult, result);   
        
        System.out.println("Testing searchFolder()");        
        
        boolean b = Utility.isFound("src",path);
        assertTrue("Found:",b);
        
        b = Utility.isFound("SRC",path);
        assertFalse("Found:",b);
    }

    /**
     * Test of deleteFile method, of class Utility.
     */
    @Test
    public void testDeleteFile() {
        System.out.println("deleteFile");
                
        String s = "C:\\Users\\user\\Desktop\\New folder";
        File dir = new File(s);        
        try {
            Utility.deleteFile(dir);            
        } catch (IllegalAccessException ex) {    
            assertFalse(ex.toString(),true);
        }
        
        
        
    }
    
}
