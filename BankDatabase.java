import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankDatabase {
    private HashMap<String, Customer> customerDatabase = new HashMap<>();
    private HashMap<String, Branch> branchDatabase = new HashMap<>();
    private HashMap<String, Account> accountsDatabase = new HashMap<>();
    private HashMap<String, ArrayList<Transaction>> transactionDatabase = new HashMap<>();

    public BankDatabase() {
        this.customerDatabase = new HashMap<>();
        this.branchDatabase = new HashMap<>();
        this.accountsDatabase = new HashMap<>();
        this.transactionDatabase = new HashMap<>();
    }

    

    /***** method to parse CSV line *****/
    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char ch : line.toCharArray()) {
            if (ch == '"') {
                inQuotes = !inQuotes;
            } else if (ch == ',' && !inQuotes) {
                result.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }
        result.add(current.toString().trim());
        return result.toArray(new String[0]);
    }

    /***** method to read customer data from the database *****/
    private HashMap<String, Customer> ReadFromCustomerDataBase(String filePath) {
        if (this.customerDatabase == null) {
            this.customerDatabase = new HashMap<>();
        }

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);

                // Check for malformed lines
                if (data.length < 9) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    int customerID = Integer.parseInt(data[0]);
                    String customerName = data[1];
                    String customerNIC = data[2];
                    String address = data[3];
                    String phoneNo = data[4];
                    String dateOfBirth = data[5];
                    String email = data[6];
                    String Password = data[7];

                    // Parse and clean account numbers
                    String[] values = data[8].replace("\"", "").split(",");
                    ArrayList<String> accountsOfCustomer = new ArrayList<>();
                    for (String value : values) {
                        value = value.trim();
                        if (!value.isEmpty()) {
                            accountsOfCustomer.add(value);
                            // System.out.println("Account added: " + value); // Debugging
                        }
                    }

                    // Create Customer object
                    Customer newCustomer = new Customer(
                        customerID, Password, customerName, customerNIC, address, phoneNo,
                        dateOfBirth, email, accountsOfCustomer
                    );

                    // Store in map
                    this.customerDatabase.put(String.valueOf(customerID), newCustomer);

                    // Debugging output
                    // System.out.println("Reading customer: " + customerName + " with ID: " + customerID);
                    // System.out.println("Accounts of customer: ");
                    // for (String account_no : accountsOfCustomer) {
                    //     System.out.println(" - Account No: " + account_no);
                    // }

                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.customerDatabase;
    }

    /***** method to read branch data from the database *****/
    private HashMap<String, Branch> ReadFromBranchDataBase(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                // Check for malformed lines
                if (data.length < 8) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                try {
                    int branchID = Integer.parseInt(data[0]);
                    String branchName = data[1];
                    String contactNo = data[2];
                    String location = data[3];
                    String branchEmail = data[4];
                    String branchManegerName = data[5];
                    String branchManagerEmail = data[6];
                    String branchManagerPhoneNo = data[7];
                    // ArrayList<Account> branchAccounts;

                    Branch newBranch = new Branch(branchName, branchID, contactNo, location, branchEmail, branchManegerName,
                            branchManagerEmail, branchManagerPhoneNo);
                    this.branchDatabase.put(String.valueOf(branchID), newBranch);
                
                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.branchDatabase;
    }

    /***** method to read account data from the database *****/
    private HashMap<String, Account> ReadFromAccountsDataBase(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);

                // Check for malformed lines
                if (data.length < 12) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                
                try {
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
                    Account newAccount = new Account(acc_No, branchID, branchName, acctype, customerName, customerID,
                            balanceAmount, PIN, createdDate, currentStatus, initialAmount);
                    this.accountsDatabase.put(String.valueOf(acc_No), newAccount);
                    // System.out.println("Reading account: " + customerName + " with Account No: " + acc_No + "in accountDatabase"); // Debugging

                    // TODO: Add the account to the corresponding branch
                    // Add the account to the corresponding branch
                    // Branch branch = this.branchDatabase.get(String.valueOf(branchID));
                    // if (branch != null) {
                    //     branch.addAccountToBranch(newAccount);
                    // }
                    // this.branchDatabase.put(String.valueOf(branchID), branch);

                } catch (Exception e) {
                    System.out.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.accountsDatabase;
    }

    /***** method to read transaction data from the database *****/
    private HashMap<String, ArrayList<Transaction>> ReadFromTransactionDataBase(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);
                if (data[0].strip().isEmpty()) {
                    // Skip this line if acc_No is empty
                    // System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                int acc_No = Integer.parseInt(data[0].strip()); // Parse acc_No

                String[] values = data[1].split(",");
                long transactionID = 0;
                String transactionType = "";
                String transactionDate = "";
                double transactionAmount = 0.00;
                String transactionDescription = "";
                ArrayList<Transaction> transactions = new ArrayList<>();
                // Loop through the transaction details
                for (int i = 0; i < values.length; i++) {
                    String[] transactionDetails = values[i].split("#");
                    transactionID = Long.parseLong(transactionDetails[0].strip().replace("\"", ""));
                    transactionType = transactionDetails[1].strip().equals("d") ? "Deposit" : "Withdraw";
                    transactionDescription = transactionDetails[2].strip();
                    transactionAmount = Double.parseDouble(transactionDetails[3].strip());
                    transactionDate = transactionDetails[4].strip();

                    // Create and add a new Transaction object with the read data
                    Transaction newTransaction = new Transaction(transactionID, transactionType, transactionDate,
                            transactionAmount, transactionDescription, acc_No);
                    transactions.add(newTransaction);
                }
                this.transactionDatabase.put(String.valueOf(acc_No), transactions);

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return this.transactionDatabase;
    }


    
    // method to escape CSV special characters //
    private String escapeCSV(String field) {
        if (field == null) return "";
        String escaped = field.replace("\"", "\"\"");

        if (escaped.contains(",") || escaped.contains("\"") || escaped.contains("\n") || escaped.contains("\r")) {
            return "\"" + escaped + "\"";
        }
        return escaped;
    }

    /***** method to update customer data to the local database *****/
    private void updateCustomersToLocalDatabase(HashMap<String, Customer> customersDetail, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write the CSV header
            bw.write("customerID,customerName,customerNIC,address,phoneNo,dateOfBirth,email,Password,accountNumbersOfCustomer");
            bw.newLine();
    
            for (Customer customer : customersDetail.values()) {
                String accountsJoined = String.join(",", customer.getAccountsOfCustomer());
                // Quote the accounts field to avoid CSV parsing issues
                String line = String.format(
                    "%s,%s,%s,%s,%s,%s,%s,%s,\"%s\"",
                    String.valueOf(customer.getCustomerID()),
                    customer.getCustomerName(),
                    customer.getCustomerNIC(),
                    escapeCSV(customer.getAddress()),
                    customer.getPhoneNo(),
                    customer.getDOB(),
                    escapeCSV(customer.getEmail()),
                    escapeCSV(customer.getPassword()),
                    accountsJoined
                );
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();  // for better debugging
        }
    }
    
    /***** method to update account data to the local database *****/
    private void updateAccountsToLocalDatabase(HashMap<String, Account> accountsDetail, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("acc_No,acctype,initialAmount,interestRate,customerName,customerID,branchName,branchID,PIN,currentStatus,createdDate,currentBalance");
            bw.newLine();
    
            for (Account acc : accountsDetail.values()) {
                String customerName = escapeCSV(acc.getCustomerName());
                String branchName = escapeCSV(acc.getBranchName());
                String accStatus = escapeCSV(String.valueOf(acc.getAccStatus()));
                String createdDate = escapeCSV(acc.getCreatedDate());
    
                String line = String.format(
                    "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                    acc.getAccNo(),
                    acc.getAccType(),
                    acc.getInitialAmount(),
                    acc.getInterestRate(),
                    customerName,
                    acc.getCustomerID(),
                    branchName,
                    acc.getBranchID(),
                    acc.getPIN(),
                    accStatus,
                    createdDate,
                    acc.getBalanceForDB()
                );
    
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /***** method to update transaction data to the local database *****/
    private void updateTransactionToLocalDatabase(HashMap<String, Account> accountsDatabase, HashMap<String, ArrayList<Transaction>> transactionDatabase, String filePath) {

        HashMap<String, String> transDB = new HashMap<>();

        for (String acc_no : accountsDatabase.keySet()) {
            ArrayList<Transaction> transactions = transactionDatabase.get(acc_no);
            if (transactions != null && !transactions.isEmpty()) {
                StringBuilder transactionDetailBuilder = new StringBuilder();

                for (Transaction transaction : transactions) {
                    String typeCode = "w";
                    if ("Deposit".equals(transaction.getTransactionType())) {
                        typeCode = "d";
                    }
                    // Build transaction string with single delimiter between fields
                    transactionDetailBuilder.append(transaction.getTransactionID())
                            .append(" # ")
                            .append(typeCode)
                            .append(" # ")
                            .append(transaction.getTransactionDescription())
                            .append(" # ")
                            .append(transaction.getTransactionAmount())
                            .append(" # ")
                            .append(transaction.getTransactionDate())
                            .append(",");
                }

                // Remove trailing comma safely
                if (transactionDetailBuilder.length() > 0) {
                    transactionDetailBuilder.setLength(transactionDetailBuilder.length() - 1);
                }

                transDB.put(acc_no, transactionDetailBuilder.toString());
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("acc_No,transactions");
            bw.newLine();

            for (String acc_no : transDB.keySet()) {
                // Quote the transactions field to be safe (if it contains commas, #, or newlines)
                String transactions = transDB.get(acc_no);
                String escapedTransactions = escapeCSV(transactions);

                bw.write(acc_no + "," + escapedTransactions);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /***** method to add new branch data to the local database *****/
    public void addNewBranchToLocalDatabase(Branch branchesDetail, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s",
                escapeCSV(String.valueOf(branchesDetail.getBranchID())),
                escapeCSV(branchesDetail.getBranchName()),
                escapeCSV(branchesDetail.getContactNo()),
                escapeCSV(branchesDetail.getLocation()),
                escapeCSV(branchesDetail.getBranchEmail()),
                escapeCSV(branchesDetail.getBranchManagerName()),
                escapeCSV(branchesDetail.getBranchManagerEmail()),
                escapeCSV(branchesDetail.getBranchManagerPhoneNo())
            );
    
            bw.write(line);
            bw.newLine();
    
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }



    /***** method to update all data to the local database when logOut *****/
    // This method is used to update all data to the local database
    // Should be called once logOut from the system (Exiting the system)
    public void localDBUpdate(HashMap<String, Customer> customerDatabase, String custoFilePath,
            HashMap<String, Account> accountsDatabase, String accFilePath,
            HashMap<String, ArrayList<Transaction>> transactionDatabase, String transFilePath) {
        this.updateCustomersToLocalDatabase(customerDatabase, custoFilePath);
        this.updateAccountsToLocalDatabase(accountsDatabase, accFilePath);
        this.updateTransactionToLocalDatabase(accountsDatabase, transactionDatabase, transFilePath);
    }

    /***** method to read all data from the database *****/
    // This method is used to initialize the database with data from files
    // Should be called once logIn to the system
    public void DBInitialize(HashMap<String, Customer> customerDatabase, String custoFilePath,
                         HashMap<String, Branch> branchDatabase, String branchFilePath,
                         HashMap<String, Account> accountsDatabase, String accFilePath,
                         HashMap<String, ArrayList<Transaction>> transactionDatabase, String transFilePath) {
        branchDatabase.putAll(this.ReadFromBranchDataBase(branchFilePath)); System.out.println("Reading branches from file: " + branchFilePath); // Debugging
        accountsDatabase.putAll(this.ReadFromAccountsDataBase(accFilePath));    System.out.println("Reading accounts from file: " + accFilePath); // Debugging
        transactionDatabase.putAll(this.ReadFromTransactionDataBase(transFilePath)); System.out.println("Reading transactions from file: " + transFilePath); // Debugging
        customerDatabase.putAll(this.ReadFromCustomerDataBase(custoFilePath)); System.out.println("Reading customers from file: " + custoFilePath); // Debugging
        System.out.println("Database initialized successfully!"); // Debugging  
    }



    // public static void main(String[] args) {
    //     BankDatabase db = new BankDatabase();
    //     String custoFilePath = "./database/users.csv";
    //     String branchFilePath = "./database/branches.csv";
    //     String accFilePath = "./database/accounts.csv";
    //     String transFilePath = "./database/transactionsOfCustomers.csv";

    //     db.DBInitialize(db.customerDatabase, custoFilePath, db.branchDatabase,
    //     branchFilePath, db.accountsDatabase, accFilePath, db.transactionDatabase,
    //     transFilePath);

    //     // Print the customer database
    //     for (Customer customer : db.customerDatabase.values()) {
    //     System.out.println(customer.getCustomerName() + " " +
    //     customer.getCustomerID() + " " + customer.getPassword());
    //     System.out.println("Accounts of customer: " + customer.getAccountsOfCustomer());
    //     }
    //     System.out.println("---------------------------------");
    //     // Print the branch database
    //     for (Branch branch : db.branchDatabase.values()) {
    //     System.out.println(branch.getBranchName() + " " + branch.getBranchID() + " "
    //     + branch.getBranchManagerName());
    //     }
    //     System.out.println("---------------------------------");
    //     // Print the accounts database
    //     for (Account acc : db.accountsDatabase.values()) {
    //     System.out.println(acc.getAccNo() + " " + acc.getAccType() + " " +
    //     acc.getCustomerName());
    //     }
    //     System.out.println("---------------------------------");
    //     // Print the transaction database
    //     for (ArrayList<Transaction> transactions : db.transactionDatabase.values()) {
    //         for (Transaction transaction : transactions) {
    //             System.out.println(transaction.getAcc_No() + " " +
    //             transaction.getTransactionType() + " " + transaction.getTransactionDate());
    //         }
    //     }

    // }

}
