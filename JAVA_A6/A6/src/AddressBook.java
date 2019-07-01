import javafx.application.Application;
import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;;

public class AddressBook extends Application {
	//Text field values and comboBox values
	TextField fstn = new TextField();
	TextField lstn = new TextField();
	TextField city = new TextField();
	TextField postcde = new TextField();
	ComboBox<String> myComboBox = new ComboBox<String>();
	
	//temp user input values
	String fn;
	String ln;
	String cty;
	String prv;
	String pstcde;
	
	//contactNo is the current contact being viewed in the window
	//totalContacts is the total amount added so far
	static int contactNo = 0;
	static int totalContacts = 0;

	AddressBookLogic addressBookLogic = new AddressBookLogic();
	
	public void start(Stage stage) {
		addressBookLogic.clearFile(); //clears file prior to adding any new data
		// Creating a grid pane with six columns of equal width (70)
		GridPane pane = new GridPane();
		pane.getColumnConstraints().add(new ColumnConstraints(70));
		pane.getColumnConstraints().add(new ColumnConstraints(70));
		pane.getColumnConstraints().add(new ColumnConstraints(70));
		pane.getColumnConstraints().add(new ColumnConstraints(70));
		pane.getColumnConstraints().add(new ColumnConstraints(70));
		pane.getColumnConstraints().add(new ColumnConstraints(70));

		pane.setPadding(new Insets(12, 13, 14, 15));
		pane.setHgap(6);
		pane.setVgap(6);

		// First Name node
		pane.add(new Label("First Name:"), 0, 0);
		pane.add(fstn, 1, 0, 5, 1);

		// Last Name node
		pane.add(new Label("Last Name:"), 0, 1);
		pane.add(lstn, 1, 1, 5, 1);

		// City node
		pane.add(new Label("      City: "), 0, 2);
		pane.add(city, 1, 2);

		// Combo drop down menu for provinces
		myComboBox = new ComboBox<String>();
		myComboBox.getItems().addAll("AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT");

		// Province node
		pane.add(new Label("   Province: "), 2, 2);
		pane.add(myComboBox, 3, 2);

		// Postal Code node
		pane.add(new Label("Postal Code:"), 4, 2);
		pane.add(postcde, 5, 2);

		// Add Button
		Button btAdd = new Button("Add");
		pane.add(btAdd, 0, 3);
		btAdd.setMaxWidth(Double.MAX_VALUE);

		btAdd.setOnAction(action -> { 
			fn = fstn.getText(); //set temp values equal to text field user input
			ln = lstn.getText();
			cty = city.getText();
			prv = myComboBox.getValue();
			pstcde = postcde.getText();
			
			if(!addressBookLogic.validateInput(fn, ln, cty, prv, pstcde)) { //validate data 
				System.out.print("Invalid input detected: please verify that all fields are valid. \n");
				System.out.print("Fields cannot exceed 20 characters.\n");
			}
			else { //if valid data, add to file
			addressBookLogic.write(fn, ln, cty, prv, pstcde);
			contactNo = contactNo + 1; //iterate your contactNo (what you see in the window)
			totalContacts = totalContacts + 1; //iterate total contacts
			clearContactForm(); //clear form to prompt new input
			}	
		});

		// First Button
		Button btFirst = new Button("First");
		pane.add(btFirst, 1, 3);
		btFirst.setMaxWidth(Double.MAX_VALUE);
		btFirst.setOnAction(action -> {
			buttonActionHandlers("First"); //calls function below
		});

		// Next Button
		Button btNext = new Button("Next");
		pane.add(btNext, 2, 3);
		btNext.setMaxWidth(Double.MAX_VALUE);
		btNext.setOnAction(action -> {
			if(contactNo != totalContacts) { // if your current view is not the last possible view (because there's no next if you are at the end)
				buttonActionHandlers("Next"); //calls function below
				}
		});

		// Previous Button
		Button btPrevious = new Button("Previous");
		pane.add(btPrevious, 3, 3);
		btPrevious.setMaxWidth(Double.MAX_VALUE);
		btPrevious.setOnAction(action -> {
			if(contactNo != 1) { //if  you are not at the first contact (there's nothing before the first)
			buttonActionHandlers("Previous"); //calls function below
			}
		});

		// Last Button
		Button btLast = new Button("Last");
		pane.add(btLast, 4, 3);
		btLast.setMaxWidth(Double.MAX_VALUE);
		btLast.setOnAction(action -> {
			buttonActionHandlers("Last"); //calls function below
		});

		// Update Button
		Button btUpdate = new Button("Update");
		pane.add(btUpdate, 5, 3);
		btUpdate.setMaxWidth(Double.MAX_VALUE);
		btUpdate.setOnAction(action -> { 
			fn = fstn.getText(); //set temp values equal to text field user input
			ln = lstn.getText();
			cty = city.getText();
			prv = myComboBox.getValue();
			pstcde = postcde.getText();
			//validate data, trim() needed to remove buffered spaces from reading file
			if(!addressBookLogic.validateInput(fn.trim(), ln.trim(), cty.trim(), prv.trim(), pstcde.trim())) { 
				System.out.print("Invalid input detected: please verify that all fields are valid. \n");
				System.out.print("Fields cannot exceed 20 characters.\n");
			}
			else {
			addressBookLogic.update(contactNo, fn, ln, cty, prv, pstcde); // call function below
			}});

		Scene scene = new Scene(pane);

		stage.setTitle("Address Book"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
	}
	//clears the contact form upon adding a new contact
	//allows for new user input
    void clearContactForm() {
    	fstn.setText("\0");
		lstn.setText("\0");
		city.setText("\0");
		myComboBox.setValue("\0");
		postcde.setText("\0");
    }
    //Event handlers, moves your position in the data and sets the text field to display that data
	void buttonActionHandlers(String action) {
		int index = contactNo;
		switch (action) {
		case "First":
			index = 1; // sets position to 1st
			break;
		case "Next":
			index = index + 1; //sets position to the one after current
			break;
		case "Previous":
			index = index - 1; // sets position the one before current
			break;
		case "Last":
			index = totalContacts; // set position to total number of contacts or last
		}
		fstn.setText(addressBookLogic.getField(index, "FN"));
		lstn.setText(addressBookLogic.getField(index, "LN"));
		city.setText(addressBookLogic.getField(index, "CTY"));
		myComboBox.setValue(addressBookLogic.getField(index, "PRV"));
		postcde.setText(addressBookLogic.getField(index, "PSTCDE"));
		
		contactNo = index; // changes the position you are at
	}
/*
	public static void main(String[] args) {
		launch(args);
	}*/
}
