import java.util.concurrent.Semaphore;

class MyThread extends Thread {
    private int id;
    private Semaphore mSemaphore = null;

    public MyThread(int id, Semaphore semaphore) {
        this.id = id;
        mSemaphore = semaphore;
    }

    public void run() {
        try {
            mSemaphore.acquire();
            System.out.println("Thread #" + id + ": blocked the semaphore.");
            sleep(1000);
        } catch (InterruptedException ex) {

        } finally {
            System.out.println("Thread #" + id + ":semaphore unblocked.");
            mSemaphore.release();
        }
    }
}

public class SemaphoreRealize {
    private static Semaphore mSemaphor = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyThread th = new MyThread(i, mSemaphor);
            th.start();
        }
    }
}
