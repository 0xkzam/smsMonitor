
package com.kuz.tmp.old.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kasun Amarasena
 */
public class UtilTest {
    
    public UtilTest() {
    }
    

    @Test
    public void test() {
        
        String s="2015-02-04";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        try {
            date = format.parse(s);           
            System.out.println("date:"+date);
            
        } catch (ParseException ex) {
            fail();
        }
        
    }
    
}
