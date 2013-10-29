package BankAccount;

/**
 * Example 3. Bank Account (synchronized, locks)
 */

public class BankAccount extends Thread{

    public int userId;  // id пользователя
    private double balance = 0; // сумма на счету
    public volatile boolean deamonDie = false; // флаг для отключения потока
    public Logger logger;

    BankAccount(int userId, double startBalance, Logger logger){
        this.userId = userId;
        balance = startBalance;
        balance = startBalance;
        this.logger = logger;

        this.logger.log("User #" + userId + ": start balance: " + getBalance());
    }

    public void run(){
        System.out.println("Account " + userId + " is active");
        try{
            while(true){
                if(deamonDie){
                    break;
                }
                yield(); // передача управления другому потоку
                sleep(100);

                // Пример потенциальных проблем с синхронизацией потоков
                //logger.log("Thread for User #" + userId + " is Alive");
            }
        }catch (InterruptedException e){

        }
        System.out.println("Account " + userId + " closed");
    }

    public synchronized boolean addMoney(double sum){
        if(sum > 0){
            balance += sum;

            logger.log("User #" + userId + ": added: " + sum);
            logger.log("User #" + userId + ": current balance: " + getBalance());

            return true;
        }else{
            return false;
        }
    }

    public synchronized boolean removeMoney(double sum){

        if(sum > 0 && balance > sum){
            balance -= sum;
            logger.log("User #" + userId + ": removed: " + sum);
            logger.log("User #" + userId + ": current balance: " + getBalance());
            return true;
        }else{
            return false;
        }
    }

    public synchronized double getBalance(){
        return balance;
    }

}
