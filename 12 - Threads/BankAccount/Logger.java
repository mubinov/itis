package BankAccount;
/**
 * Example 3. Bank Account (synchronized, locks)
 */

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.*;

public class Logger extends Thread{
    public volatile boolean deamonDie = false; // флаг для отключения потока
    private DataOutputStream dos;
    private final Lock lock = new ReentrantLock();

    public Logger(String filePath)  {
        try{
            File f=new File(filePath);
            if(f.exists() == false){
                f.createNewFile();
            }
            dos = new DataOutputStream(new FileOutputStream(filePath));
        }catch (IOException e){

        }
    }

    // Не синхронизированный метод
    public void log (String logText) {
        try{
            dos.writeBytes(logText + "\n");
        }catch (IOException e){

        }
    }

    // Cинхронизированный метод
    public synchronized void log_sync (String logText) {
        try{
            dos.writeBytes(logText + "\n");
        }catch (IOException e){

        }
    }

    // Блокирование объекта для синхронизации
    public void log_sync2 (String logText) {
        try{
            synchronized (this){
                dos.writeBytes(logText + "\n");
            }
        }catch (IOException e){

        }
    }

    // Метод с замками
    public void log_lock (String logText) {
        lock.lock();
        try{
            dos.writeBytes(logText + "\n");
        }catch (IOException e){

        }finally {
            lock.unlock();
        }
    }


    public void run() {
        try{
            while(true){
                if(deamonDie){
                    break;
                }
                yield();
                sleep(100);
            }

        }catch(InterruptedException e){
            return;
        }
    }
}