import java.net.*;

class URLDemo {
    public static void main(String args[]) throws MalformedURLException {
        URL hp = new URL("http://itis.itpark-kazan.ru/node/3");
        System.out.println("Протокол: " + hp.getProtocol());
        System.out.println("Порт: " + hp.getPort());
        System.out.println("Хост: " + hp.getHost());
        System.out.println("Файл: " + hp.getFile());
        System.out.println("Полный путь: " + hp.toExternalForm());
    }
}