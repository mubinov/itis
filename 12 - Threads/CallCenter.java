/**
 * Example 2. Call Center (synchronized)
 */
import java.util.Random;

/*
Создание call-центра из 5 работников
 */
public class CallCenter {
    public static void main(String args[]) {

        // Создается очередь звонков
        CallQueue queue = new CallQueue();

        Caller caller = new Caller("Jack", queue);
        caller.start();

        caller = new Caller("Barny", queue);
        caller.start();

        caller = new Caller("Mike", queue);
        caller.start();

        caller = new Caller("Harry", queue);
        caller.start();

        caller = new Caller("Denny", queue);
        caller.start();
    }
}

/*
Звонок
 */
class Call {

    private String description;
    private long duration;

    public Call(String description, long duration) {
        this.description = description;
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return this.duration;
    }

}

/*
Очередь звонков
 */
class CallQueue {

    private Random random = new Random();

    private String[] descriptions = new String[]{
            "Low speed Internet",
            "broke the Internet",
            "The computer does not work",
            "I can not go on facebook"
    };

    private String getRandomDescription() {
        return descriptions[random.nextInt(descriptions.length)];
    }

    public synchronized Call getCall() {
        return new Call(getRandomDescription(), random.nextInt(25) + 1);
    }

}

/*
Класс-работник
 */
class Caller extends Thread {

    private CallQueue queue;
    private String name;

    // Создается работник
    public Caller(String name, CallQueue queue) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {

        // Бесконечно ожидает звонка
        while (true) {
            Call task = queue.getCall();
            process(task);
        }
    }

    // Отвечает
    private void process(Call task) {
        try {
            sleep(task.getDuration() * 1000);
            System.out.println(String.format("%s answered the call \"%s\" in %d seconds",
                    name,
                    task.getDescription(),
                    task.getDuration()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}