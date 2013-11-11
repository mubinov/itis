import java.net.*;
import java.io.*;
import java.util.Date;

class UCDemo
{

    public static void main(String args[]) throws Exception {
        int c;
        URL hp = new URL("http://ya.ru");
        URLConnection hpCon = hp.openConnection();

        // получить дату
        long d = hpCon.getDate();
        if(d==0)
            System.out.println("Нет информации о дате.");
        else
            System.out.println("Дата: " + new Date(d));

        // получить тип содержимого
        System.out.println("Тип содержимого: " + hpCon.getContentType());

        // получить дату устаревания
        d = hpCon.getExpiration();
        if(d==0)
            System.out.println("Нет информации о сроке действия.");
        else
            System.out.println("Устареет: " + new Date(d));

        // получить дату последней модификации
        d = hpCon.getLastModified();
        if(d==0)
            System.out.println("Нет информации о дате последней модификации.");
        else
            System.out.println("Дата последней модификации: " + new Date(d));

        // получить длину содержимого
        int len = hpCon.getContentLength();
        if(len == -1)
            System.out.println("Длина содержимого недоступна.");
        else
            System.out.println("Длина содержимого: " + len);
        if(len != 0) {
            System.out.println("=== Содержимое ===");
            InputStream input = hpCon.getInputStream();
            int i = len;
            while (((c = input.read()) != -1)) {
                System.out.print((char) c);
            }
            input.close();
        } else {
            System.out.println("Содержимое недоступно.");
        }
    }
}
