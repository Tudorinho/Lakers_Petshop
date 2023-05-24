import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    private static CustomerDB instance = null;
    private static Connection conn = null;
    private CustomerDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop_db", "root", "Parola11@");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CustomerDB getInstance() {
        if (instance == null) {
            instance = new CustomerDB();
        }
        return instance;
    }

    public void saveCustomer(String name, String address, String phone) {
        // Connect to a datase
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO CUSTOMERS (name, address, phone_number) VALUES (\"" + name + "\", \"" + address + "\", \"" + phone + "\")");
        } catch (SQLException e) {
            System.out.println("SQL error");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error registering the JDBC driver for MySQL");
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            String query = "SELECT * FROM customers";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                Customer customer = new Customer(name, address, phone);
                customers.add(customer);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void updateCustomer(Customer customer) {
        try {
            String query = "UPDATE customers SET address = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, customer.getAddress());
            statement.setString(2, customer.getName());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("No customer found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String name) {
        try {
            String query = "DELETE FROM customers WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("No customer found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Created singleton instance
        CustomerDB customerManager = CustomerDB.getInstance();

        // Create and Read statements test
        customerManager.saveCustomer("Ion", "Strada Arcului 14", "070000000");
        List<Customer> customers = customerManager.getAllCustomers();
        System.out.println(customers);

        // Update statement test
        Customer firstCustomer = customers.get(0);
        firstCustomer.setAddress("Strada Modificata");
        customerManager.updateCustomer(firstCustomer);
        customers = customerManager.getAllCustomers();
        System.out.println(customers.get(0).getAddress());

        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Delete statement test
        customerManager.saveCustomer("Darius", "Strada Arcului 14", "070000000");
        customerManager.deleteCustomer("Ion");
        customers = customerManager.getAllCustomers();
        System.out.println(customers);
    }
}