import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//import java.util.Scanner;

public class ATMGUI_PG2 extends Application {

	// ATM welcome screen
	public void start(Stage stage) {

		// Creating a grid pane
		GridPane pane = new GridPane();

		pane.setPadding(new Insets(30, 100, 30, 100));
		pane.setHgap(8);
		pane.setVgap(8);

		pane.add(new Label("   Welcome Account 1234"), 0, 0); // change this
		pane.add(new Label("What would you like to do?"), 0, 1); // change this

		// Check Balance Button
		Button btBalance = new Button("Check Balance");
		pane.add(btBalance, 0, 2);
		btBalance.setMaxWidth(Double.MAX_VALUE);

		// Check Withdraw Button
		Button btWithdraw = new Button("Withdraw Money");
		pane.add(btWithdraw, 0, 3);
		btWithdraw.setMaxWidth(Double.MAX_VALUE);
		
		// Check Balance Button
		Button btDeposit = new Button("Deposit Money");
		pane.add(btDeposit, 0, 4);
		btDeposit.setMaxWidth(Double.MAX_VALUE);
		
		// Check Balance Button
		Button btExit = new Button("Exit the Account");
		pane.add(btExit, 0, 5);
		btExit.setMaxWidth(Double.MAX_VALUE);

		// on submit button click, call onClick()
		// btAdd.setOnAction(action -> onClick(userInput));

		Scene scene = new Scene(pane);

		stage.setTitle("ATM"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
	}
/*
	public static void main(String[] args) {
		launch(args);
	} */
}
