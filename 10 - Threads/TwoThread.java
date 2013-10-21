// Первый поток (наследник Thread)
class One extends Thread {

    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("One");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}

// Второй поток (реализует интерфейс Runnable)
class Two implements Runnable {

    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("Two");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}


/*
Главный поток (main)
 */
public class TwoThread  {

    public static void main(String args[]) {

        // Создание потока-наследника Thread
        One first_thread = new One();

        // Создание потока реализующего интерфейс Runnable
        Runnable r = new Two();
        Thread second_thread = new Thread(r);

        // Получение текущего потока (main)
        Thread main_thread = Thread.currentThread();

        System.out.println("Main thread :  " + main_thread);
        System.out.println("First thread :  " + first_thread);
        System.out.println("Second thread :  " + second_thread);

        first_thread.start();
        second_thread.start();
    }

}