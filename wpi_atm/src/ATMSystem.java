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
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            // Display admin menu options
            System.out.println("Administrator Options:");
            System.out.println("1----Create New Account");
            System.out.println("2----Delete Existing Account");
            System.out.println("3----Update Account Information");
            System.out.println("4----Search for Account");
            System.out.println("6----Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            // Perform actions based on selected option
            switch (option) {
                case 1:
                    createNewAccount();
                    break;
                case 2:
                    deleteExistingAccount();
                    break;
                case 3:
                    updateAccountInformation();
                    break;
                case 4:
                    searchForAccount();
                    break;
                case 6:
                    System.out.println("Exiting admin menu.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
}

// Method to create a new account
    private static void createNewAccount() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Creating a new account:");

    // Input account information
    System.out.print("Login: ");
    String login = scanner.nextLine();
    System.out.print("Pin Code: ");
    String pinCode = scanner.nextLine();
    System.out.print("Holder's Name: ");
    String holderName = scanner.nextLine();
    System.out.print("Starting Balance: ");
    double startingBalance = scanner.nextDouble();
    System.out.print("Status (Active/Inactive): ");
    String status = scanner.next();

    UserDetails.addUserToDatabase(holderName, pinCode, login, startingBalance, Integer.parseInt(status));

}

// Method to delete an existing account
    private static void deleteExistingAccount() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Deleting an existing account:");

    // Input account number to delete
    System.out.print("Enter the user account number to delete: ");
    int userAccountNumber = scanner.nextInt();
    System.out.println("You wish to delete the account held by John Doe. If this information is correct, please\n" +
                        "re-enter the account number: " + userAccountNumber);
    int reenterUserAccountNumber = scanner.nextInt();
    // Perform the necessary actions to delete the account
    // Call the appropriate method from UserDetails class to delete the account
        if(userAccountNumber==reenterUserAccountNumber){
            UserDetails.deleteUserFromDatabase(userAccountNumber);
        }else{
            System.out.println("Account # "+ userAccountNumber +" deletion is aborted");
        }



}

// Method to update account information
    private static void updateAccountInformation() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Updating account information:");

    // Input account number to update
    System.out.print("Enter the User account number to update: ");
    String accountNumber = scanner.nextLine();
    System.out.print("Login: ");
    String login = scanner.nextLine();
    System.out.print("Pin Code: ");
    String pinCode = scanner.nextLine();
    System.out.print("Holder's Name: ");
    String holderName = scanner.nextLine();
    System.out.print("Status (Active - true/Inactive - false): ");
    String status = scanner.next();

    UserDetails.updateUserDetailsInDatabase(Integer.parseInt(accountNumber), pinCode, login,holderName, Boolean.parseBoolean(status));
    // Perform the necessary actions to update the account information
    // Call the appropriate method from UserDetails class to update the account information
    // Display appropriate message after updating the account information
    System.out.println("Account information updated successfully.");
}

// Method to search for an account
    private static void searchForAccount() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the account number to search: ");
    int accountNumber = scanner.nextInt();

    UserDetails.searchAccount(accountNumber);
    // Display the retrieved account information
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
