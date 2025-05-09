import java.util.ArrayList;

import exceptions.*;

public class Branch {
    private static String MasterKey = "It's a secret key";
    private String branchName;
    private int branchID;
    private String contactNo;
    private String location;
    private String branchEmail;
    private String branchManagerName;
    private String branchManagerEmail;
    private String branchManagerPhoneNo;
    private ArrayList<Account> branchAccounts;

    Branch(String branchName, int branchID, String contactNo, String location, String branchEmail, String branchManagerName, String branchManagerEmail, String branchManagerPhoneNo) {
        this.branchName = branchName;
        this.branchID = branchID;
        this.contactNo = contactNo;
        this.location = location;
        this.branchEmail = branchEmail;
        this.branchManagerName = branchManagerName;
        this.branchManagerEmail = branchManagerEmail;
        this.branchManagerPhoneNo = branchManagerPhoneNo;
        this.branchAccounts = new ArrayList<>(); // Initialize the list
    }

    // getter method to get information about the branch
    public String getBranchName() {
        return this.branchName;
    }
    public int getBranchID() {
        return this.branchID;
    }
    public String getContactNo() {
        return this.contactNo;
    }
    public String getLocation() {
        return this.location;
    }
    public String getBranchEmail() {
        return this.branchEmail;
    }
    public String getBranchManagerName() {
        return this.branchManagerName;
    }
    public String getBranchManagerEmail() {
        return this.branchManagerEmail;
    }
    public String getBranchManagerPhoneNo() {
        return this.branchManagerPhoneNo;
    }


    
    public void addAccountToBranch(Account acc){
        branchAccounts.add(acc);
    }
    




    // setter method to set values for some attributes after verified with masterkey
    public void setBranchName(String branchName, String key) throws InvalidMasterKeyException {
        if(MasterKey.equals(key)){
            this.branchName = branchName;
        }
        else{
            throw new InvalidMasterKeyException("Incorrect MasterKey");
        }
    }
    public void setContactNo(String contactNo, String key) throws InvalidMasterKeyException {
        if(MasterKey.equals(key)){
            this.contactNo = contactNo;
        }
        else{
            throw new InvalidMasterKeyException("Incorrect MasterKey");
        }
    }
    public void setbranchManagerName(String branchManagerName, String key) throws InvalidMasterKeyException {
        if(MasterKey.equals(key)){
            this.branchManagerName = branchManagerName;
        }
        else{
            throw new InvalidMasterKeyException("Incorrect MasterKey");
        }
    }
    public void setBranchManagerEmail(String branchManagerEmail, String key) throws InvalidMasterKeyException {
        if(MasterKey.equals(key)){
            this.branchManagerEmail = branchManagerEmail;
        }
        else{
            throw new InvalidMasterKeyException("Incorrect MasterKey");
        }
    }
    public void setBranchManagerPhoneNo(String branchManagerPhoneNo, String key) throws InvalidMasterKeyException {
        if(MasterKey.equals(key)){
            this.branchManagerPhoneNo = branchManagerPhoneNo;
        }
        else{
            throw new InvalidMasterKeyException("Incorrect MasterKey");
        }
    }
}
