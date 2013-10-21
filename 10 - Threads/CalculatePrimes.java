/*
* Поиск простых чисел за заданное время
 */
public class CalculatePrimes extends Thread {

    public static final int MAX_PRIMES = 1000000; // Самое большое простое число =)

    public volatile boolean finished = false; // не кэшируемая синхронизируемая переменная

    public void run() {
        int[] primes = new int[MAX_PRIMES];
        int count = 0;

        for (int i=2; count<MAX_PRIMES; i++) {

            // Проверка, не сработал ли таймер
            if (finished) {
                break;
            }

            boolean prime = true;
            for (int j=0; j<count; j++) {
                if (i % primes[j] == 0) {
                    prime = false;
                    break;
                }
            }

            if (prime) {
                primes[count++] = i;
                System.out.println("Found prime: " + i);
            }
        }
    }
}