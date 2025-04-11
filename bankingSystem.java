import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import exceptions.*;

public class bankingSystem {
    private static String SystemUserName = "Admin001";
    private static String SystemPassword = "zxcvbnm";
    private static HashMap<String,account> accDatabase;
    private static HashMap<String,Customer> customerDatabase;
    private static HashMap<String,Branch> branchDatabase;


    private static boolean branchNameIDMatch(String branchName) throws MismatchBranchNameIDException {
        // Check if the branch name and ID match
        if (branchDatabase.containsKey(branchName)) {
            return true;
        } else {
            throw new MismatchBranchNameIDException("Invalid Branch Name. Please try again.");
        }
    }

    private static String generateUniqueCustomerID() {
        Random rand = new Random();
        String id;

        do {
            int num = 10000 + rand.nextInt(90000); // ensures 5-digit number
            id = String.valueOf(num);
        } while (customerDatabase.containsKey(id)); // retry if ID already exists

        return id;
    }


    public static void loginAsAdmin() throws InvalidUserNamePasswordException{
        // Add code to take input from user for username and password
        // Validate the username and password
        // If valid, proceed to the banking system
        // Else, show error message and exit
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login as Admin");
        System.out.println("Please enter the admin username and password to login");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals(SystemUserName) && password.equals(SystemPassword)) {
            System.out.println("\033c");
            System.out.println("Login successful!");
            // Proceed to the banking system
            // Add code to navigate to the banking system menu
            System.out.println("Welcome to the Banking System "+"\'"+username+"\'");
            System.out.println("01. Account Details");
            System.out.println("02. Cash Deposit");
            System.out.println("04. Account Statements");
            System.out.println("05. Create a new Account");
            System.out.print("Please select an option :  ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:

                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
            
                default:
                    System.out.println("Invalid option. Please try again.");
            }


        } else if(!SystemUserName.equals(username)){
            throw new InvalidUserNamePasswordException("Invalid username. Returning...");
        } else {
            throw new InvalidUserNamePasswordException("Invalid password. Returning...");
        }
    }

    public static void loginAsCustomer() throws InvalidUserNamePasswordException{
        // Add code to handle customer login
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login as Customer");
        System.out.println("Please enter your username and password to login");
        System.out.print("Customer ID: ");
        int username = scanner.nextInt(); scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (customerDatabase.containsKey(String.valueOf(username))) {
            Customer person = customerDatabase.get(String.valueOf(username));
            if(password == person.getPassword()){
                System.out.println("\033c");
                System.out.println("Login successful!");
                // Proceed to the banking system
                // Add code to navigate to the banking system menu
                System.out.println("Welcome to the Banking System "+"\'"+username+"\'");
                System.out.println("01. Account Details");
                System.out.println("02. Cash Deposit");
                System.out.println("03. Cash Withdrawal");
                System.out.println("04. Account Statements");
                System.out.println("05. Create a new Account");
                System.out.print("Please select an option :  ");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        
                        break;
                    case 2:
                        
                        break;
                    case 3:
    
                        break;
                    case 4:
    
                        break;
                    case 5:
                        
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
    
    
    
    

            } else {
                throw new InvalidUserNamePasswordException("Invalid password. Returning...");
            }

        } else {
            throw new InvalidUserNamePasswordException("Invalid username. Returning...");
        }
    }

    public static void createAccount(boolean isNewUsr) throws MismatchBranchNameIDException{
        // Add code to handle account creation
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create an Account");
        System.out.println("Please enter your details to create an account");
        if(isNewUsr){
            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Customer NIC: ");
            String customerNIC = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Phone No: ");
            String phoneNo = scanner.nextLine();
            System.out.print("Date of Birth: ");
            String dateOfBirth = scanner.nextLine();
            System.out.println("Account Type: ");
            System.out.println("     01. Saving Account");
            System.out.println("     02. Current Account");
            System.out.println("     03. Fixed Deposit");
            System.out.print("Please select an option(1 or 2 or 3) :  ");
            int typeOpt = scanner.nextInt(); scanner.nextLine();
            AccountType acctype = (typeOpt == 1)? AccountType.saving: (typeOpt == 2)?  AccountType.current: AccountType.Fixed;
            // getting the password for new customer
            String accPassword = null;
            while(true){
                System.out.print("Please enter your password: ");
                String password = scanner.nextLine();
                System.out.print("Please re-enter your password: ");
                String rePassword = scanner.nextLine();
                if(password.equals(rePassword)){
                    accPassword = password; 
                    break;
                } else {
                    System.out.println("Passwords do not match. Please try again.");
                }
            }
            String branchName;
            int branchID;
            while (true) {
                System.out.print("Branch Name: ");
                branchName = scanner.nextLine();
                try {
                    branchNameIDMatch(branchName);
                    Branch branch = branchDatabase.get(branchName);
                    branchID = branch.getBranchID();
                    break;
                } catch (MismatchBranchNameIDException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                
            }
            System.out.println("Enter the amount to deposit: ");
            double amount = scanner.nextDouble(); scanner.nextLine();
            System.out.print("Email address: ");
            String email = scanner.nextLine();
            int customerID = Integer.valueOf(generateUniqueCustomerID()); // Generate a unique customer ID
            int acc_No = 10000 + accDatabase.size() + 1; // Generate a unique account number


        } else {
            System.out.print("Customer ID: ");
            int customerID = scanner.nextInt(); scanner.nextLine();
            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Customer NIC: ");
            String customerNIC = scanner.nextLine();
            System.out.println("Account Type : ");
            System.out.println("     01. Saving Account");
            System.out.println("     02. Current Account");
            System.out.println("     03. Fixed Deposit");
            System.out.print("Please select an option(1 or 2 or 3) :  ");
            int typeOpt = scanner.nextInt(); scanner.nextLine();
            AccountType acctype = (typeOpt == 1)? AccountType.saving: (typeOpt == 2)?  AccountType.current: AccountType.Fixed;
            int acc_No = 10000 + accDatabase.size() + 1; // Generate a unique account number
        }
        scanner.close();

        // Add code to create the account and save it to the database
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033c");
        try {
            while (true){
    
                System.out.println("Welcome to the Banking System");
                System.out.println("01. Login as Admin");
                System.out.println("02. Login as Customer");
                System.out.println("03. Create an account");
                System.out.println("04. Exit");
                System.out.print("Please select an option(1, 2, 3 or 4) :  ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (option) {
                    case 1:
                        try {
                            loginAsAdmin();
                        } catch (InvalidUserNamePasswordException e) {
                            System.out.println("\033c");
                            System.out.println(e.getMessage());
                            continue;
                        } 
                        break;
                    case 2:
                        try {
                            loginAsCustomer();
                        } catch (InvalidUserNamePasswordException e) {
                            System.out.println("\033c");
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Are you a new customer ?");
                            System.out.print("01) YES   or   02) NO  :");
                            int ans = scanner.nextInt(); scanner.nextLine();
                            boolean isNewUsr = (ans == 1)? true: false;
                            createAccount(isNewUsr);
                        } catch (Exception e) {
                            System.out.println("\033c");
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;
                    case 4:
                        scanner.close();
                        System.out.println("Exiting the system...");
                        Thread.sleep(2000);
                        System.out.println("\033c");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\033c");
                        System.out.println("Invalid option. Please try again.");
                }
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        

    }
}
