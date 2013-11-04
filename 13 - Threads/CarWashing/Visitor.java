package CarWashing;

import java.util.Random;

public class Visitor extends Thread {
    public int id;
    private Object washer;

    public Visitor(int i, Object washer) {
        this.id = i;
        this.washer=washer;
    }

    public void run() {
        testFun();
    }

    public synchronized void testFun() {
        try {
            synchronized (washer) {

                System.out.println("Car #" + id +" waiting queue.");
                washer.wait();
                sleep(2000);

                System.out.println("Car #" + id +" has been washed.");
                washer.notify();
            }
        } catch (InterruptedException e) {}

    }
}