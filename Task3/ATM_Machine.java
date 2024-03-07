import java.util.Scanner;

class BankAccountDetails {
    private double balance;

    public BankAccountDetails(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void depositAmount(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("Current Balance: " + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("Current Balance: " + balance);
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

public class ATM_Machine{
    private BankAccountDetails bankAccountDetails;

    public ATM_Machine(BankAccountDetails bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + bankAccountDetails.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    bankAccountDetails.depositAmount(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    bankAccountDetails.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankAccountDetails account = new BankAccountDetails(1000.0);
        ATM_Machine atm_Machine = new ATM_Machine(account);
        atm_Machine.run();
    }
}
