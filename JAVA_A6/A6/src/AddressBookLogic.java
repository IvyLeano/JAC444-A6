import java.io.RandomAccessFile;

public class AddressBookLogic {

	RandomAccessFile file;
	String filename = "myFile.txt";

	// pos indicates the position to write to in the file
	// contactNo and contacts, are used to track/identify
	// the number of contacts in the file
	int pos = 2;
	int contactNo = 1;
	String contacts = "Contact Number: " + Integer.valueOf(contactNo) + ")";

	// this function fixes the string length to 20, by adding spaces to user inputs less than 20
	String addBuffer(String field) {
		int buffer = 20 - field.length();
		String tempfield = field;

		for (int i = 0; i < buffer; i++) {
			tempfield = tempfield + " ";
		}
		return tempfield;
	}
	// this function clears the contents of the file upon application execution
	void clearFile() {
		try {
			file = new RandomAccessFile(filename, "rw");
			file.setLength(0);
		} catch (Exception e) {
		}
	}

	// Writes to the file, when add button is clicked
	void write(String fn, String ln, String cty, String prv, String pstcode) {
		
		try {
			file = new RandomAccessFile(filename, "rw");
			String newline = "\r\n";
			
			// write contact heading to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(contacts).getBytes());
			contactNo = contactNo + 1;
			contacts = "Contact Number: " + Integer.valueOf(contactNo) + ")";
			pos = pos + 21;
			
			// write first name to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(fn).getBytes());
			pos = pos + 21;

			// write last name to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(ln).getBytes());
			pos = pos + 21;

			// write city to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(cty).getBytes());
			pos = pos + 21;

			// write province to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(prv).getBytes());
			pos = pos + 21;

			// write postal code to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(pstcode).getBytes());
			
			file.write(newline.getBytes());
			pos = pos + 24;
			file.close();
			
		} catch (Exception e) {
			System.out.print(e + "\n");
		}
		
	}

	// Read function returns a contacts information as a single string
	// the line number is passed through, representing the contact number
	String readFileLine(int line) {
		
		String contact = "\0";
		int l = 1; //since contact number starts at 1
		
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			again: while ((contact = file.readLine()) != null) { //while not at end of file, store line to "contact" variable
				if (l == line) { //if the line number of interest (what's passed through) == the line counter "l" then break while
					break again;
				} else { //else continue until line of interest is reached and stored
					l++;
				}
			}
			file.close();
		} catch (Exception e) {
			System.out.print(e + "\n");
		}

		return contact; //return contact as a single string
	}

	//returns the field of interest, ie: first name, last name...
	//based on the contact number and the field
	String getField(int contactNo, String field) {
		String contact = readFileLine(contactNo); //call the readFileLine() function to return data for "contactNo"

		String fieldOfInterest = "\0"; 
		switch (field) { //if field is "FN", "LN" ... return contact substring - ie: return first name, last name or city...
		case "FN": 
			fieldOfInterest = contact.substring(23, 43);
			break;
		case "LN":
			fieldOfInterest = contact.substring(44, 63);
			break;
		case "CTY":
			fieldOfInterest = contact.substring(65, 83);
			break;
		case "PRV":
			fieldOfInterest = contact.substring(86, 103);
			break;
		case "PSTCDE":
			fieldOfInterest = contact.substring(107, contact.length());

		}
		return fieldOfInterest;
	}

	//Updates based on contact number
	void update(int contactNo, String fn, String ln, String cty, String prv, String pstcode) {

		int fieldLen = 129;
		contacts = "Contact Number: " + Integer.valueOf(contactNo) + ")";

		try {
			file = new RandomAccessFile(filename, "rw");
			int pos = (2 + (fieldLen * (contactNo - 1))); //position starts at 2 + the length of the entire row
														  // if contact number is 1, pos would just start at 2, otherwise multiply to get the number of pos before starting pos
			file.seek(pos);
			file.write(addBuffer(contacts).getBytes());
			contactNo = contactNo + 1;
			contacts = "Contact Number: " + Integer.valueOf(contactNo) + ")";
			pos = pos + 21;

			file.seek(pos);
			file.write(addBuffer(fn).getBytes());
			pos = pos + 21;

			// write last name to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(ln).getBytes());
			pos = pos + 21;

			// write city to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(cty).getBytes());
			pos = pos + 21;

			// write province to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(prv).getBytes());
			pos = pos + 21;

			// write postal code to file, set newline and new position
			file.seek(pos);
			file.write(addBuffer(pstcode).getBytes());
			pos = pos + 24;
			file.write(("\r\n").getBytes());

			file.close();
		} catch (Exception e) {
			System.out.print(e + "\n");
		}
	}
	//validates postal code: every even index should be a letter, and every odd index a number
	boolean validPostalCode(String pstcde) {
		boolean valid = true;
		check: for (int i = 0; i < pstcde.length(); i++) {
			if (i % 2 != 0 && Character.isLetter(Character.toLowerCase(pstcde.charAt(i)))) { //odd
				valid = false;
				break check;
			}
			if (i % 2 == 0 && Character.isDigit(Character.toLowerCase(pstcde.charAt(i)))) { //even
				valid = false;
				break check;
			}
		}
		return valid;
	}
	// validates user input
	// I didn't validate repeat inputs since there was no primary key here to
	// separate address info
	boolean validateInput(String fn, String ln, String cty, String prv, String pstcde) {
		boolean empty = fn.length() == 0 || ln.length() == 0 || cty.length() == 0 || pstcde.length() == 0 || prv == null;
		boolean size = fn.length() > 20 || ln.length() > 20 || cty.length() > 20 || pstcde.length() > 20;
		boolean alpha = fn.chars().allMatch(Character::isLetter) || ln.chars().allMatch(Character::isLetter)
				|| cty.chars().allMatch(Character::isLetter);
		boolean validPostal = pstcde.length() == 6 && validPostalCode(pstcde); // no space in postal code
	
		return validPostal && !empty && alpha && !size;
	}
}
