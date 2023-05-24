import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatDB {
    private static CatDB instance;
    private Connection conn = null;

    private CatDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop_db", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized CatDB getInstance() {
        if (instance == null) {
            instance = new CatDB();
        }
        return instance;
    }

    public void createCat(Cat cat) {
        try {
            String query = "INSERT INTO cats (name, age, breed, weight, inDoor, furColor) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, cat.getName());
            statement.setInt(2, cat.getAge());
            statement.setString(3, cat.getBreed());
            statement.setDouble(4, cat.getWeight());
            statement.setBoolean(5, cat.getIsIndoor());
            statement.setString(6, cat.getFurColor());
            statement.executeUpdate();
            statement.close();
            System.out.println("Cat created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cat> getAllCats() {
        List<Cat> cats = new ArrayList<>();
        try {
            String query = "SELECT * FROM cats";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String breed = resultSet.getString("breed");
                Double weight = resultSet.getDouble("weight");
                Boolean inDoor  = resultSet.getBoolean("inDoor");
                String furColor = resultSet.getString("furColor");
                Cat cat = new Cat(name, age, breed, weight, inDoor, furColor);
                cats.add(cat);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cats;
    }

    public void updateCat(Cat cat) {
        try {
            String query = "UPDATE cats SET age = ?, breed = ?, weight = ?, inDoor = ?, furColor = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, cat.getAge());
            statement.setString(2, cat.getBreed());
            statement.setDouble(3, cat.getWeight());
            statement.setBoolean(4, cat.getIsIndoor());
            statement.setString(5, cat.getFurColor());
            statement.setString(6, cat.getName());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println("Cat updated successfully.");
            } else {
                System.out.println("No cat found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCat(String name) {
        try {
            String query = "DELETE FROM cats WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println("Cat deleted successfully.");
            } else {
                System.out.println("No cat found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Created singleton instance
        CatDB CatManager = CatDB.getInstance();

        // Create and Read statements test
        Cat Cat1 = new Cat("Bobby", 12, "Siberian Husky", 45.0, true, "meat");
        CatManager.createCat(Cat1);
        List<Cat> Cats = CatManager.getAllCats();
        System.out.println(Cats.get(0).getName());

        // Update statement test
        Cat firstCat = Cats.get(0);
        firstCat.setBreed("Type2");
        CatManager.updateCat(firstCat);
        Cats = CatManager.getAllCats();
        System.out.println(Cats.get(0).getBreed());

        // Delete statement test
        Cat Cat2 = new Cat("MiMi", 3, "Himalayan", 20.0, true, "white");
        CatManager.createCat(Cat2);
        CatManager.deleteCat("Bobby");
        Cats = CatManager.getAllCats();
        System.out.println(Cats);
    }
}

