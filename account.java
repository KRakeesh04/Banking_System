import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.*;;

public class account{
    private int acc_No;
    private int branchID;
    private String branchName;
    private AccountType acctype;
    private String customerName;
    private int customerID;
    private double amount = 0.00;
    private double interestRate;
    private int PIN = 0000;
    private String createdDate;
    private Status currentStatus;

    // account(){
    //     this.setCreatedDate();
    // }
    account(int acc_No, int branchID, String branchName, AccountType type, String customerName, int customerID,double amount, int PIN){
        this.acc_No = acc_No;
        this.branchID = branchID;
        this.branchName = branchName;
        this.acctype = type;
        this.customerName = customerName;
        this.customerID = customerID;
        this.amount = amount;
        this.interestRate = this.acctype.getInterestRate();
        this.PIN = PIN;
        this.currentStatus = Status.Active ;
        this.setCreatedDate();
    }
    account(int acc_No, int branchID, String branchName, AccountType type, String customerName, int customerID,double amount, int PIN, String createdDate, Status status){
        this.acc_No = acc_No;
        this.branchID = branchID;
        this.branchName = branchName;
        this.acctype = type;
        this.customerName = customerName;
        this.customerID = customerID;
        this.amount = amount;
        this.interestRate = this.acctype.getInterestRate();
        this.PIN = PIN;
        this.currentStatus = status;
        this.createdDate = createdDate;
    }
    

    private void setCreatedDate(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        this.createdDate = dateTime.format(formater);
    }

    public void setAccStatus(Status status){
        this.currentStatus = status;
    }

    public void setPIN(int oldPIN, int newPIN) throws InvalidPINException{
        if(this.PIN == oldPIN){
            this.PIN = newPIN;
        }
        else {
            throw new InvalidPINException("Please correctly insert the old PIN number.");
        }
    }

    
    // method to make deposit
    public void depositMoney(double amount, boolean ShowConfirmmation){
        this.amount += amount;
        if(ShowConfirmmation){
            System.out.println("Sucessfully deposited.");
        }
    }
    public void depositMoney(double amount){
        this.depositMoney(amount, false);
    }
    

    // method to make a withdrawal
    public void withdrawMoney(double amount) throws InvalidAmountException, InactiveAccStatusException{
        if(this.amount >= amount && this.currentStatus == Status.Active){
            this.amount -= amount;
            System.out.println("Withdrawal sucessfully completed.");
        }
        else if(this.currentStatus != Status.Active){
            throw new InactiveAccStatusException("Your account is inactive. Please contact the bank.");
        }
        else{
            throw new InvalidAmountException("Insufficient balance to withdraw.");
        }
    }

    // method to transfer money
    public void transferMoney(account toAcc, double amount) throws InvalidAmountException, InactiveAccStatusException{
        if(this.amount >= amount && this.currentStatus == Status.Active){
            this.amount -= amount;
            toAcc.depositMoney(amount);
            System.out.println("Transfer sucessfully completed.");
        }
        else if(this.currentStatus != Status.Active){
            throw new InactiveAccStatusException("Your account is inactive. Please contact the bank.");
        }
        else{
            throw new InvalidAmountException("Insufficient balance to transfer.");
        }
    }


    // method to get the balance
    public double getBalance() throws InactiveAccStatusException{
        if(this.currentStatus == Status.Active){
            return this.amount;
        }
        else{
            throw new InactiveAccStatusException("Your account is inactive. Please contact the bank.");
        }
    }

    // method to calculate and add interest
    public void calculateInterest() throws InactiveAccStatusException{
        if(this.currentStatus == Status.Active){
            // double interest = this.amount * this.interestRate;
            // this.amount += interest;
            //TODO: calculation for the interest 
        }
        else{
            throw new InactiveAccStatusException("Your account is inactive. Please contact the bank.");
        }
    }
    
    // getter methods for this class
    public int getPIN(){
        return this.PIN;
    }

    public double getInterestRate(){
        return this.interestRate;
    }

    public int getAccNo(){
        return this.acc_No;
    }

    public String getCustomerName(){
        return this.customerName;
    }
    
    public int getCustomerID(){
        return this.customerID;
    }
    
    public String getCreatedDate(){
        return this.createdDate;
    }

    public AccountType getAccType(){
        return this.acctype;
    }
    
    public Status getAccStatus(){
        return this.currentStatus;
    }

    public String getBranchName(){
        return this.branchName;
    }

    public int getBranchID(){
        return this.branchID;
    }
}




enum Status{
    Active,
    Closed,
    Suspended;
}


enum AccountType{
    saving(0.045),
    current(0.0),
    Fixed(0.07);

    private static final String masterKey = "passcode_to_edit_intrestRate_by_Bank";
    private double interestRate;

    AccountType(double interestRate){
        this.interestRate = interestRate;
    }

    public double getInterestRate(){
        return this.interestRate;
    }

    public void ChangeInterestRate(String Key, double newRAte) throws InvalidMasterKeyException{
            if(masterKey == Key){
                this.interestRate =newRAte;
            }
            else{
                throw  new InvalidMasterKeyException("Incorrect MasterKey");
            }
    }
}





