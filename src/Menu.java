import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    PetShopSystem petShop;

    public Menu(PetShopSystem petShop) {
        this.petShop = petShop;
        this.scanner = new Scanner(System.in);
    }

//    CustomerDB customerManager = CustomerDB.getInstance();
//    DogDB DogManager = DogDB.getInstance();
//    CatDB CatManager = CatDB.getInstance();
//    EmployeeDB EmployeeManager = EmployeeDB.getInstance();
//    PetDB petManager = PetDB.getInstance();

    public void display() {
        while (true) {
            System.out.println("Pet Shop Menu:");
            System.out.println("1. Display all customers");
            System.out.println("2. Display customers with pets");
            System.out.println("3. Display all employees");
            System.out.println("4. Hire employee");
            System.out.println("5. Fire employee");
            System.out.println("6. Update employee");
            System.out.println("7. Display all pets");
//            System.out.println("7. Feed the pets");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    petShop.displayCustomers();
                    AuditLogger.logAction("Read Operation");
                    break;
                case 2:
                    petShop.displayCustomers(petShop.getCustomersWithPets());
                    AuditLogger.logAction("Read Operation");
                    break;
                case 3:
                    petShop.displayEmployees();
                    AuditLogger.logAction("Read Operation");
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
                    petShop.hireEmployeeDB(newEmployee);
                    System.out.println("Employee hired: " + newEmployee);
                    AuditLogger.logAction("Create Operation");
                    break;
                case 5:
                    System.out.print("Enter the name of the employee to be fired: ");
                    String employeeName = scanner.nextLine();
                    petShop.fireEmployeeDB(employeeName);
                    System.out.println("Employee fired successfully.");
                    AuditLogger.logAction("Delete Operation");
                    break;
                case 6:
                    // Prompt user for employee details
                    System.out.print("Enter the name of the employee to update: ");
                    String employeeName1 = scanner.nextLine();
                    System.out.print("Enter the new age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter the new salary: ");
                    double salary = scanner.nextDouble();

                    scanner.nextLine(); // Consume newline character

                    // Create an Employee object with the updated details
                    Employee updatedEmployee = new Employee(employeeName1, age, salary);

                    // Call the updateEmployeeDB method in PetShopSystem instance
                    petShop.updateEmployeeDB(updatedEmployee);
                    AuditLogger.logAction("Update Operation");
                    break;
                case 7:
                    petShop.displayPets();
                    AuditLogger.logAction("Read Operation");
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

