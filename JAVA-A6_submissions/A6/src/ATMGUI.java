import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ATMGUI extends Application {
	static int pin = 0;
	static TextField newpinInput;
	static TextField fnInput;
	static TextField lnInput;
	static Account account = new Account();
	static TextField accountNoInput = new TextField();

	@Override
	public void start(Stage stage) {
		account.initialize(100.00);
		// creating 3 gridpanes for: pin page, welcome account and create account page
		GridPane gridPane = new GridPane();
		GridPane gridPane2 = new GridPane();
		GridPane gridPane3 = new GridPane();

		// pane1 setup - pin page
		Text accountNo = new Text("Enter account number: ");
		accountNoInput = new TextField();
		Button btnSubmit = new Button("Submit");

		// pane2 setup - welcome account page
		Text welcome = new Text("   Welcome Account " + account.validatePin(pin).getId() + "\n");
		Text options = new Text("What would you like to do?\n");
		Button btnBalance = new Button("Check Balance");
		btnBalance.setMaxWidth(Double.MAX_VALUE);
		Button btnWithdraw = new Button("Withdraw Money");
		btnWithdraw.setMaxWidth(Double.MAX_VALUE);
		Button btnDeposit = new Button("Deposit Monty");
		btnDeposit.setMaxWidth(Double.MAX_VALUE);
		Button btnExit = new Button("Exit the Account");
		btnExit.setMaxWidth(Double.MAX_VALUE);

		// pane3 setup - create account page
		Text welcome2 = new Text(
				"Welcome! The pin you have entered is not registerd to any account. \nPlease register your account below: ");
		Text firstname = new Text("Enter your first name: ");
		fnInput = new TextField();
		Text lastname = new Text("Enter your last name: ");
		lnInput = new TextField();
		Text newpin = new Text("Enter a pin: ");
		newpinInput = new TextField();
		Button btnSubmit2 = new Button("Submit");

		// gridPane sizing/padding setup
		gridPane.setMinSize(400, 200);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane2.setMinSize(400, 200);
		gridPane2.setPadding(new Insets(10, 10, 10, 10));
		gridPane3.setMinSize(400, 200);
		gridPane3.setPadding(new Insets(10, 10, 10, 10));

		// gridPane vertical and horizontal gaps between the columns
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane2.setVgap(5);
		gridPane2.setHgap(5);
		gridPane3.setVgap(5);
		gridPane3.setHgap(5);

		// Setting the Grid alignment
		gridPane.setAlignment(Pos.CENTER);
		gridPane2.setAlignment(Pos.CENTER);
		gridPane3.setAlignment(Pos.CENTER);

		// Arranging all the nodes in the grid for pane 1
		gridPane.add(accountNo, 0, 0);
		gridPane.add(accountNoInput, 1, 0);
		gridPane.add(btnSubmit, 1, 2);

		// Arranging all the nodes in the grid for pane 2
		gridPane2.add(welcome, 0, 0);
		gridPane2.add(options, 0, 1);
		gridPane2.add(btnBalance, 0, 2);
		gridPane2.add(btnWithdraw, 0, 3);
		gridPane2.add(btnDeposit, 0, 4);
		gridPane2.add(btnExit, 0, 5);

		// Arranging all nodes in the grid for pane3
		gridPane3.add(welcome2, 0, 0);
		gridPane3.add(firstname, 0, 1);
		gridPane3.add(fnInput, 1, 1);
		gridPane3.add(lastname, 0, 2);
		gridPane3.add(lnInput, 1, 2);
		gridPane3.add(newpin, 0, 3);
		gridPane3.add(newpinInput, 1, 3);
		gridPane3.add(btnSubmit2, 1, 4);

		// button onClick events
		btnSubmit.setOnAction(action -> { // Submit button for pin page >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>...
				while(Integer.valueOf(accountNoInput.getText())) {
				try {	
					accountNoInput = new TextField();
					int tempPin = Integer.valueOf(accountNoInput.getText());
					if (Integer.toString(tempPin).length() != 4) {
						throw new Exception();
					}
					else {
						pin = tempPin;
						System.out.print(pin);
					}
				} catch (Exception e) {
					System.out.print("Invalid Input, Please enter a 4 digit pin\n");
					accountNoInput = new TextField();
				}
				}
/*
			// if there is no account with that pin, send to create account page
			if (account.validatePin(pin) == null) {
				System.out.print("null account");
				Scene scene3 = new Scene(gridPane3);
				stage.setScene(scene3);
			}
			// if there is an account, send to scene2 - welcome p
			else {
				System.out.print("account found");
				Scene scene2 = new Scene(gridPane2);
				stage.setScene(scene2);
			};*/
		});

		btnSubmit2.setOnAction(action -> { // Submit button for create account page
			boolean valid = false;
			try {
				int tempPin = Integer.valueOf(newpinInput.getText());
				boolean nullInput = fnInput.getText() == "\0" || lnInput.getText() == "\0"
						|| Integer.toString(tempPin).length() != 4
						|| !fnInput.getText().chars().allMatch(Character::isLetter)
						|| !lnInput.getText().chars().allMatch(Character::isLetter);
				if (nullInput) {
					throw new Exception();
				} else {
					valid = true;
					pin = tempPin;
				}
			} catch (Exception e) {
				System.out.print("Invalid Input. Please enter valid data into all fields\n");
			}
			// if user input is valid, store the data and return to pin input scene
			if (valid) {
				account.createAccount(fnInput.getText(), lnInput.getText(), 0.05, pin);
			}
		});

		Scene scene = new Scene(gridPane);
		stage.setTitle("ATM");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}