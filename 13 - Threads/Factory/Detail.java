package Factory;

public class Detail extends Thread {
    private int id;
    private Object factoryNotificator;

    public Detail(int id, Object factoryNotifier) {
        this.id = id;
        this.factoryNotificator = factoryNotifier;
    }

    public synchronized void run() {
        try {
            synchronized (factoryNotificator) {
                sleep(100);
                System.out.println("Detail #" + id + " is ready.");
                factoryNotificator.wait();
                System.out.println("Detail #" + id + " is on the board.");
            }
        } catch (InterruptedException e) {}
    }
}