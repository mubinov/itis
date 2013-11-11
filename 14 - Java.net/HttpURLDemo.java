import java.net.*;
import java.util.*;
class HttpURLDemo
{
    public static void main(String args[]) throws Exception {
        URL hp = new URL("http://www.google.com");
        HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
        System.out.println("Метод запроса: " + hpCon.getRequestMethod());
        System.out.println("Код ответа: " + hpCon.getResponseCode());
        System.out.println("Сообщение ответа: " + hpCon.getResponseMessage());

        // Получить список полей заголовка и набор его ключей.
        Map<String, List<String>> hdrMap = hpCon.getHeaderFields();
        Set<String> hdrField = hdrMap.keySet();
        System.out.println("\nHeader:");

        // Отобразить все ключи и значения заголовка.
        for(String k : hdrField) {
            System.out.println(k + " : " + hdrMap.get(k));
        }
    }
}