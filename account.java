import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.*;;

public class account{
    private int acc_No;
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
    account(int acc_No, AccountType type, String customerName, int customerID,double amount, int PIN){
        this.acc_No = acc_No;
        this.acctype = type;
        this.customerName = customerName;
        this.customerID = customerID;
        this.amount = amount;
        this.interestRate = this.acctype.getInterestRate();
        this.PIN = PIN;
        this.currentStatus = Status.Active ;
        this.setCreatedDate();
    }

    private void setCreatedDate(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm:ss");
        this.createdDate = dateTime.format(formater);
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
    public void withdrawMoney(double amount) throws InvalidAmountException{
        if(this.amount < amount){
            throw new InvalidAmountException("Your input exceeds the account balance amount. Please check the input.");
        }
        else{
            this.amount -= amount;
            System.out.println("Withdrawal sucessfully completed.");
        }
    }

    
    // method to check the pin
    public int getPIN(){
        return this.PIN;
    }
    
    public double getAmount(){
        return this.amount;
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





