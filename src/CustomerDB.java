import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class CustomerDB {
    //    (String name, String address, String phone)
    public static void saveCustomer(String name, String address, String phone) {
// Connect to a datase
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop_db", "root", "Parola11@");
            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            //conn.setAutoCommit(false);


            // Pentru CREATE, folosim INSERT
            // INSERT INTO STUDENTI (ID, NUME, PRENUME, DATA_NASTERII values (1, Popescu, Mihai, 4.05.2003)
            // String insert = "INSERT INTO STUDENTI (NUME, PRENUME, DATA_NASTERII) values ('Popescu', 'Mihai', '2003-05-04 00:00:00.000')";
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO CUSTOMERS (name, address, phone_number) VALUES (\""+name+"\", \""+address+"\", \""+phone+"\")");
        } catch (SQLException e) {
            System.out.println("SQL error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error registering the JDBC driver for MySQL");
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        saveCustomer("Ion", "Strada Arcului 14", "070000000");
    }

}

