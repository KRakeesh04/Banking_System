# Banking System

This is a Java-based Banking System application (terminal-based) that simulates basic banking operations such as account creation, deposits, withdrawals, and more. The system supports both admin and customer functionalities.

---

## Folder Structure

The project is organized as follows:
```text
Banking_System/  
├── BankingSystem.java          
├── account.java                
├── bankDatabase.java           
├── bankStatement.java          
├── Branch.java                 
├── Customer.java               
├── Transaction.java            
├── writePDF.java               
├── exceptions/                 
│   ├── InvalidAmountException.java  
│   ├── InvalidCustomerDetailException.java  
│   ├── InvalidMasterKeyException.java  
│   ├── InvalidPINException.java  
│   ├── InvalidUserNamePasswordException.java  
│   ├── InactiveAccStatusException.java  
│   └── MismatchBranchNameIDException.java  
├── database/                         
│   ├── accounts.csv            
│   ├── branches.csv            
│   ├── transactionsOfCustomers.csv  
│   └── users.csv               
├── .gitignore                  
└── README.md                 
```

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/KRakeesh04/Banking_System.git
   cd Banking_System
2. **Compile and run**:
   ```bash
   javac BankingSystem.java
   java BankingSystem
### Future implementations
1. change data from csv files to mysql
2. create UI using jframe

For any suggestions, feel free to reach out to me
