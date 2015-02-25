
package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.control.com_interface.MessageObserver;
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
