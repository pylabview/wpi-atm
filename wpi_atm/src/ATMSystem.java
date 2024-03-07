import com.wpi.atm.model.UserDetails;

import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    private static int currentUserId;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display login screen
        System.out.println("Welcome to the ATM System");
        System.out.println("Please enter your login and 5-digit pin code:");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Pin code: ");
        String pinCode = scanner.nextLine();
        currentUserId = 0;
        // Verify login and pin code
        Map<String, Object> roleInfo = UserDetails.getRoleDescription(login, pinCode);
        if (roleInfo != null) {
            String roleDescription = (String) roleInfo.get("role_description");
            currentUserId = (int) roleInfo.get("user_id");
            System.out.println("Role Description: " + roleDescription);
            System.out.println("User ID: " + currentUserId);

 // Display appropriate menu based on user's role
            if (roleDescription.equalsIgnoreCase("Admin")) {
                displayAdminMenu();
            } else {
                displayCustomerMenu();
            }
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    }

    // Method to display admin menu
    private static void displayAdminMenu() {
        // Implement admin menu options here
        System.out.println("Admin menu is under construction.");
    }

    // Method to display customer menu
    private static void displayCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Display customer option menu
            System.out.println("Customer Options:");
            System.out.println("1----Withdraw Cash");
            System.out.println("3----Deposit Cash");
            System.out.println("4----Display Balance");
            System.out.println("5----Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            // Perform actions based on selected option
            switch (option) {
                case 1:
                    // Call withdrawCash() method
                    withdrawCash();
                    break;
                case 3:
                    // Call depositCash() method
                    depositCash();
                    break;
                case 4:
                    // Call displayBalance() method
                    displayBalance();
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    // Method to withdraw cash
    private static void withdrawCash() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: ");
        double amount = scanner.nextDouble();
        double currentBalance = UserDetails.getBalanceFromDatabase(currentUserId);
        // Call withdrawFromAccount() method with the specified amount
        UserDetails.withdrawFromAccount(currentUserId, amount, currentBalance); // You need to provide userId
    }

    // Method to deposit cash
    private static void depositCash() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the cash amount to deposit: ");
        double amount = scanner.nextDouble();
        double currentBalance = UserDetails.getBalanceFromDatabase(currentUserId);
        // Call depositToAccount() method with the specified amount
        UserDetails.depositToAccount(currentUserId, amount, currentBalance); // You need to provide userId
    }

    // Method to display balance
    private static void displayBalance() {
        // Call getBalance() method to retrieve balance information
        UserDetails.getBalanceFromDatabase(currentUserId); // You need to provide userId
    }
}
