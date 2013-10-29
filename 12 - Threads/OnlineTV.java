/**
 * Example 1. OnlineTV (Threads stoping and starting)
 */
public class OnlineTV {
    public static void main(String args[]) {

        // Включаем потоковое видео
        TV tv = new TV();

        tv.start();

        try {
            // ждем 2 секунды
            Thread.sleep(2000);

            //Нажали на "паузу"
            tv.pauseTV();

            // ждем 2 секунды
            Thread.sleep(5000);

            //Нажали на "продолжение"
            tv.resumeTV();

            // ждем 2 секунды
            Thread.sleep(5000);

            //Выключаем TV
            tv.stopTV();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class TV extends Thread {

    private int frameId;
    private boolean stopped = false, paused = false;

    // Пауза
    public synchronized void pauseTV(){
        paused = true;
    }

    // Продолжение
    public synchronized void resumeTV(){
        paused = false;
    }

    // Выключение
    public synchronized void stopTV(){
        stopped = true;
    }

    public void run() {
        try {

            // Бесконечно в цикле показывает следующий кадр
            while (!stopped) {
                if(!paused)showNextFrame();
                sleep(10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Показать следующий кадр из потока
    private void showNextFrame() {
        frameId++;
        System.out.println("Showing frame #" + frameId);
    }

}