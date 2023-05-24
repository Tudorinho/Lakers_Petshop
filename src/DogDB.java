import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogDB {
    private static DogDB instance;
    private Connection conn = null;

    private DogDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop_db", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DogDB getInstance() {
        if (instance == null) {
            instance = new DogDB();
        }
        return instance;
    }

    public void createDog(Dog dog) {
        try {
            String query = "INSERT INTO dogs (name, age, breed, weight, isFriendly, favoriteFood) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dog.getName());
            statement.setInt(2, dog.getAge());
            statement.setString(3, dog.getBreed());
            statement.setDouble(4, dog.getWeight());
            statement.setBoolean(5, dog.getIsFriendly());
            statement.setString(6, dog.getFavoriteFood());
            statement.executeUpdate();
            statement.close();
            System.out.println("Dog created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dog> getAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        try {
            String query = "SELECT * FROM dogs";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String breed = resultSet.getString("breed");
                Double weight = resultSet.getDouble("weight");
                Boolean isFriendly  = resultSet.getBoolean("isFriendly");
                String favoriteFood = resultSet.getString("favoriteFood");
                Dog dog = new Dog(name, age, breed, weight, isFriendly, favoriteFood);
                dogs.add(dog);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dogs;
    }

    public void updateDog(Dog dog) {
        try {
            String query = "UPDATE dogs SET age = ?, breed = ?, weight = ?, isFriendly = ?, favoriteFood = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, dog.getAge());
            statement.setString(2, dog.getBreed());
            statement.setDouble(3, dog.getWeight());
            statement.setBoolean(4, dog.getIsFriendly());
            statement.setString(5, dog.getFavoriteFood());
            statement.setString(6, dog.getName());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println("Dog updated successfully.");
            } else {
                System.out.println("No dog found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDog(String name) {
        try {
            String query = "DELETE FROM dogs WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println("Dog deleted successfully.");
            } else {
                System.out.println("No dog found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Created singleton instance
        DogDB DogManager = DogDB.getInstance();

        // Create and Read statements test
        Dog Dog1 = new Dog("Bobby", 12, "Siberian Husky", 45.0, true, "meat");
        DogManager.createDog(Dog1);
        List<Dog> Dogs = DogManager.getAllDogs();
        System.out.println(Dogs.get(0).getName());

        // Update statement test
        Dog firstDog = Dogs.get(0);
        firstDog.setBreed("Type2");
        DogManager.updateDog(firstDog);
        Dogs = DogManager.getAllDogs();
        System.out.println(Dogs.get(0).getBreed());

        // Delete statement test
        Dog Dog2 = new Dog("Grande", 3, "Golden Retriever", 60.0,true, "sushi");
        DogManager.createDog(Dog2);
        DogManager.deleteDog("Bobby");
        Dogs = DogManager.getAllDogs();
        System.out.println(Dogs);
    }
}

