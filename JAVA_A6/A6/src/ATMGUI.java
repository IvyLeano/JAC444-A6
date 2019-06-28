import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ATMGUI extends Application {
	
	// a temporary variable to store user pin input
	int tempPin = 0;
	
	int index;
	String fn;
	String ln;
	boolean validPin = false;
	Account accounts = new Account();
	
	// ATM pin prompting screen
	public void start(Stage stage) {
	
		accounts.initializeAccounts(100.00);
		// Creating a grid pane
		GridPane pane1 = new GridPane();
		GridPane pane2 = new GridPane();
		GridPane pane3 = new GridPane();
		pane1.setPrefSize(400, 300);
		pane2.setPrefSize(400, 300);
		pane3.setPrefSize(400, 300);
		//pane1.setPadding(new Insets(100, 100, 100, 100));
		//pane2.setPadding(new Insets(100, 100, 100, 100));
		//pane.setHgap(8);
		//pane.setVgap(20);

		TextField userInput = new TextField();

		// Account Number node
		pane1.add(new Label("Enter an Account number: "), 0, 0);
		pane1.add(userInput, 1, 0);

		// Submit Button
		Button btAdd = new Button("Submit");
		pane1.add(btAdd, 1, 1);
		Scene scene = new Scene(pane1);
		// on submit button click, call onClick()
		btAdd.setOnAction(action -> {
			try {
				int pin = Integer.parseInt(userInput.getText());
				if(String.valueOf(pin).length() != 4) { throw new Exception();}
				else {
					tempPin = pin;
					validPin = true;
					if(validPin) {
					index = accounts.checkPin(pin);
					if(index != 0) {
						
						//secondPage(stage);
						pane2.add(new Label("   Welcome Account 1234"), 0, 0); // change this
						pane2.add(new Label("What would you like to do?"), 0, 1); // change this

						// Check Balance Button
						Button btBalance = new Button("Check Balance");
						pane2.add(btBalance, 0, 2);
						btBalance.setMaxWidth(Double.MAX_VALUE);
				
						

						// Check Withdraw Button
						Button btWithdraw = new Button("Withdraw Money");
						pane2.add(btWithdraw, 0, 3);
						btWithdraw.setMaxWidth(Double.MAX_VALUE);
						
						// Check Balance Button
						Button btDeposit = new Button("Deposit Money");
						pane2.add(btDeposit, 0, 4);
						btDeposit.setMaxWidth(Double.MAX_VALUE);
						
						// Check Balance Button
						Button btExit = new Button("Exit the Account");
						pane2.add(btExit, 0, 5);
						btExit.setMaxWidth(Double.MAX_VALUE);
						Scene scene2 = new Scene(pane2);
						stage.setScene(scene2); 

						}
					else {
						System.out.print("enter info");
						TextField userInput2 = new TextField();
						TextField userInput3 = new TextField();
						//TextField userInput4 = new TextField();
			
						// Account Number node
						pane3.add(new Label("Create New Account: "), 0, 0);
			
						pane3.add(new Label("Enter your first name: "), 0, 1);
						pane3.add(userInput2, 1, 1);
						fn = userInput2.getText();
						
						pane3.add(new Label("Enter your last name: "), 0, 2);
						pane3.add(userInput3, 1, 2);
						ln = userInput3.getText();
						/*
						pane3.add(new Label("Enter a new pin: "), 0, 3);
						pane3.add(userInput4, 1, 3);
						tempPin = Integer.parseInt(userInput4.getText());
						*/
						Button btSub = new Button("Submit");
						pane3.add(btSub, 1, 4);
						
						btSub.setOnAction(onClick -> { 
							accounts.newAccount(tempPin, fn, ln);
						    stage.setScene(scene);
							
						});
						
						Scene scene3 = new Scene(pane3);
						stage.setScene(scene3);
					}
					}
					}
			} catch (Exception e) {
				System.out.print("Invalid Pin: Please enter a 4 digit pin\n");
			}
		});
		//Scene scene = new Scene(pane1);
		
		stage.setTitle("ATM"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage		
	}
	
}
