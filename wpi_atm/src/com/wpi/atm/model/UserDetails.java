package com.wpi.atm.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDetails {
    private final int id;
    private final String holder, roleDescription, userLogin, userLoginPin;
    private int balance;
    private boolean active;

    private static String jdbcUrl = "jdbc:mysql://localhost:3306/atm";
    private static String username = "root";
    private static String password = "Cucuta1983!@#$";

    public UserDetails(int id, String holder,
                       String roleDescription,
                       String userLogin, String userLoginPin,
                       int balance,
                       boolean active) {
        this.id = id;
        this.holder = holder;
        this.roleDescription = roleDescription;
        this.userLogin = userLogin;
        this.userLoginPin = userLoginPin;
        this.balance = balance;
        this.active = active;
    }

    public String getUserLoginPin() {
        return userLoginPin;
    }

    // Getters for the fields
    public int getId() {
        return id;
    }

    public String getHolder() {
        return holder;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public int getCurrentBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }



    // Method to retrieve user data from MySQL database and return a list of UserDetails objects
    public static List<UserDetails> getUsersFromDatabase() {
        List<UserDetails> userList = new ArrayList<>();

        String query = "select users.id, users.holder, users.user_login, users.user_login_pin, roles.role_description, accounts.active, accounts.balance from users " +
                "join accounts on  users.id = accounts.user_id " +
                "join roles on users.id = roles.user_id ";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String holder = rs.getString("holder");
                String roleDescription = rs.getString("role_description");
                String userLogin = rs.getString("user_login");
                int currentBalance = rs.getInt("balance");
                boolean active = rs.getBoolean("active");
                String userLoginPin = rs.getString("user_login_pin");

                userList.add(new UserDetails(id, holder, roleDescription, userLogin, userLoginPin, currentBalance, active));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

     public static boolean addUserToDatabase(String holder, String userLoginPin, String userLogin, int balance) {

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement()) {

            // Execute SQL query to insert new user
            String insertQuery = "INSERT INTO users (user_type, holder, user_login_pin, user_login) " +
                    "VALUES (0, '" + holder + "', '" + userLoginPin + "', '" + userLogin + "');";
            stmt.executeUpdate(insertQuery);

            // Get the last inserted user ID
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int userId = 0;
            if (rs.next()) {
                userId = rs.getInt(1);
            }

            // Execute SQL query to insert user role
            String insertRoleQuery = "INSERT INTO roles (role_description, user_id) " +
                    "VALUES ('Customer', " + userId + ");";
            stmt.executeUpdate(insertRoleQuery);

            // Execute SQL query to insert user account
            String insertAccountQuery = "INSERT INTO accounts (user_id, balance, active) " +
                    "VALUES (" + userId + ", balance, 1);";
            stmt.executeUpdate(insertAccountQuery);

            System.out.println("New user added successfully.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

        public static boolean deleteUserFromDatabase(int userId) {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        boolean deletedOK = false;

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, userId);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("User with ID " + userId + " does not exist.");
            } else {
                System.out.println("User with ID " + userId + " deleted successfully.");
                deletedOK = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedOK;
    }

     public static boolean updateUserDetailsInDatabase(int userId, String userLoginPin, String userLogin, String holder, boolean active) {
        String updateUserQuery = "UPDATE users SET user_login_pin = ?, user_login = ?, holder = ? WHERE id = ?";
        String updateAccountQuery = "UPDATE accounts SET active = ? WHERE user_id = ?";
        boolean updateOkay = false;

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement userStmt = conn.prepareStatement(updateUserQuery);
             PreparedStatement accountStmt = conn.prepareStatement(updateAccountQuery)) {

            // Update user details
            userStmt.setString(1, userLoginPin);
            userStmt.setString(2, userLogin);
            userStmt.setString(3, holder);
            userStmt.setInt(4, userId);
            int userUpdatedRows = userStmt.executeUpdate();

            // Update account status
            accountStmt.setBoolean(1, active);
            accountStmt.setInt(2, userId);
            int accountUpdatedRows = accountStmt.executeUpdate();

            if (userUpdatedRows > 0 || accountUpdatedRows > 0) {
                System.out.println("User details updated successfully.");
                updateOkay = true;
            } else {
                System.out.println("No changes were made.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateOkay;
    }

    public static void getBalanceFromDatabase(int userId) {
        // Query to set the current timestamp
        String setTimestampQuery = "SET @now = CURRENT_TIMESTAMP()";

        // Query to select user's balance
        String selectQuery = "SELECT accounts.balance, accounts.id AS account_number, users.holder, @now AS time_stamp " +
                             "FROM users " +
                             "JOIN accounts ON accounts.user_id = users.id " +
                             "WHERE users.id = ?";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmtSetTimestamp = conn.prepareStatement(setTimestampQuery);
             PreparedStatement stmtSelect = conn.prepareStatement(selectQuery)) {

            // Execute the SET statement
            stmtSetTimestamp.executeUpdate();

            // Set the user ID parameter for the SELECT statement
            stmtSelect.setInt(1, userId);

            // Execute the SELECT statement
            ResultSet rs = stmtSelect.executeQuery();

            if (rs.next()) {
                int balance = rs.getInt("balance");
                int accountNumber = rs.getInt("account_number");
                String holder = rs.getString("holder");
                String timeStamp = rs.getString("time_stamp");

                System.out.println("Balance: " + balance);
                System.out.println("Account Number: " + accountNumber);
                System.out.println("Holder: " + holder);
                System.out.println("Timestamp: " + timeStamp);
            } else {
                System.out.println("No balance found for user with ID " + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public static void withdrawFromAccount(int userId, double amount, double currentBalance) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        // Query to update user's balance for withdrawal
        String updateWithdrawBalanceQuery = "UPDATE accounts SET balance = balance - ? WHERE user_id = ?";

        // Query to record the withdrawal transaction
        String insertWithdrawalTransactionQuery = "INSERT INTO transactions (type) VALUES (?)";

        // Query to link the withdrawal transaction to the user
        String linkWithdrawalTransactionQuery = "INSERT INTO users_transactions (transaction_id, user_id) VALUES (LAST_INSERT_ID(), ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmtUpdateBalance = conn.prepareStatement(updateWithdrawBalanceQuery);
             PreparedStatement stmtInsertWithdrawalTransaction = conn.prepareStatement(insertWithdrawalTransactionQuery);
             PreparedStatement stmtLinkWithdrawalTransaction = conn.prepareStatement(linkWithdrawalTransactionQuery)) {

            if (currentBalance < amount) {
                System.out.println("Insufficient funds for withdrawal.");
                return;
            }

            // Withdraw amount from the balance
            stmtUpdateBalance.setDouble(1, amount);
            stmtUpdateBalance.setInt(2, userId);
            stmtUpdateBalance.executeUpdate();

            // Record the withdrawal transaction
            stmtInsertWithdrawalTransaction.setString(1, "Withdraw");
            stmtInsertWithdrawalTransaction.executeUpdate();

            // Link the withdrawal transaction to the user
            stmtLinkWithdrawalTransaction.setInt(1, userId);
            stmtLinkWithdrawalTransaction.executeUpdate();

            // Display withdrawal details
            System.out.println("Cash Successfully Withdrawn");
            System.out.println("Account #" + userId);
            System.out.println("Date: " + "01/29/2024"); // Assuming a static date for this example
            System.out.println("Withdrawn: " + amount);
            System.out.println("Balance: " + (currentBalance - amount));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public static void depositToAccount(int userId, double amount) {
        // Query to update user's balance for deposit
        String updateDepositBalanceQuery = "UPDATE accounts SET balance = balance + ? WHERE user_id = ?";

        // Query to record the deposit transaction
        String insertDepositTransactionQuery = "INSERT INTO transactions (type) VALUES (?)";

        // Query to link the deposit transaction to the user
        String linkDepositTransactionQuery = "INSERT INTO users_transactions (transaction_id, user_id) VALUES (LAST_INSERT_ID(), ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmtUpdateBalance = conn.prepareStatement(updateDepositBalanceQuery);
             PreparedStatement stmtInsertDepositTransaction = conn.prepareStatement(insertDepositTransactionQuery);
             PreparedStatement stmtLinkDepositTransaction = conn.prepareStatement(linkDepositTransactionQuery)) {

            // Deposit funds into the user's account
            stmtUpdateBalance.setDouble(1, amount);
            stmtUpdateBalance.setInt(2, userId);
            stmtUpdateBalance.executeUpdate();

            // Record the deposit transaction
            stmtInsertDepositTransaction.setString(1, "Deposit");
            stmtInsertDepositTransaction.executeUpdate();

            // Link the deposit transaction to the user
            stmtLinkDepositTransaction.setInt(1, userId);
            stmtLinkDepositTransaction.executeUpdate();

            System.out.println("Deposit successful.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
