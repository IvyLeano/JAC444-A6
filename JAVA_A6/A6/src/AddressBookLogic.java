
//A class that stores contact information
class Contacts {
	private String m_firstName;
	private String m_lastName;
	private String m_city;
	private String m_province;
	private String m_postalCode;

	// No-argument default constructor
	Contacts() {
		m_firstName = "\0";
		m_lastName = "\0";
		m_city = "\0";
		m_province = "\0";
		m_postalCode = "\0";
	}

	// Setter methods for the Contact class
	void setName(String fn, String ln) {
		m_firstName = fn;
		m_lastName = ln;
	}

	void setCity(String cty) {
		m_city = cty;
	}

	void setProvince(String prv) {
		m_province = prv;
	}
	void setPostalCode(String pstcde) {
		m_postalCode = pstcde;
	}

	String getFirstName() {
		return m_firstName;
	}

	String getLastName() {
		return m_lastName;
	}

	String getCity() {
		return m_city;
	}

	String getProvince() {
		return m_province;
	}

	String getPostalCode() {
		return m_postalCode;
	}

	// method used to check if there is no contact data stored
	boolean isEmpty() {
		return getFirstName() == "\0";
	}
}

public class AddressBookLogic {
	// The address book only has an array of contacts, set to 10 in this case
	Contacts[] m_contacts = new Contacts[10];
	int m_noContacts = 0;

	AddressBookLogic() {
		for (int i = 0; i < 10; i++) {
			m_contacts[i] = new Contacts();
		}
	}

	int getFirstEmptyContactSlot() {
		int index = 0;
		check : for(int i = 0; i < 10; i++) {
			if(m_contacts[i].isEmpty()) { 
				index = i;
				break check;
				}
		}
		System.out.print("getFirstEmptyContactSlot(): " + index + "\n");
		return index;
	}
	
	// add button functionality, adds a contact
	void add(String fn, String ln, String cty, String prv, String pstcode) {
		int index = getFirstEmptyContactSlot();
		m_contacts[index].setName(fn, ln);
		m_contacts[index].setCity(cty);
		m_contacts[index].setProvince(prv);
		m_contacts[index].setPostalCode(pstcode);
		
		m_noContacts = m_noContacts + 1;
		
		System.out.print("getFirstEmptyContactSlot(): \n");
	}
	Contacts getContact(int index) {
		return m_contacts[index];
	}
	///remove this when done
	void test() {
		 for(int i = 0; i < 10; i++) {
			 System.out.print("test(): \n");
			 System.out.print(m_contacts[i].getFirstName() + " " +
			 m_contacts[i].getLastName()  + " " +
			 m_contacts[i].getCity()  + " " +
			 m_contacts[i].getProvince()  + " " +
			 m_contacts[i].getPostalCode()  + "\n");
			 
		}
	}

}
