import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Scanner;

public class ATMGUI_PG1 extends Application {
	// a temporary variable to store user pin input
	static int tempPin = 0;

	// ATM pin prompting screen
	public void start(Stage stage) {

		// Creating a grid pane
		GridPane pane = new GridPane();

		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(8);
		pane.setVgap(20);

		TextField userInput = new TextField();

		// Account Number node
		pane.add(new Label("Enter an Account number: "), 0, 0);
		pane.add(userInput, 1, 0);

		// Submit Button
		Button btAdd = new Button("Submit");
		pane.add(btAdd, 1, 1);

		// on submit button click, call onClick()
		btAdd.setOnAction(action -> onClick(userInput));

		Scene scene = new Scene(pane);

		stage.setTitle("ATM"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
	}
	//sets the t
	public void onClick(TextField userInput) {
			try {
				tempPin = Integer.parseInt(userInput.getText());
				//if(tempPin > 4 || tempPin < 4) { throw new Exception();}
			} catch (Exception e) {
				System.out.print("Invalid Pin: Please enter a 4 digit pin\n");
			}
		}
}
