package BankAccount;

/**
 * Example 3. Bank Account (synchronized, locks, priority, yield)
 */

public class Main {

    public static Logger logger = new Logger ("D:/Test/logger.txt");

    public synchronized static boolean transaction(BankAccount fromAccount, BankAccount toAccount, double money){
        if(fromAccount.getId() != toAccount.getId()){
            if(fromAccount.isAlive() && toAccount.isAlive() && fromAccount.getBalance() >= money ){
                if(
                        fromAccount.removeMoney(money) &&
                        toAccount.addMoney(money)) {

                    logger.log("Transaction: User1 #" + fromAccount.userId + "; User2 #" + toAccount.userId + "; money " + money);

                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        logger.start();

        BankAccount account1 = new BankAccount(1, 1800.00, logger);
        BankAccount account2 = new BankAccount(2, 0.00, logger);
        BankAccount account3 = new BankAccount(3, 1.00, logger);

        account1.setPriority(1);
        account2.setPriority(10);
        account3.setPriority(1);

        account1.start();
        account2.start();
        account3.start();

        transaction(account1, account2, 1700);
    }
}