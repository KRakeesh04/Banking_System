public class Transaction {
    private long transactionID; //random 16 - 20 digit number
    private String transactionType; // deposit, withdraw, transfer
    private String transactionDate; // date and time of transaction
    private double transactionAmount; // amount of transaction
    private String transactionDescription; // description of transaction
    private int acc_No; // account number of the transaction
    private String transactionStatus; // status of transaction (success, failed, pending)
    // private String transactionMethod; // method of transaction (ATM, online banking, branch visit)

    public Transaction(long transactionID, String transactionType, String transactionDate, double transactionAmount, String transactionDescription, int acc_No) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionDescription = transactionDescription;
        this.acc_No = acc_No;
        this.transactionStatus = "success"; // default status
    }

    public long getTransactionID() {
        return transactionID;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public double getTransactionAmount() {
        return transactionAmount;
    }
    public String getTransactionDescription() {
        return transactionDescription;
    }
    public int getAcc_No() {
        return acc_No;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }

    
}
