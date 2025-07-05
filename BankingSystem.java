import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfWriter;
// import com.itextpdf.layout.Document;
// import com.itextpdf.layout.element.Paragraph;
// import com.itextpdf.layout.element.Table;
// import com.itextpdf.layout.element.Cell;
// import com.itextpdf.layout.properties.UnitValue;

// import javax.mail.* ;
// import javax.mail.internet.*;
// import java.util.Properties;
// import java.io.File;
// import java.io.IOException;
// import javax.activation.DataHandler;
// import javax.activation.DataSource;
// import javax.activation.FileDataSource;

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

        do {
            transactionID = 1000000000000000L + (long)(rand.nextDouble() * 9000000000000000L); // ensures 16-digit number
        } while (transactionDatabase.containsKey(String.valueOf(transactionID))); // retry if ID already exists

        return transactionID;
    }

    
    private void ListAllBranchNames(){
        // TODO: need minor changes to display the branch names in a better way like orderly
        int count = 0;
        for (String branch : branchDatabase.keySet()) {
            System.out.print("|  " + branch + " - " + branchDatabase.get(branch).getBranchName() + "  ");
            if(branchDatabase.get(branch).getBranchName().length() < 20) {
                int spacesToAdd = 20 - branchDatabase.get(branch).getBranchName().length();
                System.out.println(" ".repeat(spacesToAdd));
            }
            if(count % 4 == 3) {
                System.out.print("|");
                System.out.println();
            }
        }
        System.out.print("|");
    }


    private void LoginAsAdmin() throws InvalidUserNamePasswordException{
        // TODO: Add code to handle admin login
        // Scanner scanner = new Scanner(System.in);
        System.out.println("\033c");
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
            while(true){
                try {
                    System.out.println("Hello "+"\'"+username+"\' \uD83D\uDE0E");
                    System.out.println("01. Account Details");
                    System.out.println("02. Cash Deposit");
                    System.out.println("03. Account Statements");
                    System.out.println("04. Close an account");
                    System.out.println("05. Create a new Account");
                    System.out.println("06. Change Customer Details");
                    System.out.println("07. Logout");
                    System.out.print("Please select an option :  ");
                    int option = Integer.parseInt(scanner.nextLine()) ;
                    System.out.println("\033c");

                    switch (option) {
                        case 1:
                            System.out.println("Account Details\n");
                            System.out.print("Enter userID : ");
                            String userID = scanner.nextLine() ;
                            Customer person = customerDatabase.get(String.valueOf(userID));
                            System.out.println();
                            System.out.println("Customer Name : " + person.getCustomerName());
                            System.out.println("Customer Address : " + person.getAddress());
                            System.out.println("Customer phone no. : " + person.getPhoneNo());
                            System.out.println();
                            ArrayList<String> accs = person.getAccountsOfCustomer() ; 
                            // TODO: using the acc count to show options to get the more details and activate or deactivate or close the accounts
                            int accCounts = this.DisplayAccountDetails(accs) ; 

                            break;

                        case 2:
                            System.out.println("Cash Deposit \n");
                            System.out.print("Enter Account number : ");
                            int accNo = Integer.parseInt(scanner.nextLine()) ;
                            double amount = Double.parseDouble(scanner.nextLine()) ;
                            DepositCash(accNo,amount) ;
                            System.out.println("\033c");
                            System.out.println("Cash deposited successfully to Account No: " + accNo);
                            break;

                        case 3:
                            // function to show last 10 or less transaction details and option to send the statement through the email
                            System.out.println("Account Statement \n");
                            System.out.print("Enter Account number(1****) : ");
                            int accountNo = Integer.parseInt(scanner.nextLine()) ;
                            Show10Transactions(accountNo);
                            break;

                        case 4:
                            System.out.println("Close an Account \n");
                            System.out.print("Enter Account number : ");
                            int acc = Integer.parseInt(scanner.nextLine()) ;
                            CloseAccount(acc);
                            System.out.println("\033c");
                            break;

                        case 5:
                            System.out.println("Create a new Account \n");
                            System.out.println("Is this a new customer ?");
                            System.out.print("01) YES   or   02) NO  :");
                            int ans = Integer.parseInt(scanner.nextLine());
                            boolean isNewUsr = (ans == 1)? true: false;
                            this.CreateAccount(isNewUsr, null);
                            System.out.println("\033c");
                            break;

                        case 6:
                            System.out.println("Change Customer Details \n");
                            System.out.print("Enter Customer ID : ");
                            int customerID = Integer.parseInt(scanner.nextLine());
                            if (customerDatabase.containsKey(String.valueOf(customerID))) {
                                Customer cust = customerDatabase.get(String.valueOf(customerID));
                                this.changeCustomerDetails(cust);
                                break;

                            } else {
                                System.out.println("\033c");
                                System.out.println("Customer ID not found.");
                                break;
                            }
                                
                        case 7:
                            try {
                                // scanner.close();
                                System.out.println("Logging out...");
                                Thread.sleep(1500); // Simulate a delay for logout
                                return;
                            } catch (InterruptedException e) {
                                System.out.println("Logout interrupted.");
                            }
                            break;
                    
                        default:
                            System.out.println("\033c");
                            System.out.println("Invalid option. Please try again.");
                            Thread.sleep(300);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("\033c");
                    System.out.println("An error occurred: " + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Please try again.");
                    try {
                        Thread.sleep(1500); // Simulate a delay for error message
                    } catch (InterruptedException ie) {
                        System.out.println("Error message interrupted.");
                    }
                }
            }


        } else if(!SystemUserName.equals(username)){
            // scanner.close();
            throw new InvalidUserNamePasswordException("Invalid username. Returning...");
        } else {
            // scanner.close();
            throw new InvalidUserNamePasswordException("Invalid password. Returning...");
        }
        // scanner.close();
    }

    private void changeCustomerDetails(Customer cust) {
        System.out.println("Change Customer Details");
        System.out.println("Current Customer Name: " + cust.getCustomerName());
        System.out.print("Enter new Customer Name (or press Enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            cust.setCustomerName(newName);
        }

        System.out.println("Current Customer NIC: " + cust.getCustomerNIC());
        System.out.print("Enter new Customer NIC (or press Enter to keep current): ");
        String newNIC = scanner.nextLine();
        if (!newNIC.isEmpty()) {
            cust.setCustomerNIC(newNIC);
        }

        System.out.println("Current Address: " + cust.getAddress());
        System.out.print("Enter new Address (or press Enter to keep current): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            cust.setAddress(newAddress);
        }

        System.out.println("Current Phone No.: " + cust.getPhoneNo());
        System.out.print("Enter new Phone No. (or press Enter to keep current): ");
        String newPhoneNo = scanner.nextLine();
        if (!newPhoneNo.isEmpty()) {
            cust.setPhoneNo(newPhoneNo);
        }

        System.out.println("Current Date of Birth: " + cust.getDOB());
        System.out.print("Enter new Date of Birth (or press Enter to keep current): ");
        String newDOB = scanner.nextLine();
        if (!newDOB.isEmpty()) {
            cust.setDOB(newDOB);
        }
        System.out.println("\033c");
        System.out.println("User ID  \'" + cust.getCustomerID() + "\'  Details updated successfully!");
    }

    private void LoginAsCustomer() throws InvalidUserNamePasswordException{
        // TODO: Add code to handle customer login
        // Scanner scanner = new Scanner(System.in);
        System.out.println("\033c");
        System.out.println("Login as Customer");
        System.out.println("Please enter your username and password to login");
        System.out.print("Customer ID: ");
        int username = Integer.parseInt(scanner.nextLine());
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (customerDatabase.containsKey(String.valueOf(username))) {
            Customer person = customerDatabase.get(String.valueOf(username));
            if(password.equals(person.getPassword())) {
                System.out.println("\033c");
                System.out.println("Login successful!");
                // Proceed to the banking system
                // Add code to navigate to the banking system menu
                while (true) {
                    try {
                        System.out.println("\n");
                        System.out.println("Hello "+"\'"+person.getCustomerName()+"\' \uD83D\uDE0E");
                        System.out.println("01. Account Details");
                        System.out.println("02. Cash Deposit");
                        System.out.println("03. Cash Withdrawal");
                        System.out.println("04. Fund Transfer");
                        System.out.println("05. Account Statements");
                        System.out.println("06. Create a new Account");
                        System.out.println("07. Logout");
                        System.out.print("Please select an option :  ");
                        int option = Integer.parseInt(scanner.nextLine()) ;
                        ArrayList<String> accs = person.getAccountsOfCustomer() ;
                        System.out.println("\033c");
                        // if (accs.isEmpty()) {
                        //     System.out.println("No accounts found for this customer. Please create an account first.");
                        //     continue;
                        // }

                        switch (option) {
                            case 1:
                                System.out.println("Account Details\n");
                                System.out.println("Customer Name : " + person.getCustomerName());
                                this.DisplayAccountDetails(accs) ;
                                break;
                        
                            case 2:
                                System.out.println("Cash Deposit \n");
                                System.out.println("Customer Name : " + person.getCustomerName());
                                this.DisplayAccountDetails(accs) ;
                                System.out.println();
                                System.out.print("Please enter the account number to deposit cash into : ");
                                int accNo = Integer.parseInt(scanner.nextLine()) ;
                                System.out.print("Please enter the amount to Deposit : ");
                                double amount = Double.parseDouble(scanner.nextLine()) ;
                                DepositCash(accNo,amount) ;
                                System.out.println("\033c");
                                System.out.println("Cash deposited successfully to Account No: " + accNo);
                                break;
                        
                            case 3:
                                System.out.println("Cash Withdrawal \n");
                                System.out.println("Customer Name : " + person.getCustomerName());
                                this.DisplayAccountDetails(accs) ;
                                System.out.println();
                                System.out.print("Please enter the account number to withdraw cash from : ");
                                int accno = Integer.parseInt(scanner.nextLine()) ;
                                System.out.print("Please enter the amount to withdraw : ");
                                double amount_val = Double.parseDouble(scanner.nextLine()) ;
                                System.out.print("Please enter your 4 Digit PIN : ");
                                String PIN = scanner.nextLine() ;
                                WithdrawCash(accno,amount_val,Integer.valueOf(PIN)) ;
                                System.out.println("\033c");
                                System.out.println("Cash withdrawn successfully from Account No: " + accno);
                                break;
                        
                            case 4:
                                System.out.println("Fund Transfer \n");
                                System.out.println("Customer Name : " + person.getCustomerName());
                                this.DisplayAccountDetails(accs) ;
                                System.out.println();
                                System.out.print("Enter the account number to transfer funds from : ");
                                int fromAcc = Integer.parseInt(scanner.nextLine()) ;
                                System.out.print("Enter the account number to transfer funds to : ");
                                int toAcc = Integer.parseInt(scanner.nextLine()) ;
                                System.out.print("Please enter the amount to transfer : ");
                                double transferAmount = Double.parseDouble(scanner.nextLine()) ;
                                System.out.print("Please enter the description for the transfer : ");
                                String discription = scanner.nextLine() ;
                                System.out.print("Please enter your 4 Digit PIN : ");
                                int transferPIN = Integer.parseInt(scanner.nextLine()) ;
                                TransferFunds(fromAcc,toAcc,transferAmount,transferPIN, discription) ;
                                break;
                        
                            case 5:
                                System.out.println("Account Statements \n");
                                System.out.println("Customer Name : " + person.getCustomerName());
                                this.DisplayAccountDetails(accs) ;
                                System.out.print("Please enter the account number to show the last transactions : ");
                                int acc = Integer.parseInt(scanner.nextLine()) ;
                                Show10Transactions(acc);
                                // function to show last 10 transaction details and option to send the statement through the email
                                break;
                        
                            case 6:
                                this.CreateAccount(false, person);
                                break;
                        
                            case 7:
                                try {
                                    System.out.println("Logging out...");
                                    Thread.sleep(1500); // Simulate a delay for logout
                                    return;
                                } catch (InterruptedException e) {
                                    System.out.println("Logout interrupted.");
                                }
                                break;
                            
                            default:
                                System.out.println("\033c");
                                System.out.println("Invalid option. Please try again.");
                                Thread.sleep(300);
                        }

                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println("An error occurred: " + e.getMessage());
                        e.printStackTrace();
                        System.out.println("Please try again.");
                        try {
                            Thread.sleep(1500); // Simulate a delay for error message
                        } catch (InterruptedException ie) {
                            System.out.println("Error message interrupted.");
                        }
                    }

                }
    

            } else {
                // scanner.close();
                throw new InvalidUserNamePasswordException("Invalid password. Returning...");
            }
            // scanner.close();
        } else {
            // scanner.close();
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
