// Proiect Programare Avansata pe Obiecte -> Java
// Echipa Lakers formata din Nastase Antonio si Tudor Dan
// Idee de proiect: Pet shop
public class Main {
    public static void main(String[] args) {

        // Create a new pet shop system
        PetShopSystem petShop = new PetShopSystem();

        // Add a customer to the system
        Customer customer1 = new Customer("George", "1 Mai Street", "0744234543");
        Customer customer2 = new Customer("Marc", "2 iFEF", "073838484");
        petShop.addCustomer(customer1);
        petShop.addCustomer(customer2);

        // Add a dog to the system
        Pet pet1 = new Dog("Grande", 3, "Golden Retriever", 60.0, true, "sushi");
        petShop.addPet(pet1);
        customer1.addPet(pet1);

        // Add a cat to the system
        Pet pet2 = new Cat("MiMi", 3, "Himalayan", 20.0, true, "white");
        petShop.addPet(pet2);
        customer1.addPet(pet2);

        Pet pet3 = new Dog("Bobby", 12, "Siberian Husky", 45.0, true, "meat");
        petShop.addPet(pet3);
        customer2.addPet(pet3);

        // Hire 2 employees
        Employee employee1 = new Employee("Maria", 25, 5000.0);
        petShop.hireEmployee(employee1);
        Employee employee2 = new Employee("Ion", 43, 7500.0);
        petShop.hireEmployee(employee2);


        // Interactive menu
        Menu menu = new Menu(petShop);
        menu.display();
    }
}
