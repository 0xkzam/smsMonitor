package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */
public class WorkerTest {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Worker task = new Worker("COM4");
                task.execute();
                
            }
        });

    }
}
