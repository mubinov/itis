import java.util.*;

class RandmizeCount extends Thread{
    public void run() {
        Random rand = new Random();

        // В бесконечном цикле выводим случайное число и номер текущего потока
        for (int j=0; j<100; j++)
            System.out.println("Thread#" + this.getId() + " say: " + rand.nextInt(100));
    }
}

public class RandThreads {
    public static void main(String[] args) {

        // Создадим 10 потоков
        for (int j=0; j<10; j++) {
            // Создаем
            RandmizeCount randThread = new RandmizeCount();

            //Запускаем
            randThread.start();
            System.out.println("Thread#" + randThread.getId() + " started.");
        }

        // Главный поток ожидает заврешения других потоков
    }
}
