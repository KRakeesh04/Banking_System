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
    private ArrayList<account> accountsOfCustomer;

    public Customer(int customerID, String Password, String customerName, String customerNIC, String address, String phoneNo, String dateOfBirth, String email, ArrayList<account> accountsOfCustomer){
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

    public void addAccountToCustomer(account a){
        this.accountsOfCustomer.add(a);
    }


}
