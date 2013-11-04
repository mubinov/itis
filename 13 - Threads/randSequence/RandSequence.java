package randSequence;

import java.util.Random;

public class RandSequence  extends Thread{
    public volatile String outString = "";

    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            outString += rand.nextInt(10);
        }
    }
}
