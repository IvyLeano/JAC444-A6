import java.util.Date;
import java.io.Serializable;;

@SuppressWarnings("serial")
public class Account implements Serializable {
	private int m_id;
	private String m_firstName;
	private String m_lastName;
	private double m_balance;
	private double m_annualInterestRate;
	private Date m_dateCreated;
	private int m_pin;
	
	Account(){
		m_id = 0;
		m_firstName = "\0";
		m_lastName = "\0";
		m_balance = 0.0;
		m_annualInterestRate = 0.0;
		m_dateCreated = new Date();
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
	void setFirstName(String firstName) {
		m_firstName = firstName;
	}
	void setLastName(String lastName) {
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
	//if pin exists it will return the account number
	//if it does not it will return 0, account numbers start at 1
	int accountExists(Account[] account, int pin) {
		int accountNumber = 0;
		for(int i = 0; i < 10; i++) {
			if(account[i].getPin() == pin) {
				accountNumber = i + 1;
			}
		}
		return accountNumber;
	}
}
