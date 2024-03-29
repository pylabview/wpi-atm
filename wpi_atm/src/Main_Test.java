import com.wpi.atm.model.UserDetails;

import java.util.List;
import java.util.Map;

public class Main_Test {
    public static void main(String[] args) {
        // Retrieve user data from the database
//        UserDetails.addUserToDatabase("Marcos Villalva", "67896", "marcoV1234", 1200,1);
//
//        UserDetails.deleteUserFromDatabase(11);

        UserDetails.updateUserDetailsInDatabase(2, "67891", "rod1996", "Rodrigo", true);



        List<UserDetails> userList = UserDetails.getUsersFromDatabase();

        // Display user details
        for (UserDetails user : userList) {
            System.out.println("User ID: " + user.getId());
            System.out.println("Holder: " + user.getHolder());
            System.out.println("Role Description: " + user.getRoleDescription());
            System.out.println("User Login: " + user.getUserLogin());
            System.out.println("Current Balance: " + user.getCurrentBalance());
            System.out.println("Active: " + user.isActive());
            System.out.println("User Login Pin: " + user.getUserLoginPin());
            System.out.println();
        }
//        double currentBalance = UserDetails.getBalanceFromDatabase(2);
//        UserDetails.withdrawFromAccount(2, 100, currentBalance);
//        UserDetails.depositToAccount(2, 150.2, currentBalance);
//        UserDetails.getBalanceFromDatabase(2);

        Map<String, Object> roleInfo = UserDetails.getRoleDescription("admin1", "12345");
        if (roleInfo != null) {
            String roleDescription = (String) roleInfo.get("role_description");
            int userId = (int) roleInfo.get("user_id");
            System.out.println("Role Description: " + roleDescription);
            System.out.println("User ID: " + userId);
        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    }
}
