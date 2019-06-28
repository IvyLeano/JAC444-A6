import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Scanner;

public class ATMGUI extends Application {
	@Override
	public void start(Stage stage) {

		// Creating a grid pane 
		GridPane pane = new GridPane();

		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setHgap(8);
		pane.setVgap(20);

		// Account Number node
		pane.add(new Label("Enter an Account number: "), 0,0);
		pane.add(new TextField(), 1, 0);

		// Submit Button
		Button btAdd = new Button("Submit");
		//Scanner input = new Scanner(System.in);
		//int accountNumber = input.nextInt();
		//input.close();
		pane.add(btAdd, 1, 1);
	
		Scene scene = new Scene(pane);

		stage.setTitle("ATM"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
		//System.out.print(accountNumber);
		//return accountNumber;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
