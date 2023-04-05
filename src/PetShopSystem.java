import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PetShopSystem {
    private List<Customer> customers;
    private Set<Pet> pets;
    private Map<String, Employee> employees;

    public PetShopSystem() {
        customers = new ArrayList<>();
        pets = new HashSet<>();
        employees = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
    }

    public void hireEmployee(Employee employee) {
        employees.put(employee.getName(), employee);
    }

    public void fireEmployee(String name) {
        employees.remove(name);
    }

    public List<Pet> getAllPets() {
        List<Pet> allPets = new ArrayList<>();
        allPets.addAll(pets);

        for (Customer customer : customers) {
            allPets.addAll(customer.getPets());
        }

        return allPets;
    }

    public List<Customer> getCustomersWithPets() {
        List<Customer> customersWithPets = new ArrayList<>();

        for (Customer customer : customers) {
            if (!customer.getPets().isEmpty()) {
                customersWithPets.add(customer);
            }
        }

        return customersWithPets;
    }

    public Employee findEmployee(String name) {
        return employees.get(name);
    }

    public static Set<Dog> filterDogs(Set<Pet> pets) {
        Set<Dog> dogs = new HashSet<>();

        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                dogs.add((Dog) pet);
            }
        }

        return dogs;
    }

    public void displayCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("Customers with pets:");
            System.out.println("---- " + customer);
        }
        System.out.println(System.lineSeparator());
    }

    public void displayCustomers() {
        System.out.println("All our customers:");
        for(Customer customer : this.customers) {
            System.out.println("---- " + customer);
        }
        System.out.println(System.lineSeparator());
    }

    public void feedPets() {
        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
                System.out.println(dog.getName() + " is eating " + dog.getFavoriteFood());
            } else {
                System.out.println(pet.getName() + " is eating cat food");
            }
        }
        System.out.println(System.lineSeparator());
    }
}
