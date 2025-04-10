public class customer {
    private int customerID;
    private String customerName;
    private String customerNIC;
    private String address;
    private String phoneNo;
    private String dateOfBirth; // format yyyy-mm-dd
    private String email = "none";

    public customer(int customerID, String customerName, String customerNIC, String address, String phoneNo, String dateOfBirth, String email){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerNIC = customerNIC;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
    public customer(int customerID, String customerName, String customerNIC, String address, String phoneNo, String dateOfBirth){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerNIC = customerNIC;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email){
        this.email = email;
    }

    
}
