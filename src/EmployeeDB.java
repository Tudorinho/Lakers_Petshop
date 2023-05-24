import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {
    private static EmployeeDB instance;
    private static Connection conn = null;

    private EmployeeDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop_db", "root", "Parola11@");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void hireEmployeeDB(Employee employee) {
//        createEmployee(employee);
//    }

    public static EmployeeDB getInstance() {
        if (instance == null) {
            instance = new EmployeeDB();
        }
        return instance;
    }

    public void createEmployee(Employee employee) {
        try {
            String query = "INSERT INTO employees (name, age, salary) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setDouble(3, employee.getSalary());
            statement.executeUpdate();
            statement.close();
            System.out.println("Employee created successfully(database).");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employees";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(name, age, salary);
                employees.add(employee);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void updateEmployee(Employee employee) {
        try {
            String query = "UPDATE employees SET age = ?, salary = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, employee.getAge());
            statement.setDouble(2, employee.getSalary());
            statement.setString(3, employee.getName());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("No employee found with the given name.");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String name) {
        try {
            String query = "DELETE FROM employees WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("No employee found with the given name.");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Created singleton instance
        EmployeeDB EmployeeManager = EmployeeDB.getInstance();

        // Create and Read statements test
        Employee employee1 = new Employee("Maria", 25, 5000.0);
        EmployeeManager.createEmployee(employee1);
        List<Employee> Employees = EmployeeManager.getAllEmployees();
        System.out.println(Employees);

        // Update statement test
        Employee firstEmployee = Employees.get(0);
        firstEmployee.setAge(135);
        EmployeeManager.updateEmployee(firstEmployee);
        Employees = EmployeeManager.getAllEmployees();
        System.out.println(Employees.get(0).getAge());

        // Delete statement test
        Employee employee2 = new Employee("Ion", 43, 7500.0);
        EmployeeManager.createEmployee(employee2);
        EmployeeManager.deleteEmployee("Ion");
        Employees = EmployeeManager.getAllEmployees();
        System.out.println(Employees);

        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
