package ATM_Interface;

import java.util.Scanner;

class BankAccount {
    String name;  
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register () {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Your Name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter Your Username: ");
        this.userName = sc.nextLine();
        System.out.println("\nEnter Your Passsword: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter Your Account No: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistracion Successful. Please Log in to your Bank Account");
        // sc.close();
    }
    public boolean login() {
        boolean isLogin = false;
        Scanner sc=new Scanner(System.in);
        // sc.close();
        while (!isLogin) {
            System.out.println("\nEnter Your Username: ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.println("\nEnter your password: ");
                    String Passsword = sc.nextLine();
                    if (Passsword.equals(password)) {
                        System.out.println("\nLogin successful");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("\nEnter Amount to Withdraw: ");
        Scanner sc=new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if(balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdral Successful.");
                String str = amount + "Rs Withdral\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nInsufficient Balance.");
            }
        } catch(Exception e) {

        }
        // sc.close();
    }

    public void deposit() {
        System.out.println("\nEnter Amount to Deposit: ");
        Scanner sc=new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if(amount <= 10000f) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit Successful.");
                String str = amount + "Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);

            } else {
                System.out.println("\nSorry! The limit is 10000f.");
            }
        } catch(Exception e) {

        }
        // sc.close();
    }

    public void transfer() {
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Receipent's Name: ");
        String receipent = sc.nextLine();
        System.out.println("\nEnter Amount to transfer");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                if(amount <= 10000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully TRansferred to "+ receipent);
                    String str = amount + "Rs transferred to " + receipent+"\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("\nSorry! The limit is 10000f.");
                }
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch(Exception e) {

        }
        // sc.close();
    }

    public void checkBalance () {
        System.out.println("\n"+balance+"Rs");
    }

    public void transHistory() {
        if(transactions == 0){
            System.out.println("No Transactions Happend");
        } else {
            System.out.println("\n"+transactionHistory);
        }
    }
}

public class ATMInterface {

    public static int takenIntegerInput (int limit) {
        int input = 0;
        boolean flag = false;

        while(!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                // sc.close();

                if(flag && input>limit || input<1) {
                    System.out.println("Choose the number between 1 to "+limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value.");
                flag=false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n*****************WELCOME TO UCO BANK ATM INTERFACE**************");
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option: ");
        int choose = takenIntegerInput(2);

        if (choose == 1) {
            BankAccount b=new BankAccount();
            b.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Enter your choice: ");
                int ch = takenIntegerInput(2);
                if (ch ==1) {
                    if (b.login()) {
                        System.out.println("\n*****************WELCOME BACK "+b.name +"**************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.println("Enter your choice: ");
                            int c = takenIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break; 
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break; 
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }

                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }

}