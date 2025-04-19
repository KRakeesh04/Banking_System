public class Transection {
    private int transectionID; //random 10 digit number
    private String transectionType; // deposit, withdraw, transfer
    private String transectionDate; // date and time of transection
    private double transectionAmount; // amount of transection
    private String transectionDescription; // description of transection
    private int acc_No; // account number of the transection
    private String transectionStatus; // status of transection (success, failed, pending)
    // private String transectionMethod; // method of transection (ATM, online banking, branch visit)

    public Transection(int transectionID, String transectionType, String transectionDate, double transectionAmount, String transectionDescription, int acc_No) {
        this.transectionID = transectionID;
        this.transectionType = transectionType;
        this.transectionDate = transectionDate;
        this.transectionAmount = transectionAmount;
        this.transectionDescription = transectionDescription;
        this.acc_No = acc_No;
        this.transectionStatus = "success"; // default status
    }
}
