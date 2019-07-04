import java.util.Date;
import java.io.*;

@SuppressWarnings("serial")
public class Account implements Serializable {
	private int m_id;
	private String m_firstName;
	private String m_lastName;
	private double m_balance;
	private double m_annualInterestRate;
	private Date m_dateCreated;
	private int m_pin;
	
	Account[] m_accounts = new Account[10];
	
	
	Account(){
		m_id = 0;
		m_firstName = "\0";
		m_lastName = "\0";
		m_balance = 0.0;
		m_annualInterestRate = 0.0;
		m_dateCreated = new Date();
		m_pin = 0;
	}
	Account(int id, double balance){
		m_id = id;
		m_balance = balance;
	}
	//accessor methods
	int getId() {
		return m_id;
	}
	double getBalance() {
		return m_balance;
	}
	double getAnnualInterestRate() {
		return m_annualInterestRate;
	}
	Date getDateCreated() {
		return m_dateCreated;
	}
	String getFirstName() {
		return m_firstName;
	}
	String getLastName() {
		return m_lastName;
	}
	int getPin() {
		return m_pin;
	}
	//returns annual interest rate divided by 12 months
	double getMonthlyInterestRate() {
		return getAnnualInterestRate() / 12.00;
	}
	//returns monthly interest rate * balance
	double getMonthlyInterest() {
		return getMonthlyInterestRate() * getBalance();
	}
	
	//mutator methods
	void setId(int id) {
		m_id = id;
	}
	void setBalance(double balance) {
		m_balance = balance;
	}
	void setAnnualInterestRate(double annualInterestRate) {
		m_annualInterestRate = annualInterestRate;
	}
	void setName(String firstName, String lastName) {
		m_firstName = firstName;
		m_firstName = lastName;
	}
	
	void setPin(int pin) {
		m_pin = pin;
	}
	//methods
	void withdraw(double withdrawalAmount) {
		double balance = getBalance() - withdrawalAmount;
		setBalance(balance);
	}
	void deposits(double depositAmount) {
		double balance = getBalance() + depositAmount;
		setBalance(balance);
	}
	void initializeAccounts(double balance) {
		for(int i = 0; i < 10; i++) {
			m_accounts[i] = new Account(i + 1, balance);
		}
	}
	int checkPin(int pin) {
		int index = 0;
		for(int i = 0; i < 10; i++) {
			if(m_accounts[i].getPin() == pin) {
				index = i + 1;
			}
		}
		return index;
	}
	void newAccount(int pin, String firstName, String lastName) {
		double standardInterest = 0.05;
		again : for(int i = 0; i < 10; i++) {
			if(getPin() == 0) {
				setPin(pin);
				setName(firstName, lastName);
				setAnnualInterestRate(standardInterest);
				break again;
			}
		}
	}
	void serialize() {
		String filename = "account.dat";
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			for(int i = 0; i < 10; i++) {
				out.writeObject(m_accounts[i]);
			}
			out.close();
			file.close();
			
		}catch(Exception e) {
			System.out.print(e + "\n");
		}
	}
	void deserialize() {
		String filename = "account.dat";
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			//while(EOF) {Account object = (Account)in.readObject();}
			Account object = (Account)in.readObject();
			in.close();
			file.close();
		}catch(Exception e) {
			System.out.print(e + "\n");
		}
	}
}
