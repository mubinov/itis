package Factory;

public class Transporter extends Thread {
    private Object factoryNotificator;

    public Transporter(Object factoryNotifier) {
        this.factoryNotificator = factoryNotifier;
    }

    public synchronized void run() {
        try {
            System.out.println("Transporter began to move.");
            sleep(10000);

            synchronized (factoryNotificator) {
                System.out.println("Transporter in place.");
                factoryNotificator.notifyAll();
            }

        } catch (InterruptedException e) {}
    }
}