package com.kuz.tmp.control.com_interface;

/**
 *
 * @author Kasun Amarasena
 */
public class ThreadTest {

    private volatile boolean finished = false;

    public void test() {
        Thread t1 = new Thread(new Runnable() {

            @Override
            @SuppressWarnings("empty-statement")
            public void run() {
                while (!finished);
                System.out.println("Thread t1 finished");
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override            
            public void run() {
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException ex) {
                }
                System.out.println("Thread t2 finished");
                finished = true;
            }
        });
        
        t1.start();
        t2.start();
        
    }

    public static void main(String[] args) {
        new ThreadTest().test();
    }
}
