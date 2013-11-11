import java.net.*;
import java.io.*;

class Whois {

    public static void main(String args[]) throws Exception {
        int c;

        // Создает сокетное соединение с internic.net, порт 43
        Socket s = new Socket("whois.internic.net", 43);

        // Получает входной и выходной потоки.
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        // Конструирует строку запроса
        String str = "mubinov.com\r\n";
        // Преобразует в байты
        byte buf[] = str.getBytes();
        // Посылает запрос
        out.write(buf);
        // Читает и отображает ответ
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
        s.close();
    }

}
