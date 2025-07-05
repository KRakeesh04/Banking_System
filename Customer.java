import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String Password;
    private String customerName;
    private String customerNIC;
    private String address;
    private String phoneNo;
    private String dateOfBirth; // format  yyyy-mm-dd
    private String email = "none";
    private ArrayList<String> accountsOfCustomer = new ArrayList<>();

    // Constructor for creating a new customer with all details
    public Customer(int customerID, String Password, String customerName, String customerNIC, String address, String phoneNo, String dateOfBirth, String email, ArrayList<String> accountsOfCustomer){
        this.customerID = customerID;
        this.Password = Password;
        this.customerName = customerName;
        this.customerNIC = customerNIC;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.accountsOfCustomer = accountsOfCustomer;
    }
    // Constructor for creating a new customer with email and accounts
    public Customer(int customerID, String Password, String customerName, String customerNIC, String address, String phoneNo, String dateOfBirth, String email){
        this.customerID = customerID;
        this.Password = Password;
        this.customerName = customerName;
        this.customerNIC = customerNIC;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
    // Constructor for creating a new customer without email and accounts
    public Customer(int customerID, String Password, String customerName, String customerNIC, String address, String phoneNo, String dateOfBirth){
        this.customerID = customerID;
        this.Password = Password;
        this.customerName = customerName;
        this.customerNIC = customerNIC;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getCustomerName(){
        return this.customerName;
    }
    
    public int getCustomerID(){
        return this.customerID;
    }

    public String getCustomerNIC(){
        return this.customerNIC;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPhoneNo(){
        return this.phoneNo;
    }

    public String getDOB(){
        return this.dateOfBirth;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.Password;
    }

    public void addAccountToCustomer(String a){
        this.accountsOfCustomer.add(a);
    }

    public ArrayList<String> getAccountsOfCustomer(){
        return this.accountsOfCustomer;
    }

}
