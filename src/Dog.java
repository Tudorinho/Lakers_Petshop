import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Dog extends Pet {
    private boolean isFriendly;
    private String favoriteFood;

    public Dog(String name, int age, String breed, double weight, boolean isFriendly, String favoriteFood) {
        super(name, age, breed, weight);
        this.isFriendly = isFriendly;
        this.favoriteFood = favoriteFood;
    }

    public boolean getIsFriendly() {
        return isFriendly;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setIsFriendly(boolean isFriendly) {
        this.isFriendly = isFriendly;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public static void mapDogs() {
        Map<String, Dog> dogs = new HashMap<>();

        Dog dog1 = new Dog("Rex", 3, "Golden Retriever", 54, true, "Fish");
        Dog dog2 = new Dog("Ace", 2, "Labrador Retriever", 32, true, "Chocolate");

        dogs.put(dog1.getName(), dog1);
        dogs.put(dog2.getName(), dog2);

        Dog retrievedDog = dogs.get("Rex");
        System.out.println(retrievedDog.toString());
    }
}

