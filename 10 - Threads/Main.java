public class Main {

    public static void main(String[] args) {

        // Класс подсчета простых чисел в отдельном потоке
        CalculatePrimes calculator = new CalculatePrimes();

        // Старт потока
        calculator.start();

        try {
            Thread.sleep(1000); // Главный поток "уснет" на секунду
        }
        catch (InterruptedException e) {
            // неудача
        }

        calculator.finished = true; // Изменение volatile переменной другого потока

    }
}
