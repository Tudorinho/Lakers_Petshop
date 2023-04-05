import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    PetShopSystem petShop;

    public Menu(PetShopSystem petShop) {
        this.petShop = petShop;
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("Pet Shop Menu:");
            System.out.println("1. Display all customers");
            System.out.println("2. Display customers with pets");
            System.out.println("3. Display all employees");
            System.out.println("4. Hire employee");
            System.out.println("5. Fire employee");
            System.out.println("6. Display all pets");
            System.out.println("7. Feed the pets");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    petShop.displayCustomers();
                    break;
                case 2:
                    petShop.displayCustomers(petShop.getCustomersWithPets());
                    break;
                case 3:
                    petShop.displayEmployees();
                    break;
                case 4:
                    System.out.print("Enter employee name: ");
                    String empName = scanner.nextLine();
                    System.out.print("Enter employee age: ");
                    int empAge = scanner.nextInt();
                    System.out.print("Enter employee salary: ");
                    double empSalary = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character

                    Employee newEmployee = new Employee(empName, empAge, empSalary);
                    petShop.hireEmployee(newEmployee);
                    System.out.println("Employee hired: " + newEmployee);

                    break;
                case 5:
                    System.out.print("Enter the name of the employee to be fired: ");
                    String employeeName = scanner.nextLine();
                    boolean result = petShop.fireEmployee(employeeName);
                    if (!result) {
                        System.out.println("Failed to fire employee");
                    }
                    break;

                case 6:
                    petShop.displayPets();
                    break;
                case 7:
                    petShop.feedPets();
                    break;
                case 0:
                    // exit the program
                    System.out.println("Exiting Pet Shop...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

