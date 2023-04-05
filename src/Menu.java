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
            System.out.println("2. Display all employees");
            System.out.println("3. Display all pets");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    petShop.displayCustomers();
                    break;
                case 2:
                    // display all employees
                    break;
                case 3:
                    // display all pets
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

