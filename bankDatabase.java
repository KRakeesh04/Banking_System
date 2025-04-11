import java.util.HashMap;


public class bankDatabase {
    private HashMap<String,Customer> customerDatabase;
    private HashMap<String,Branch> branchDatabase;
    private HashMap<String,account> accountsDatabase;

    public HashMap<String,Customer> ReadFromCustomerDataBase(String filePath){

        return this.customerDatabase;
    }

    public HashMap<String,Branch> ReadFromBranchDataBase(String filePath){

        return this.branchDatabase;
    }

    public HashMap<String,account> ReadFromAccountsDataBase(String FilePath){

        return this.accountsDatabase;
    }



    //method to update latest details to local database when log-out the system
    public void updateCustomersToLocalDatabase(HashMap<String,Customer> customersDetail, String filePath){

    }
    public void updateAccountsToLocalDatabase(HashMap<String,account> accountsDetail, String filePath){

    }
    public void addNewBrancheToLocalDatabase(Branch branchesDetail, String filePath){

    }

}
