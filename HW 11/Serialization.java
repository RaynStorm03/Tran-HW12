/**
* This Serialization program is a menu-
* driven program that takes and collects
* the user's information.
*
* Assignment: 11
* 
* @author  Ryan Tran
* @version 1.0
* @since   2023-04-04
*/


package serialization;

// Import Classes
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Serialization {
	
	/** Main Method */
	public static void main(String[] args) {
		// Initialize generic list
		ArrayList<Person> l = new ArrayList<>();
		
		// Get information if file already exists
		try {
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("Person.bin"));
			l = (ArrayList<Person>) inStream.readObject();
			inStream.close();
		}
		catch (IOException ex) {
		} 
		catch (ClassNotFoundException e) {
		}
		
		// Initialize scanner object
		Scanner kb = new Scanner(System.in);
		
		// Iniatialize options
		int option = 0;
		
		// Keep looping until user exits
		while (option != 5) {
			try {
				// Display options
				System.out.println("1) Add information into a file\n"
								+ "2) Retrieve information from a file and display them.\n"
								+ "3) Delete information.\n"
								+ "4) Update information.\n"
								+ "5) Exit.");
				System.out.print("Make a selection: ");
				// Take input
				option = kb.nextInt();
				System.out.println();
				
				// Errors if not within 1-5
				if (option < 1 || option > 5) {
					throw new Exception("Invalid Option, try again.");
				}
				kb.nextLine();
				
				// Switch case
				switch(option) {
					case 1:		// Add information to a file
						// Enter person data
						System.out.print("Enter name: ");
						String name = kb.nextLine();
						System.out.print("Enter phone number: ");
						String phone = kb.nextLine();
						System.out.print("Enter date of birth: ");
						String DOB = kb.nextLine();
						System.out.print("Enter email address: ");
						String email = kb.nextLine();
						
						// Add to list and write to file
						l.add(new Person(name, phone, DOB, email));
						writeToFile(l);
						System.out.println();
						break;
					case 2:		// Retrieve information from a file and display them
						// Print if file is not empty
						if (l.size() > 0)
							readFile();
						else
							System.out.println("File is empty");
						System.out.println();
						break;
					case 3:		// Delete information
						// Prompt user for name
						System.out.print("Whos information would you like to delete? <Enter Name>: ");
						String rname = kb.nextLine();
						
						// Try to delete from list and update file
						try {
						l.remove(findName(rname, l));
						writeToFile(l);	
						System.out.println(rname + " deleted.\n");
						}
						catch (Exception ex) {
							// Display error message
							System.out.println("Person not found.\n");
						}
						break;
					case 4:		// Update Information
						// Prompt user for name
						System.out.print("Whos information would you like to update? <Enter Name>: ");
						String uname = kb.nextLine();
						
						// Try to find person
						try {
							// Find person's name
							int index = findName(uname, l);
							if (index == -1)
								throw new Exception();
							
							// Enter new information
							System.out.print("Enter new phone number (or leave blank to keep): ");
							String uphone = kb.nextLine();
							System.out.print("Enter new date of birth (or leave blank to keep): ");
							String uDOB = kb.nextLine();
							System.out.print("Enter new email address (or leave blank to keep): ");
							String uemail = kb.nextLine();
							
							// Update information
							if (uphone != "")
								l.get(index).setPhone(uphone);
							if (uDOB != "")
								l.get(index).setDOB(uDOB);
							if (uemail != "")
								l.get(index).setEmail(uemail);
							
							// Update file
							writeToFile(l);
							System.out.println(l.get(index).getName() + " updated.\n");
						}
						catch (Exception ex) {
							// Display error message
							System.out.println("Person not found.\n");
						}
						break;
					case 5:		// Exit
						break;
					default:	// Do nothing
					
				}
			}
			catch (Exception ex) {
				// Display error message and reset options
				if (ex.getMessage() == null) {
					System.out.println("Invalid Input, try again.");
					kb.next();
				}
				else
					System.out.println(ex.getMessage());
				option = 0;
				
			}
		}
		// Display exit message
		System.out.println("Closing application.");

	}
	
	// Writes the list to serializable file
	public static void writeToFile(ArrayList<Person> p) throws IOException {
		ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Person.bin"));
		outStream.writeObject(p);
		outStream.close();
	}
	
	// Reads, deserializes, and displays the data from the file
	public static void readFile() throws IOException, ClassNotFoundException {
		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("Person.bin"));
		
		// Deserialize the list from file
		ArrayList<Person> l = (ArrayList<Person>) inStream.readObject();
		
		// Display information from list
		System.out.println("Displaying all information from file.\n"
				+ "=====================================");
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i).toString());
		}
		
		inStream.close();
	}
	
	// Get the index of the person in the list
	public static int findName(String name, ArrayList<Person> list) {
		for (int i = 0; i < list.size(); i++) {
			if ((name.toLowerCase()).equals((list.get(i).getName()).toLowerCase())) {
				return i;
			}
		}		
		return -1;
	}

}
