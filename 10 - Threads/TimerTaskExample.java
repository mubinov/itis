import java.util.*;

public class TimerTaskExample {
    public static void main(String[] args) {
        Timer timer = new Timer();

        final CalculatePrimes calculator = new CalculatePrimes();
        calculator.start();
        TimerTask tt = new TimerTask() {
            public void run()
            {
                calculator.finished = true;
            }
        };

        timer.schedule(tt, 10000);
    }
}
