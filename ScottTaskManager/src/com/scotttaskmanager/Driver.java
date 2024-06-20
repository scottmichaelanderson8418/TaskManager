package com.scotttaskmanager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Driver {

	// Driver....main....
	public static void main(String args[]) throws Exception {

		// The Driver will inject the ArrayList type into the TaskManager class
		TaskManager.initialize(new ArrayList<Task>());
		TaskManager.readTasksFromFile();

		// Step 1 Create Scanner object and declare boolean "done" = false

		// Create new scanner instance/object
		Scanner scanner = new Scanner(System.in);

		// Declare boolean variable
		boolean done = false;

		// Step 2
		// while loop will display main menu until user chooses to quit
		while (!done) {

			// Step 3
			// scanner object is injected into the displayMenu method
			// The displayMenu is printed to the screen and the user is asked to make a
			// selection
			Integer choice = displayMenu(scanner);

			// Step 4
			// executeAction method passes scanner object & Integer choice
			done = executeAction(scanner, choice);
		}

		// Step 5
		// system prints out "Goodbye" to the screen and closes the scanner
		System.out.println("Goodbye !!!");

		scanner.close();
	}

	public static int displayMenu(final Scanner scanner) throws Exception {

		boolean booleanA = true;
		int choice = -1;

		// Display mainMenu to the screen
		List<String> mainMenu = new ArrayList<String>();

		mainMenu.add("****************************");
		mainMenu.add("Main Menu");
		mainMenu.add("****************************");
		mainMenu.add("1. Add a task");
		mainMenu.add("2. Remove a task");
		mainMenu.add("3. Mark a task complete");
		mainMenu.add("4. List the tasks");
		mainMenu.add("5. Exit Task Manager");
		mainMenu.add("****************************");

		for (int i = 0; i < mainMenu.size(); i++) {
			System.out.println(mainMenu.get(i));
		}

		// run this while loop until a valid "choice" is received from the user
		while (booleanA) {

			try {

				// Ask user for method to execute
				System.out.println("What would you like to do today? ");

				// Store the user input into int variable ""choice"
				choice = scanner.nextInt();

				scanner.nextLine();

				verifyChoice(choice);

				booleanA = false;

				// insert blank line
				System.out.println();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input....Enter positive integer value 1-5");
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return choice;
	}

	// verifyChoice method checks that the integer value for int variable "choice"
	// is a positive
	// integer between 1-5.
	public static void verifyChoice(final int choice) throws Exception {
		// the value for "choice" must be an integer value 1-5
		if (choice <= 0 || choice > 5) {

			// throws exception if integer value is negative or
			throw new Exception("Invalid input....Enter positive integer value between 1-5");
		}
	}

	// executeAction method will
	public static Boolean executeAction(final Scanner scanner, int choice) throws Exception {

		boolean done = false;

		if (choice == 1) {
			// Add task method in Task Manager.

			TaskManager.addTask(scanner);

		} else if (choice == 2) {
			// Call the remove task method in Task Manager.
			TaskManager.removeTask(scanner);
		} else if (choice == 3) {
			// Call the complete task method in Task Manager.
			TaskManager.completeTask(scanner);
		} else if (choice == 4) {
			// TODO: call the list tasks method in Task Manager.
			TaskManager.listTasks();

		} else if (choice == 5) {

			TaskManager.writeTasksToFile();

			done = true;
		}

		else {
			System.out.println("Invalid choice...please try again");
		}
		return done;
	}
}
