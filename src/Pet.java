import java.util.List;
import java.util.Set;

public class Pet {
    private String name;
    private int age;
    private String breed;
    private double weight;

    public Pet(String name, int age, String breed, double weight) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static void displayPets(List<Pet> pets) {
        for (Pet pet : pets) {
            System.out.println(pet.toString());
        }
    }
}

