/**
* This Create Loans Program is a GUI
* application that creates an array
* of five user inputted loans, it then
* displays all the loans.
*
* Assignment: 10
* 
* @author  Ryan Tran
* @version 1.0
* @since   2023-03-30
*/

package loanCompany;

// Import Classes
import java.util.ArrayList;
import java.text.DecimalFormat; 

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CreateLoans extends Application {

	@Override	// Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Initialize a generic list of loans
		ArrayList<Loan> l = new ArrayList<>();
		
		// Get the current interest rate from an input pop-up window.
		Double rate = Double.parseDouble(JOptionPane.showInputDialog("Enter current prime interest rate <%>: "));
		
		// Create vertical box
		VBox vBox = new VBox(20);
		
		// Create radio buttons and put them in a horizontal box
		ToggleGroup group = new ToggleGroup();
		HBox radios = new HBox(20);
		Label lblType = new Label("Loan Type: ");
		RadioButton rbBusiness = new RadioButton("Business");
		RadioButton rbPersonal = new RadioButton("Personal");
		rbBusiness.setToggleGroup(group);
		rbPersonal.setToggleGroup(group);
		radios.getChildren().addAll(lblType, rbBusiness, rbPersonal);
		radios.setAlignment(Pos.CENTER);
		
		// Initialize text fields and their labels
		Label lblNum = new Label("Loan Number: ");
		Label lblLName = new Label("Last Name: ");
		Label lblAmount = new Label("Loan Amount: ");
		Label lblTerm = new Label("Term Years: ");
		TextField tfNum = new TextField();
		TextField tfLName = new TextField();
		TextField tfAmount = new TextField();
		TextField tfTerm = new TextField();
		
		// Create grid pane for text fields
		GridPane gridPane = new GridPane();
		gridPane.add(lblNum, 0, 0);
		gridPane.add(lblLName, 0, 1);
		gridPane.add(lblAmount, 0, 2);
		gridPane.add(lblTerm, 0, 3);
		gridPane.add(tfNum, 1, 0);
		gridPane.add(tfLName, 1, 1);
		gridPane.add(tfAmount, 1, 2);
		gridPane.add(tfTerm, 1, 3);
		gridPane.setAlignment(Pos.CENTER);
		
		// Create lower box for button and status
		HBox button = new HBox(20);
		TextArea taMessage = new TextArea();
		taMessage.setPrefHeight(60);
		taMessage.setPrefWidth(325);
		taMessage.setEditable(false);
		Button btEnter = new Button("Enter");
		button.getChildren().addAll(taMessage, btEnter);
		button.setAlignment(Pos.CENTER);
		
		// Add all sections to vertical box
		vBox.getChildren().addAll(radios, gridPane, button);
		
		// Create and register the button handler
		btEnter.setOnAction(e -> {
			try {
				if (l.size() != 5) {
					// Take value from text field
					int num = Integer.parseInt(tfNum.getText());
					String lname = tfLName.getText();
					double amount = Double.parseDouble(tfAmount.getText());
					int term = Integer.parseInt(tfTerm.getText());
					
					// Refuse any loan amount over 500000
					if (amount > 500000)
						throw new Exception("Loan Amount can't go over $500,000.");
					
					// Create loan based on radio button
					if (rbBusiness.isSelected()) {
						l.add(new BusinessLoan(num, lname, amount, term));
						System.out.println("Business Loan Added");
					} 
					else if (rbPersonal.isSelected()) {
						l.add(new PersonalLoan(num, lname, amount, term));
						System.out.println("Personal Loan Added");
					}
					else {
						// Throw exception if radio button is not selected
						throw new Exception("Select a loan type.");
					}
					
					// Set interest rate
					l.get(l.size() - 1).setInterestRate(rate);
					
					// Reset all fields
					tfNum.setText("");
					tfLName.setText("");
					tfAmount.setText("");
					tfTerm.setText("");
					rbBusiness.setSelected(false);
					rbPersonal.setSelected(false);
					
					// Update status text box
					taMessage.setText("Loan #" + l.size() + " Added");
				
				}
			}
			catch (Exception ex) {
				// Display Error Message
				taMessage.setText("Invalid Input, Try again\n" + ex);
			}
			// If list has five loans
			if (l.size() == 5) {
				// Close the application window
				primaryStage.close();
				
				// Create a decimal formatter
				DecimalFormat precisionTwo = new DecimalFormat( "0.00" );
				
				// Create and add header to text area
				JTextArea outputTextArea = new JTextArea();
				outputTextArea.append(Loan.companyName + " application loans\n" 
						+ "================================================================================================"
						+ "\n");
				// Add all elements in list
				for (int i = 0; i < l.size(); i++) {
					outputTextArea.append(l.get(i).toString() + "\n"
							+ "Amount owed at end of term: $" + precisionTwo.format(l.get(i).calculateOwed())
							+ "\n"
							+ "------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+ "\n");
					
				}
				// Show results in message window
				JOptionPane.showMessageDialog(null, outputTextArea, "Loan List", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(vBox, 500, 250);
		primaryStage.setTitle("Loan Company");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/** Main Method */
	public static void main(String args[]) {
		launch(args);
	}
	
}
