import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDB {
    private static PetDB instance;
    private Connection conn = null;

    private PetDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/petshop_db", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PetDB getInstance() {
        if (instance == null) {
            instance = new PetDB();
        }
        return instance;
    }

    public void createPet(Pet pet) {
        try {
            String query = "INSERT INTO pets (name, age, breed, weight) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, pet.getName());
            statement.setInt(2, pet.getAge());
            statement.setString(3, pet.getBreed());
            statement.setDouble(4, pet.getWeight());
            statement.executeUpdate();
            statement.close();
            System.out.println("Pet created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        try {
            String query = "SELECT * FROM pets";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String breed = resultSet.getString("breed");
                double weight = resultSet.getDouble("weight");
                Pet pet = new Pet(name, age, breed, weight);
                pets.add(pet);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    public void updatePet(Pet pet) {
        try {
            String query = "UPDATE pets SET age = ?, breed = ?, weight = ? WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, pet.getAge());
            statement.setString(2, pet.getBreed());
            statement.setDouble(3, pet.getWeight());
            statement.setString(4, pet.getName());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println("Pet updated successfully.");
            } else {
                System.out.println("No pet found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePet(String name) {
        try {
            String query = "DELETE FROM pets WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println("Pet deleted successfully.");
            } else {
                System.out.println("No pet found with the given name.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Created singleton instance
        PetDB petManager = PetDB.getInstance();

        // Create and Read statements test
        Pet pet1 = new Pet("Bobby", 12, "Siberian Husky", 45.0);
        petManager.createPet(pet1);
        List<Pet> pets = petManager.getAllPets();
        System.out.println(pets.get(0).getName());

        // Update statement test
        Pet firstPet = pets.get(0);
        firstPet.setBreed("Type2");
        petManager.updatePet(firstPet);
        pets = petManager.getAllPets();
        System.out.println(pets.get(0).getBreed());

        // Delete statement test
        Pet pet2 = new Pet("Grande", 3, "Golden Retriever", 60.0);
        petManager.createPet(pet2);
        petManager.deletePet("Bobby");
        pets = petManager.getAllPets();
        System.out.println(pets);
    }
}