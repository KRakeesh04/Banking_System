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
    private HashMap<String,account> accountsDatabase;
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
                ArrayList<account> accountsOfCustomer = new ArrayList<>();
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
                // ArrayList<account> branchAccounts;

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
    private HashMap<String,account> ReadFromAccountsDataBase(String filePath){
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
                account newAccount = new account(acc_No, branchID, branchName, acctype, customerName, customerID, balanceAmount, PIN, createdDate, currentStatus, initialAmount);
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
                int acc_No = Integer.parseInt(data[0]);

                String[] values = data[1].split(",");
                int transectionID = 0;
                String transectionType = "";
                String transectionDate = "";
                double transectionAmount = 0.00;
                String transectionDescription = "";
                for (int i = 0; i < values.length; i++) {
                    String[] transectionDetails = values[i].split("#");
                    transectionID = Integer.parseInt(transectionDetails[0].strip());
                    transectionType = transectionDetails[1].strip();
                    transectionDescription = transectionDetails[2].strip();
                    transectionAmount = Double.parseDouble(transectionDetails[3].strip());
                    transectionDate = transectionDetails[4].strip();

                    // Create and add a new transection object with the read data
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


    //method to update latest details to local database when log-out the system
    public void updateCustomersToLocalDatabase(HashMap<String,Customer> customersDetail, String filePath){

    }
    public void updateAccountsToLocalDatabase(HashMap<String,account> accountsDetail, String filePath){

    }

    public void addNewBrancheToLocalDatabase(Branch branchesDetail, String filePath){

    }


    
}


