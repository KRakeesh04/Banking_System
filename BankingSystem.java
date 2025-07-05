import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import exceptions.*;

public class BankingSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static String SystemUserName = "Admin001";
    private static String SystemPassword = "zxcvbnm";
    private static String custoFilePath = "./database/users.csv";
    private static String branchFilePath = "./database/branches.csv";
    private static String accFilePath = "./database/accounts.csv";
    private static String transFilePath = "./database/transactionsOfCustomers.csv";
    private static HashMap<String,Account> accDatabase = new HashMap<>();
    private static HashMap<String,Customer> customerDatabase = new HashMap<>();
    private static HashMap<String,Branch> branchDatabase = new HashMap<>();
    private static HashMap<String,ArrayList<Transaction>> transactionDatabase = new HashMap<>();
    private static BankDatabase bankDatabase;

    public BankingSystem() {
        // TODO: need to mark the last updated date for the interest amount and send the amount to the customer account at the end of the month
        // This constructor can be used to initialize any necessary components or configurations for the banking system.
        // Initialize the databases
        bankDatabase = new BankDatabase();
        bankDatabase.DBInitialize(customerDatabase, custoFilePath, branchDatabase, branchFilePath, accDatabase, accFilePath, transactionDatabase, transFilePath);
        System.out.println("Successfully initialized the banking system with the database.");
        
        for( String customerID : customerDatabase.keySet()) {
            Customer customer = customerDatabase.get(customerID);
            System.out.println("Customer ID: " + customerID + ", Name: " + customer.getCustomerName());
            System.out.println("Accounts of Customer: " + customer.getAccountsOfCustomer());
            System.out.println("Customer Password: " + customer.getPassword());
            System.out.println();
        }

    }

    private void removeLinesInTerminal(int lineCount) {
        for (int i = 0; i < lineCount; i++) {
            System.out.print("\033[1A");
            System.out.print("\033[2K");
            // System.out.println("\033[F\033[K");
        }
    }
    private boolean branchNameIDMatch(String branchID) throws MismatchBranchNameIDException {
        // Check if the branch name and ID match
        if (branchDatabase.containsKey(branchID)) {
            return true;
        } else {
            throw new MismatchBranchNameIDException("Invalid Branch ID. Please try again.");
        }
    }
    private void checkPINValidity(int PIN) throws InvalidPINException {
        // Check if the PIN is valid
        if (String.valueOf(PIN).length() != 4 || PIN < 0) {
            throw new InvalidPINException("Invalid PIN. Please enter a 4-digit PIN.");
        }
    }
    private void checkExistingCustomerDetails(int customerID, String customerName, String customerNIC) throws InvalidCustomerDetailException{
        if(!customerDatabase.containsKey(String.valueOf(customerID)) ){
            throw new InvalidCustomerDetailException("Customer ID mismatch. Please try again.");
        } else if( !customerName.equals(customerDatabase.get(String.valueOf(customerID)).getCustomerName()) ){
            throw new InvalidCustomerDetailException("Customer Name does not match with database details.");
        } else if( !customerDatabase.get(String.valueOf(customerID)).getCustomerNIC().equals(customerNIC) ){
            throw new InvalidCustomerDetailException("Customer NIC number does not match with database details.");
        }
    }
    private String generateUniqueCustomerID() {
        Random rand = new Random();
        String id;

        do {
            int num = 10000 + rand.nextInt(90000); // ensures 5-digit number
            id = String.valueOf(num);
        } while (customerDatabase.containsKey(id)); // retry if ID already exists

        return id;
    }
    private long RandomLongGeneratorForTransactionID() {
        Random rand = new Random();
        long transactionID;

    private void listAllBranchNames(){
        for (String branch : branchDatabase.keySet()) {
            System.out.print("|  " + branch + "  ");
        }
        System.out.print("|");
    }


    public void loginAsAdmin() throws InvalidUserNamePasswordException{
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

    public void loginAsCustomer() throws InvalidUserNamePasswordException{
        // Add code to handle customer login
        // TODO: catch the error of wrong input type
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

    public void createAccount(boolean isNewUsr) throws MismatchBranchNameIDException{
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
            System.out.println("To access transections for this bank account, you need to create a 4 digit PIN");
            int PIN;
            while(true){
                System.out.print("Please enter your 4 digit PIN: ");
                PIN = scanner.nextInt(); scanner.nextLine();
                try {
                    checkPINValidity(PIN);
                    break;
                } catch (InvalidPINException e) {
                    this.removeLinesInTerminal(2);
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            // getting the password for new customer
            String accPassword = null;
            while(true){
                System.out.print("Please enter your password for your customer account: ");
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

            System.out.println("Available Branches: ");
            this.listAllBranchNames();
            System.out.println();
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
                    this.removeLinesInTerminal(2);
                    System.out.println(e.getMessage());
                    continue;
                }
                
            }
            System.out.println("Enter the amount for initial deposit: ");
            double amount = scanner.nextDouble(); scanner.nextLine();
            System.out.print("Email address: ");
            String email = scanner.nextLine();
            int customerID = Integer.valueOf(generateUniqueCustomerID()); // Generate a unique customer ID
            int acc_No = 10000 + accDatabase.size() + 1; // Generate a unique account number

            // creating account and customer
            Customer newCustomer = new Customer(customerID, accPassword, customerName, customerNIC, address, phoneNo, dateOfBirth, email);
            Account newAccount = new Account(acc_No, branchID, branchName, acctype, customerName, customerID, amount, PIN);
            newCustomer.addAccountToCustomer(newAccount);
            accDatabase.put(String.valueOf(acc_No), newAccount);
            customerDatabase.put(String.valueOf(customerID), newCustomer);
            branchDatabase.get(branchName).addAccountToBranch(newAccount);
            System.out.println("Account created successfully!");
            System.out.println("Account Number: " + acc_No);
            System.out.println("Customer ID: " + customerID);


        } else {
            System.out.println();
            int customerID;
            String customerName;
            String customerNIC;
            while (true) {
                System.out.print("Customer ID: ");
                customerID = scanner.nextInt(); scanner.nextLine();
                System.out.print("Customer Name: ");
                customerName = scanner.nextLine();
                System.out.print("Customer NIC: ");
                customerNIC = scanner.nextLine();
                try {
                    this.checkExistingCustomerDetails(customerID, customerName, customerNIC);
                    break;
                } catch (InvalidCustomerDetailException e) {
                    removeLinesInTerminal(3);
                    System.out.println(e.getMessage());
                    continue;
                }
                
            }
            System.out.println("Account Type : ");
            System.out.println("     01. Saving Account");
            System.out.println("     02. Current Account");
            System.out.println("     03. Fixed Deposit");
            System.out.print("Please select an option(1 or 2 or 3) :  ");
            int typeOpt = scanner.nextInt(); scanner.nextLine();
            AccountType acctype = (typeOpt == 1)? AccountType.saving: (typeOpt == 2)?  AccountType.current: AccountType.Fixed;
            System.out.println("To access transections for this bank account, you need to create a 4 digit PIN");
            int PIN;
            while(true){
                System.out.print("Please enter your 4 digit PIN: ");
                PIN = scanner.nextInt(); scanner.nextLine();
                try {
                    checkPINValidity(PIN);
                    break;
                } catch (InvalidPINException e) {
                    this.removeLinesInTerminal(2);
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            // getting branch detail
            System.out.println("Available Branches: ");
            this.listAllBranchNames();
            System.out.println();
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
                    this.removeLinesInTerminal(2);
                    System.out.println(e.getMessage());
                    continue;
                }
                
            }
            // getting initial amount for the account
            System.out.println("Enter the amount to deposit: ");
            double amount = scanner.nextDouble(); scanner.nextLine();
            int acc_No = 10000 + accDatabase.size() + 1; // Generate a unique account number

            Account newAccount = new Account(acc_No, branchID, branchName, acctype, customerName, customerID, amount, PIN);
            accDatabase.put(String.valueOf(acc_No), newAccount);
            branchDatabase.get(branchName).addAccountToBranch(newAccount);
            customerDatabase.get(String.valueOf(branchID)).addAccountToCustomer(newAccount);
            System.out.println("Account created successfully!");
            System.out.println("Account Number: " + acc_No);
            System.out.println("Customer ID: " + customerID);
        }
        scanner.close();

    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {

            BankingSystem theBank = new BankingSystem();
            System.out.println("\033c");

            while (true){
                try {
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
                                theBank.loginAsAdmin();
                            } catch (InvalidUserNamePasswordException e) {
                                System.out.println("\033c");
                                System.out.println(e.getMessage());
                                continue;
                            } 
                            break;
                        case 2:
                            try {
                                theBank.loginAsCustomer();
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
                                theBank.createAccount(isNewUsr);
                            } catch (Exception e) {
                                System.out.println("\033c");
                                System.out.println(e.getMessage());
                                continue;
                            }
                            break;
                        case 4:
                            scanner.close();
                            System.out.println("Exiting the system...");
                            Thread.sleep(1500);
                            System.out.println("\033c");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("\033c");
                            System.out.println("Invalid option. Please try again.");
                            Thread.sleep(300);
                    }
                    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("\033c");
                    System.out.println("An error occurred :( ");
                    System.out.println("Please try again.");
                    scanner.nextLine(); // Clear the invalid input
                    Thread.sleep(300);
                    continue;
                }
                
    
            }
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("An error occurred while initializing the banking system.");
        } finally {
            scanner.close();
        }

    }
}
