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
	static int accountNumber = 0;
	static int countofAccounts = 0;
	static TextField newpinInput;
	static TextField fnInput;
	static TextField lnInput;
	static Account account = new Account();
	static TextField accountNoInput = new TextField();
	static TextField accountPinInput = new TextField();
	static Text accountInfoText = new Text();
	static TextField withdrawAmount = new TextField();
	static Button submit3 = new Button("Submit");
	
	

	@Override
	public void start(Stage stage) {
		Account accounts = new Account();
		// creating 3 gridpanes for: pin page, welcome account and create account page
		GridPane gridPane = new GridPane();
		GridPane gridPane2 = new GridPane();
		GridPane gridPane3 = new GridPane();

		// pane1 setup - pin page
		Text accountNo = new Text("Enter account number: ");
		accountNoInput = new TextField();
		Text accountPin = new Text("Enter pin: ");
		accountPinInput = new TextField();
		Button btnSubmit = new Button("Submit");

		// pane2 setup - welcome account page
		Text welcome = new Text("   Welcome Account " + accountNumber + "\n");
		Text options = new Text("What would you like to do?\n");
		Button btnBalance = new Button("Check Balance");
		btnBalance.setMaxWidth(Double.MAX_VALUE);
		Button btnWithdraw = new Button("Withdraw Money");
		btnWithdraw.setMaxWidth(Double.MAX_VALUE);
		Button btnDeposit = new Button("Deposit Money");
		btnDeposit.setMaxWidth(Double.MAX_VALUE);
		Button btnExit = new Button("Exit the Account");
		btnExit.setMaxWidth(Double.MAX_VALUE);
		

		// pane3 setup - create account page
		Text welcome2 = new Text(
				"Welcome! The account number you have entered is not registerd. \nPlease register your account below: ");
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
		gridPane.add(accountPin, 0, 1);
		gridPane.add(accountPinInput, 1, 1);
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
				try {
					if(accountNoInput.getText().length() == 0 || accountPinInput.getText().length() != 4) {
						throw new Exception();
					}
					//checks if the input is numeric, throws exception if input is not numeric
					accountNumber =  Integer.valueOf(accountNoInput.getText());
					pin = Integer.valueOf(accountPinInput.getText());
			
				} catch(Exception e) {
					System.out.print("Incorrect Input, fields should be numeric and pins of 4 digit lengths. Please try again.\n");
				}
				//accountNumber and pin are originally set to 0, if anything else is stored then the data must have been valid
				if(accountNumber != 0 && pin != 0) {
					//checks if account exists
					if(countofAccounts != 0) {
					 if(accounts.validatePin(accountNumber, pin, countofAccounts) != null) {
						Scene scene2 = new Scene(gridPane2);
						stage.setScene(scene2);
					}
					}
					//if account doesn't exist then send to create account screen
					else {
						Scene scene3 = new Scene(gridPane3);
						stage.setScene(scene3);
						accountNumber = 0; 
						pin = 0;
					}
				}	
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
				accounts.initialize(100.00, fnInput.getText(), lnInput.getText(), 0.05, pin, 0);
				
				Text accountInfoText2 = new Text("The form has been submitted. Your account number is: " + accountNumber);
				gridPane3.add(accountInfoText2, 0, 5);
				accountNumber = accountNumber + 1;
				Scene scene4 = new Scene(gridPane2);
				stage.setScene(scene4);
			}
			
		});
		
		btnBalance.setOnAction(action ->{ 
			gridPane2.getChildren().remove(accountInfoText);
			gridPane2.getChildren().remove(withdrawAmount);
			gridPane2.getChildren().remove(submit3);
			accountInfoText = new Text("Account Balance: " + account.getAccount(countofAccounts).getBalance());
			gridPane2.add(accountInfoText, 0, 6);
	
		
			
		});
		
		btnWithdraw.setOnAction(action ->{
			gridPane2.getChildren().remove(accountInfoText);
			accountInfoText = new Text("Withdrawal amount: \n");
			gridPane2.add(accountInfoText, 0, 6);
			
			gridPane2.getChildren().remove(withdrawAmount);
			withdrawAmount = new TextField();
			gridPane2.add(withdrawAmount, 0, 7);
			
			Button submit3 = new Button("Submit");
			gridPane2.add(submit3, 0, 8);
			
			submit3.setOnAction(action2 ->{
				//System.out.print());
				//Text accountInfoText2 = new Text();
				if(Integer.valueOf(withdrawAmount.getText()) > account.getAccount(countofAccounts).getBalance()){
					gridPane2.getChildren().remove(accountInfoText);
					gridPane2.getChildren().remove(withdrawAmount);
					accountInfoText = new Text("Invalid entry. "); //*****
					gridPane2.add(accountInfoText, 0, 6);
					//accountInfoText2 = new Text(" ");
					//gridPane2.add(accountInfoText2, 0, 10);
				}
				else {
					gridPane2.getChildren().remove(accountInfoText);
					gridPane2.getChildren().remove(withdrawAmount);
					gridPane2.getChildren().remove(submit3);
				accounts.withdraw(Integer.valueOf(withdrawAmount.getText()), countofAccounts); // //validate >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
				}
				
				
			});
		//	withdrawAmount = new TextField();
			
		});
		btnDeposit.setOnAction(action1 ->{
			gridPane2.getChildren().remove(accountInfoText);
			accountInfoText = new Text("Deposit amount: \n");
			gridPane2.add(accountInfoText, 0, 6);
			withdrawAmount = new TextField();
			gridPane2.add(withdrawAmount, 0, 12);
			Button submit3 = new Button("Submit");
			gridPane2.add(submit3, 0, 13);
			
			submit3.setOnAction(action2 ->{
			accounts.deposits(Integer.valueOf(withdrawAmount.getText()), countofAccounts); // //validate >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.
			gridPane2.getChildren().remove(accountInfoText);
			gridPane2.getChildren().remove(withdrawAmount);
			gridPane2.getChildren().remove(submit3);
				
		});
		});
		btnExit.setOnAction(action2 ->{
			//accounts.serialize();
			System.exit(0);
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