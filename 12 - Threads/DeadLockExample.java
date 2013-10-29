/**
 * Example 5. DeadLock Example
 */

public class DeadLockExample {
    static Object ob1 = new Object();
    static Object ob2 = new Object();

    // Поток 1, заблокирован Объект 1, ожидается Объект 2
    private static class Thread1 extends Thread {
        public void run() {
            synchronized (ob1) {
                System.out.println("Object 1 is locked...");
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                System.out.println("Waiting for Object 2");
                synchronized (ob2) {
                    System.out.println("Object 2 is locked...");
                }
            }
        }
    }

    // Поток 2, заблокирован Объект 2, ожидается Объект 1
    private static class Thread2 extends Thread {
        public void run() {
            synchronized (ob2) {
                System.out.println("Object 2 is locked...");
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                }
                System.out.println("Waiting for Object 1");
                synchronized (ob1) {
                    System.out.println("Object 1 is locked...");
                }
            }
        }
    }

    public static void main(String args[]) {
        Thread th1 = new Thread1();
        Thread th2 = new Thread2();
        th1.start();
        th2.start();
    }
}

