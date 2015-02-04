

package com.kuz.tmp.old.controller;

import com.kuz.tmp.old.controller.FileUtil;
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
     * Test of isFound method, of class FileUtil.
     */
    @Test
    public void testIsFound() {
        System.out.println("isFound");
        String name = "";
        String directory = "";
        boolean expResult = false;
        boolean result = FileUtil.isFound(name, directory);
        assertEquals(expResult, result);   
        
        System.out.println("Testing searchFolder()");        
        
        boolean b = FileUtil.isFound("src",path);
        assertTrue("Found:",b);
        
        b = FileUtil.isFound("SRC",path);
        assertFalse("Found:",b);
    }

    /**
     * Test of deleteFile method, of class FileUtil.
     */
    @Test
    public void testDeleteFile() {
        System.out.println("deleteFile");
                
        String s = "C:\\Users\\user\\Desktop\\New folder";
        File dir = new File(s);        
        try {
            FileUtil.deleteFile(dir);            
        } catch (IllegalAccessException ex) {    
            fail(ex.toString());
        }
        
        
        
    }
    
}
