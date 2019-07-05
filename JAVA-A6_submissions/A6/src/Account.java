import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Account implements Serializable {     //change this so you have an accounts class and a class that manages an array of accounts it 
	private int m_id;
	private String m_firstName;
	private String m_lastName;
	private double m_balance;
	private double m_annualInterestRate;
	private Date m_dateCreated; 
	private int m_pin;

	// created an array of 10 account objects
	static Account[] m_accounts = new Account[10];

	// constructors
	Account() {
		m_id = 0;
		m_firstName = "\0";
		m_lastName = "\0";
		m_balance = 0.0;
		m_annualInterestRate = 0.0;
		m_dateCreated = new Date(System.currentTimeMillis());
		m_pin = 0;
	}


	Account(double balance, String fn, String ln, double interestRate, int pin, int accountNo) {
		m_id = accountNo;
		m_balance = balance;
		m_firstName = fn;
		m_lastName = ln;
		m_annualInterestRate = interestRate;
		m_pin = pin;
		m_dateCreated = new Date(System.currentTimeMillis());
	}

	// accessor methods
	int getPin() {
		return m_pin;
	}
	int getId() {
		return m_id;
	}

	double getBalance() {
		return m_balance;
	}

	double getAnnualInterestRate() {
		return m_annualInterestRate;
	}
	String getName() {
		return m_firstName + " " + m_lastName;
	}

	Date getDateCreated() {
		return m_dateCreated;
	}

	// mutator methods
	void setPin(int pin) {
		m_pin = pin;
	}
	void setId(int id) {
		m_id = id;
	}

	void setNames(String fn, String ln) {
		m_firstName = fn;
		m_lastName = ln;
	}
	
	void setBalance(double balance) {
		m_balance = balance;
	}
	void setDate() {
		m_dateCreated =  new Date(System.currentTimeMillis());
	}

	void setAnnualInterestRate(double annualInterestRate) {
		m_annualInterestRate = annualInterestRate;
	}

	// initializes accounts
	void initialize(double balance, String fn, String ln, double interestRate, int pin, int accountNo) {
		
		// initialize each account to have a balance of $100
		m_accounts[0] = new Account(balance, fn, ln, interestRate, pin, 0);
		
	}
	// returns annual interest rate divided by 12 months
	double getMonthlyInterestRate() {
		return getAnnualInterestRate() / 12.00;
	}

	// returns monthly interest rate * balance
	double getMonthlyInterest() {
		return getMonthlyInterestRate() * getBalance();
	}

	// withdraws amount from account
	void withdraw(double withdrawalAmount, int index) {
		m_accounts[index].setBalance(m_accounts[index].getBalance() - withdrawalAmount);
	}

	// deposits amount into account
	void deposits(double depositAmount, int index) {
		
		m_accounts[index].setBalance(m_accounts[index].getBalance() + depositAmount);
	}
	//checks if pin is valid and if it is present in the accounts array
		Account validatePin(int accountNo, int pin, int index) {
			Account account = null;
			for(int i = 0; i < index; i++) {
				if(m_accounts[i].getId() == accountNo && m_accounts[i].getPin() == pin) {
					account = m_accounts[i];
				}	
			}
			return account;
		}
		//returns account at index passed through
		Account getAccount(int index) {
		
			return m_accounts[index];
		}
		
	// serialize account objects
	void serialize() {
		String filename = "account.dat";
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			for (int i = 0; i < m_accounts.length; i++) {
				if(getName() != "\0") {
				out.writeObject(m_accounts[i]);
				}
			}
			out.close();
			file.close();

		} catch (Exception e) {
			System.out.print(e + "\n");
		}
	}

	// de-serialize, and output account info
	void deserialize() {
		String filename = "account.dat";
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			Account object = new Account();
			
		   for(int i = 0; i < m_accounts.length; i++) {
			object = (Account) in.readObject();
			m_accounts[i] = object;
			System.out.print("Date Created: " + m_accounts[i].getDateCreated() + "\n");
			System.out.print("Account Number: " + m_accounts[i].getId() + "\n");
			System.out.print("Account Holder: " + m_accounts[i].getName() + "\n");
			System.out.print("Account Balance: " + m_accounts[i].getBalance() + "\n");
			System.out.print("Annual Interest Rate: " + m_accounts[i].getAnnualInterestRate() + "\n\n");
			
			}
			in.close();
			file.close();
		} catch (Exception e) {
			System.out.print(e + "\n");
		}
	}
	
}

