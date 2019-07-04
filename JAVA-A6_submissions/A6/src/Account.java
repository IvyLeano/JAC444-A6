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
	Account[] m_accounts = new Account[10];

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


	Account(int id, double balance) {
		m_id = id;
		m_balance = balance;
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

	void setAnnualInterestRate(double annualInterestRate) {
		m_annualInterestRate = annualInterestRate;
	}

	// methods
	// initializes accounts
	void initialize(double balance) {
		// initialize each account to have a balance of $100
		for (int i = 0; i < 10; i++) {
			m_accounts[i] = new Account(i + 1, balance);
		}
	}

	void createAccount(String fn, String ln, double interestRate, int pin) {
		// initialize each account to have a balance of $100
		again : for (int i = 0; i < 10; i++) {
			if(m_accounts[i].getName() == "\0") {
			m_accounts[i].setNames(fn, ln);
			m_accounts[i].setAnnualInterestRate(interestRate);
			m_accounts[i].setPin(pin);
			break again;
			}
		}
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
	void withdraw(double withdrawalAmount) {
		double balance = getBalance() - withdrawalAmount;
		setBalance(balance);
	}

	// deposits amount into account
	void deposits(double depositAmount) {
		double balance = getBalance() + depositAmount;
		setBalance(balance);
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
			
			System.out.print("Date Created: " + object.getDateCreated() + "\n");
			System.out.print("Account Number: " + object.getId() + "\n");
			System.out.print("Account Holder: " + object.getName() + "\n");
			System.out.print("Account Balance: " + object.getBalance() + "\n");
			System.out.print("Annual Interest Rate: " + object.getAnnualInterestRate() + "\n\n");
			
			}
			in.close();
			file.close();
		} catch (Exception e) {
			System.out.print(e + "\n");
		}
	}
	//checks if pin is valid and if it is present in the accounts array
	Account validatePin(int pin) {
		Account account = null;
		for(int i = 0; i < m_accounts.length; i++) {
			if(m_accounts[i].getPin() == pin) {
				account = m_accounts[i];
			}
			
		}
		return account;
	}
	

}
