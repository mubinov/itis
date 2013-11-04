package CarWashing;

public class CarWashingMonitor {

    public static Object washer;

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Monitor Log:");

        washer = new Object();
        int numVisitors = 5;

        for (int id = 0; id < numVisitors; id++) {
            Visitor newVisitor=new Visitor(id, washer);
            newVisitor.start();
        }

        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (washer) {
            washer.notify();
        }
    }
}