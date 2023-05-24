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

    CustomerDB customerManager = CustomerDB.getInstance();
    DogDB DogManager = DogDB.getInstance();
    CatDB CatManager = CatDB.getInstance();
    EmployeeDB EmployeeManager = EmployeeDB.getInstance();
    PetDB petManager = PetDB.getInstance();

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


    // Method to remove an employee from the employee map
    public void removeEmployee(String employeeId) {
        employees.remove(employeeId);
    }

    public boolean fireEmployee(String employeeName) {
        Employee employee = findEmployee(employeeName);
        if (employee == null) {
            System.out.println("Employee not found");
            return false;
        }
        employees.remove(employeeName);
        System.out.println(employeeName + " has been fired.");
        return true;
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

//    public Employee findEmployee(String name) {
//        return employees.get(name);
//    }

    public static Employee findEmployee(String name, Map<String, Employee> employees) {
        return employees.get(name);
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

    //displayCustomers with list of customers as parameter(for the customers with pets)
    public void displayCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println("Customers with pets:");
            System.out.println("---- " + customer);
        }
        System.out.println(System.lineSeparator());
    }

    //displayCustomers with no parameters(representing all the customers)
    public void displayCustomers() {
        System.out.println("All our customers:");
        for(Customer customer : this.customerManager.getAllCustomers()) {
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


    public void displayEmployees() {
        if(employees.isEmpty()) {
            System.out.println("There are no employees in the system.");
            return;
        }
        System.out.println("List of employees in the system:");
        for (Map.Entry<String, Employee> entry : employees.entrySet()) {
            Employee employee = entry.getValue();
            System.out.println("Name: " + employee.getName() + ", Age: " + employee.getAge() + ", Salary: " + employee.getSalary());
        }
    }


    public void displayPets() {
        for (Pet pet : this.petManager.getAllPets()) {
            if (pet instanceof Dog) {
                Dog dog = (Dog) pet;
                System.out.println("Dog:");
                System.out.println("Name: " + dog.getName());
                System.out.println("Age: " + dog.getAge());
                System.out.println("Breed: " + dog.getBreed());
                System.out.println("Weight: " + dog.getWeight());
                System.out.println("Is friendly: " + dog.getIsFriendly());
                System.out.println("Favorite food: " + dog.getFavoriteFood());
                System.out.println();
            } else if (pet instanceof Cat) {
                Cat cat = (Cat) pet;
                System.out.println("Cat:");
                System.out.println("Name: " + cat.getName());
                System.out.println("Age: " + cat.getAge());
                System.out.println("Breed: " + cat.getBreed());
                System.out.println("Weight: " + cat.getWeight());
                System.out.println("Is indoor: " + cat.getIsIndoor());
                System.out.println("Fur color: " + cat.getFurColor());
                System.out.println();
            }
            else{
                System.out.println("Name: " + pet.getName());
                System.out.println("Age: " + pet.getAge());
                System.out.println("Breed: " + pet.getBreed());
                System.out.println("Weight: " + pet.getWeight());
            }
        }
    }
}



