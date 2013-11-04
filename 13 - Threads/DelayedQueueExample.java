import java.util.concurrent.*;

public class DelayedQueueExample {
    public static void main(String[] args) {

        int n = 4;
        DelayQueue<DelayedThread> queue=new DelayQueue<DelayedThread>();

        for (int i = 0; i < n; i++){
            queue.add(new DelayedThread());
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(!queue.isEmpty()){
            new Thread(queue.poll()).start();
        }
    }
}

class DelayedThread implements Runnable,Delayed {
    static public int count=0;
    private TimeUnit time=TimeUnit.NANOSECONDS;
    private int id=count++;

    public void run() {
        System.out.println("Thread #" + id + " started");
    }

    public long getDelay(TimeUnit unit){
        return unit.convert(id*100000000000L-System.nanoTime(),time) ;
    }

    public int compareTo(Delayed o) {
        if(this.getDelay(time)<o.getDelay(time))
            return -1;
        if(this.getDelay(time)>o.getDelay(time))
            return 1;
        return 0;
    }
}