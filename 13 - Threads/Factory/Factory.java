package Factory;

public class Factory {
    public static Object factoryNotificator;

    public static void main(String[] args) {
        factoryNotificator = new Object();

        for (int id = 0; id < 10; id++) {
            Detail detail = new Detail(id, factoryNotificator);
            detail.start();
        }

        Transporter transporter = new Transporter(factoryNotificator);
        transporter.start();
    }
}
