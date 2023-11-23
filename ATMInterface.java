package task3;
import java.util.*;

public class ATMInterface {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ATM a = new userBankAccount();
		double amount;
		//displaying options to perform by user
		while(true) {//to repeat multiple operations by the user
			System.out.println("Please select any one option from below:-");
			System.out.println("1. Withdraw");
			System.out.println("2. Deposit");
			System.out.println("3. Check Balance");
			System.out.println("4. Exit");
			int choice = sc.nextInt();
			
			switch(choice) {//performing the operations according to the user choice
			
			case 1: System.out.println("Enter the amount to withdraw:");
				amount = sc.nextDouble();
				a.withdraw(amount);
				break;
			
			case 2: System.out.println("Enter the amount to deposit:");
				amount = sc.nextDouble();
				a.deposit(amount);
				break;
			
			case 3: a.checkBalance();
					break;
			
			case 4: System.out.println("Thanks for using the ATM");
					sc.close();
					return;
			//once the operations done user get exited from the atm
			default: System.out.println("Please choose the correct option");
					 break;
			}
		}
	}
}

interface ATM{//atm interface class to hide the operations performing in the user Bank Account
	void withdraw(double amount);
	void deposit(double amount);
	void checkBalance();
}

class userBankAccount implements ATM{
	private double balance=0.0;//initial balance will be 0
	
	public void withdraw(double amount) {
		if(amount<=balance) {//checking the balance for sufficient to withdraw amount
		balance-=amount;//amount withdrawn from the balance
		System.out.println("Your amount "+amount+" is debited successfully");
		}
		else {
			System.out.println("Sorry your balance is insufficient to withdraw the requested amount");
		}
	}
	
	public void deposit(double amount) {
		balance+=amount;//amount deposited to the balance
		System.out.println("Your amount "+amount+" is credited successfully");
	}
	
	public void checkBalance() {//balance checking method
		System.out.println("Your current balance is "+balance);
	}
}