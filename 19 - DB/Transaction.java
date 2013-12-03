import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction {

    static Connection connection = null;

    static boolean sendMoney(String from, String to, int sum){
       try{
           connection.setAutoCommit(false);
           PreparedStatement preparedStatement =  connection.prepareStatement(
                   "SELECT * FROM `money` where `name` IN(?,?) GROUP BY `name`");
           preparedStatement.setString(1, from);
           preparedStatement.setString(2, to);
           ResultSet result = preparedStatement.executeQuery();

           int acceptFlag = 0;
           while (result.next()) {

               if((result.getString("name").equals(from) && result.getFloat("balance")>=sum) ||
                   result.getString("name").equals(to)){
                   acceptFlag++;
               }
           }
           if(acceptFlag==2){
               preparedStatement =  connection.prepareStatement(
                       "UPDATE `money` SET `balance`=`balance`+? WHERE name=?");
               preparedStatement.setInt(1, sum);
               preparedStatement.setString(2, to);
               preparedStatement.execute();


               preparedStatement =  connection.prepareStatement(
                       "UPDATE `money` SET `balance`=`balance`-? WHERE name=?");
               preparedStatement.setInt(1, sum);
               preparedStatement.setString(2, from);
               preparedStatement.execute();
           }
           connection.commit();

       } catch (Exception ex) {
           Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
           try{
            connection.rollback();
           }  catch (Exception e) {
           }
       }
       return false;
    }

    static boolean simpleSendMoney(String from, String to, int sum){
        try{
            Statement statement = connection.createStatement();

            String query = "UPDATE `money` SET `balance`=`balance`+" + sum + " WHERE name='"+to+"'";
            System.out.println(query);
            statement.executeUpdate(query);

            query = "UPDATE `money` SET `balance`=`balance`-" + sum + " WHERE name='"+from+"'";
            System.out.println(query);
            statement.executeUpdate(query);

        } catch (Exception ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            try{
                connection.rollback();
            }  catch (Exception e) {
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/itis";
        String name = "root";
        String password = "";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, password);

            simpleSendMoney("Alice", "Bob", 1000);
            //sendMoney("Alice", "Bob", 1);

        } catch (Exception ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
