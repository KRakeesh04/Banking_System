import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class bankDatabase {
    private HashMap<String,Customer> customerDatabase;
    private HashMap<String,Branch> branchDatabase;
    private HashMap<String,Account> accountsDatabase;
    private HashMap<String,Transection> transectionDatabase;


    public bankDatabase(){
        this.customerDatabase = new HashMap<>();
        this.branchDatabase = new HashMap<>();
        this.accountsDatabase = new HashMap<>();
        this.transectionDatabase = new HashMap<>();
    }


    /***** method to read customer data from the database *****/
    private HashMap<String,Customer> ReadFromCustomerDataBase(String filePath){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int customerID = Integer.parseInt(data[0]);
                String customerName = data[1];
                String customerNIC = data[2];
                String address = data[3];
                String phoneNo = data[4];
                String dateOfBirth = data[5];
                String email = data[6];
                String Password = data[7];
                
                String[] values = data[8].split(",");
                ArrayList<Account> accountsOfCustomer = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    accountsOfCustomer.add(this.accountsDatabase.get(values[i]));
                }

                // Create a new Customer object with the read data
                Customer newCustomer = new Customer(customerID, Password, customerName, customerNIC, address, phoneNo, dateOfBirth, email, accountsOfCustomer);
                this.customerDatabase.put(String.valueOf(customerID), newCustomer);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            
        }

        return this.customerDatabase;
    }


    /***** method to read branch data from the database *****/
    private HashMap<String,Branch> ReadFromBranchDataBase(String filePath){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int branchID = Integer.parseInt(data[0]);
                String branchName = data[1];
                String contactNo = data[2];
                String location = data[3];
                String branchEmail = data[4];
                String branchManegerName = data[5];
                String branchManagerEmail = data[6];
                String branchManagerPhoneNo = data[7];
                // ArrayList<Account> branchAccounts;

                Branch newBranch = new Branch(branchName, branchID, contactNo, location, branchEmail, branchManegerName, branchManagerEmail, branchManagerPhoneNo);
                this.branchDatabase.put(String.valueOf(branchID), newBranch);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.branchDatabase;
    }

    
    /***** method to read account data from the database *****/
    private HashMap<String,Account> ReadFromAccountsDataBase(String filePath){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int acc_No = Integer.parseInt(data[0]);
                AccountType acctype = AccountType.valueOf(data[1]);
                double initialAmount = Double.parseDouble(data[2]);
                // double interestRate = Double.parseDouble(data[3]);
                String customerName = data[4];
                int customerID = Integer.parseInt(data[5]);
                String branchName = data[6];
                int branchID = Integer.parseInt(data[7]);
                int PIN = Integer.parseInt(data[8]);
                Status currentStatus = Status.valueOf(data[9]);
                String createdDate = data[10];
                double balanceAmount = Double.parseDouble(data[11]);
                
                // Create and add a new account object with the read data
                Account newAccount = new Account(acc_No, branchID, branchName, acctype, customerName, customerID, balanceAmount, PIN, createdDate, currentStatus, initialAmount);
                this.accountsDatabase.put(String.valueOf(acc_No), newAccount);

                // Add the account to the corresponding branch
                Branch branch = this.branchDatabase.get(String.valueOf(branchID));
                if (branch != null) {
                    branch.addAccountToBranch(newAccount);
                }
                this.branchDatabase.put(String.valueOf(branchID), branch);


            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.accountsDatabase;
    }
    

    /***** method to read transection data from the database *****/
    private HashMap<String,Transection> ReadFromTransectionDataBase(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].strip().isEmpty()) {
                    // Skip this line if acc_No is empty
                    // System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                int acc_No = Integer.parseInt(data[0].strip()); // Parse acc_No

                String[] values = data[1].split(",");
                long transectionID = 0;
                String transectionType = "";
                String transectionDate = "";
                double transectionAmount = 0.00;
                String transectionDescription = "";
                for (int i = 0; i < values.length; i++) {
                    String[] transectionDetails = values[i].split("#");
                    transectionID = Long.parseLong(transectionDetails[0].strip().replace("\"", ""));
                    transectionType = transectionDetails[1].strip().equals("d") ? "Deposit" : "Withdraw";
                    transectionDescription = transectionDetails[2].strip();
                    transectionAmount = Double.parseDouble(transectionDetails[3].strip());
                    transectionDate = transectionDetails[4].strip();

                    // Create and add a new Transection object with the read data
                    Transection newTransection = new Transection(transectionID, transectionType, transectionDate, transectionAmount, transectionDescription, acc_No);
                    this.transectionDatabase.put(String.valueOf(transectionID), newTransection);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.transectionDatabase;
    }
    
    
    /***** method to update customer data to the local database *****/
    private void updateCustomersToLocalDatabase(HashMap<String,Customer> customersDetail, String filePath){
        // Write the updated customer data to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("customerID,customerName,customerNIC,address,phoneNo,dateOfBirth,email,Password,accountNumbersOfCustomer\n");
            for (Customer customer : customersDetail.values()) {
                bw.write(customer.getCustomerID() + "," + customer.getCustomerName() + "," + customer.getCustomerNIC() + "," + customer.getAddress() + "," + customer.getPhoneNo() + "," + customer.getDOB() + "," + customer.getEmail() + ",");
                ArrayList<Account> accounts = customer.getAccountsOfCustomer();
                for (int i = 0; i < accounts.size(); i++) {
                    bw.write(accounts.get(i).getAccNo());
                    if (i < accounts.size() - 1) {
                        bw.write(",");
                    }
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /***** method to update account data to the local database *****/
    private void updateAccountsToLocalDatabase(HashMap<String,Account> accountsDetail, String filePath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("acc_No,acctype,initialAmount,interestRate,customerName,customerID,branchName,branchID,PIN,currentStatus,createdDate,currentBalance\n");
            for (Account acc : accountsDetail.values()) {
                bw.write(acc.getAccNo() + "," + acc.getAccType() + "," + acc.getInitialAmount() + "," + acc.getInterestRate() + "," + acc.getCustomerName() + "," + acc.getCustomerID() + "," + acc.getBranchName() + "," + acc.getBranchID() + "," + acc.getPIN() + "," + acc.getAccStatus() + "," + acc.getCreatedDate() + "," + acc.getBalanceForDB() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /***** method to update transection data to the local database *****/
    private void updateTransectionToLocalDatabase(HashMap<String,Transection> transectionDetail, String filePath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("acc_No,transections\n");
            for (Transection trenc : transectionDetail.values()) {
                //TODO: find a method to store the transection details back to its original form
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /***** method to add new branch data to the local database *****/
    public void addNewBrancheToLocalDatabase(Branch branchesDetail, String filePath){
        // Write the updated branch data to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(branchesDetail.getBranchID() + "," + branchesDetail.getBranchName() + "," + branchesDetail.getContactNo() + "," + branchesDetail.getLocation() + "," + branchesDetail.getBranchEmail() + "," + branchesDetail.getBranchManagerName() + "," + branchesDetail.getBranchManagerEmail() + "," + branchesDetail.getBranchManagerPhoneNo() + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    /***** method to update all data to the local database when logOut *****/
    // This method is used to update all data to the local database
    // Should be called once logOut from the system (Exiting the system)
    public void localDBUpdate(HashMap<String,Customer> customerDatabase, String custoFilePath, HashMap<String,Account> accountsDatabase, String accFilePath, HashMap<String,Transection> transectionDatabase, String transFilePath){
        this.updateCustomersToLocalDatabase(customerDatabase, custoFilePath);
        this.updateAccountsToLocalDatabase(accountsDatabase, accFilePath);
        this.updateTransectionToLocalDatabase(transectionDatabase, transFilePath);
    }
    
    /***** method to read all data from the database *****/
    // This method is used to initialize the database with data from files
    // Should be called once logIn to the system
    public void DBInitialize(HashMap<String,Customer> customerDatabase, String custoFilePath, HashMap<String,Branch> branchDatabase, String branchFilePath, HashMap<String,Account> accountsDatabase, String accFilePath, HashMap<String,Transection> transectionDatabase, String transFilePath){
        customerDatabase = this.ReadFromCustomerDataBase(custoFilePath);
        branchDatabase = this.ReadFromBranchDataBase(branchFilePath);
        accountsDatabase = this.ReadFromAccountsDataBase(accFilePath);
        transectionDatabase = this.ReadFromTransectionDataBase(transFilePath);
    }


//     public static void main(String[] args) {
//         bankDatabase db = new bankDatabase();
//         String custoFilePath = "./database/users.csv";
//         String branchFilePath = "./database/branches.csv";
//         String accFilePath = "./database/accounts.csv";
//         String transFilePath = "./database/transectionsOfCustomers.csv";

//         db.DBInitialize(db.customerDatabase, custoFilePath, db.branchDatabase, branchFilePath, db.accountsDatabase, accFilePath, db.transectionDatabase, transFilePath);
        
//         // Print the customer database
//         for (Customer customer : db.customerDatabase.values()) {
//             System.out.println(customer.getCustomerName() + " " + customer.getCustomerID() + " " + customer.getPassword());
//         }
//         System.out.println("---------------------------------");
//         // Print the branch database
//         for (Branch branch : db.branchDatabase.values()) {
//             System.out.println(branch.getBranchName() + " " + branch.getBranchID() + " " + branch.getBranchManagerName());
//         }
//         System.out.println("---------------------------------");
//         // Print the accounts database
//         for (Account acc : db.accountsDatabase.values()) {
//             System.out.println(acc.getAccNo() + " " + acc.getAccType() + " " + acc.getCustomerName());
//         }
//         System.out.println("---------------------------------");
//         // Print the transection database
//         for (Transection transection : db.transectionDatabase.values()) {
//             System.out.println(transection.getAcc_No() + " " + transection.getTransectionType() + " " + transection.getTransectionDate());
//         }
        
//     }

}


