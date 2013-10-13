import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Connection connection = null;

        //URL к базе
        //[протокол]:[подпротокол]://[хост]:[порт_СУБД]/[БД]
        String url = "jdbc:mysql://localhost:3306/test_db";

        String name = "root"; //Имя пользователя БД
        String password = ""; //Пароль

        try {
            //Загружаем драйвер
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Драйвер подключен");

            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");

            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос
            ResultSet result = statement.executeQuery(
                    "SELECT * FROM users where id >2 and id <10");

            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result.next()) {
                System.out.println("Номер в выборке #" + result.getRow()
                        + "\t Номер в базе #" + result.getInt("id")
                        + "\t" + result.getString("login"));
            }

            // Вставить запись
            statement.executeUpdate(
                    "INSERT INTO users(id, login) values(1, 'test')");

            //Обновить запись
            statement.executeUpdate(
                    "UPDATE users SET login = 'admin' where id = 1");


            //2.PreparedStatement: предварительно компилирует запросы,
            //которые могут содержать входные параметры

            PreparedStatement preparedStatement = null;

            // ? - место вставки нашего значеня
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users where id > ? and id < ?");
            //Устанавливаем в нужную позицию значения определённого типа
            preparedStatement.setInt(1, 2);
            preparedStatement.setInt(2, 10);

            //выполняем запрос
            result = preparedStatement.executeQuery();

            System.out.println("Выводим PreparedStatement");
            while (result.next()) {
                System.out.println("Номер в выборке #" + result.getRow()
                        + "\t Номер в базе #" + result.getInt("id")
                        + "\t" + result.getString("login"));
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(id, login) values(?, ?)");

            preparedStatement.setInt(1, 2);
            preparedStatement.setString(2, "test");
            //метод принимает значение без параметров
            //тем же способом можно сделать и UPDATE
            preparedStatement.executeUpdate();


            //3.CallableStatement: используется для вызова хранимых функций,
            // которые могут содержать входные и выходные параметры
            CallableStatement callableStatement = null;

        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
