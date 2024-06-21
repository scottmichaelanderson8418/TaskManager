package com.scotttaskmanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

	// What should I do to make task manager accept any list?
	// 2nd Approach
	private static List<Task> tasks = null;

	// New method must be the first method to call...
	// this method takes a list as a TaskManager
	public static void initialize(List<Task> tasks) {

		TaskManager.tasks = tasks;
	}

	// Add Task
	public static void addTask(Scanner scanner) throws Exception {

		if (tasks == null) {
			System.out.println("EXCEPTION");
			throw new Exception(Constants.NULL_TASK_LIST_MESSAGE);
		}

		System.out.println(Constants.ADD_TASK_MESSAGE);

		String text = scanner.nextLine();

		if (text.length() != 0) {

			Task task = new Task(text);

			tasks.add(task);
		}

	}

	// Method to create new file
	public static void createNewFile() {
		try {
			File myObj = new File("MyTasks.txt");

			if (myObj.createNewFile()) {
				// System.out.println("File successfully created :)");
			} else {
				System.out.println("File already Exists");
			}

		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}

	public static void deleteFile() {

		try {
			Path fileToDeletePath = Paths.get("MyTasks.txt");
			Files.delete(fileToDeletePath);

		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}

	public static void readTasksFromFile() throws IOException {

		FileReader fw = null;
		String line = "";
		List<String> strList = new ArrayList<>();
		List<Task> taskList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("MyTasks.txt"))) {

			if (reader != null) {

				while ((line = reader.readLine()) != null) {

					strList.add(line);
				}

			}

			reader.close();
		}
		String completeValue = "";

		for (int i = 0; i < strList.size(); i++) {

			if (strList.get(i).isEmpty()) {
				i++;
			}
			Task task = new Task(line);

			task.setText(strList.get(i++));

			if (strList.get(i++).equals("false")) {

				task.setComplete(false);
			} else {
				task.setComplete(true);
			}

			taskList.add(task);

		}

		for (int i = 0; i < taskList.size(); i++) {

			tasks.add(taskList.get(i));

		}

	}

	public static String readAllLines(BufferedReader reader) throws IOException {
		StringBuilder content = new StringBuilder();
		String line;
		PressEnter.pressEnter();
		while ((line = reader.readLine()) != null) {
			content.append(line);
			content.append(System.lineSeparator());
		}

		PressEnter.pressEnter();

		content.toString();

		PressEnter.pressEnter();

		return content.toString();

	}

	public static void writeTasksToFile() {

		FileWriter fw = null;
		String line = "";

		deleteFile();

		createNewFile();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("MyTasks.txt"))) {

			if (tasks.isEmpty()) {
				return;
			}

			if (writer != null) {

				for (int k = 0; k < tasks.size(); k++) {
					writer.write(tasks.get(k).getText().trim() + "\n");
					writer.write(tasks.get(k).getComplete().toString().trim() + "\n\n");

				}

				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Remove Task
	public static void removeTask(Scanner scanner) throws Exception {

		if (tasks == null)
			throw new Exception(Constants.NULL_TASK_LIST_MESSAGE);

		if (!tasks.isEmpty()) {

			listTasks();

			System.out.println(Constants.REMOVE_TASK_MESSAGE);
			int choice;
			boolean done = false;
			while (!done) {
				try {
					choice = scanner.nextInt();
					choice -= 1;
					tasks.remove(choice);
					done = true;
				} catch (Exception exception) {
					System.out.println(Constants.INVALID_INPUT_MESSAGE);
				}
				scanner.nextLine();
			}
		} else {
			System.out.println("****************************");
			System.out.println("My Tasks List");
			System.out.println("****************************");
			System.out.println(Constants.EMPTY_TASK_LIST_MESSAGE);
			System.out.println("****************************\n");
			pressEnterKeyToContinue();
		}
	}

	// Complete Task...
	public static void completeTask(Scanner scanner) throws Exception {

		String checkA = "";
		if (tasks == null)
			throw new Exception(Constants.NULL_TASK_LIST_MESSAGE);

		if (!tasks.isEmpty()) {

			System.out.println("****************************");
			System.out.println("My Tasks List");
			System.out.println("****************************");

			// display only the list of tasks to be completed...filter out the completed
			// tasks..

			for (int i = 0; i < tasks.size(); i++) {
				// if the task at location "get(i)" is not complete then show in list
				if (tasks.get(i).getComplete() == false) {
					System.out.println((i + 1) + ". " + tasks.get(i).toString());
					checkA = tasks.get(i).toString() + checkA;
				}
			}
			// check if all tasks are completed
			if (checkA.length() == 0) {
				System.out.println("All tasks are Completed.");

				System.out.println("****************************");
				System.out.println();

				pressEnterKeyToContinue();

			} else {

				System.out.println("****************************");
				System.out.println();

				System.out.println(Constants.COMPLETE_TASK_MESSAGE);

				int choice;
				boolean done = false;

				while (!done) {
					try {
						choice = scanner.nextInt();

						verifyChoice(choice);

						for (int i = 0; i < tasks.size(); i++) {
							if ((i + 1) == choice) {
								tasks.get(i).setComplete(true);
							}
						}
						done = true;
					}

					catch (Exception exception) {
						System.out.println(Constants.INVALID_INPUT_MESSAGE);
					}
					scanner.nextLine();
				}
			}
		} else {

			System.out.println("****************************");
			System.out.println("My Tasks List");
			System.out.println("****************************");
			System.out.println(Constants.EMPTY_TASK_LIST_MESSAGE);
			System.out.println("****************************\n");
			pressEnterKeyToContinue();
		}
	}

	// List Tasks with StringBuilder
	public static void listTasks() throws Exception {

		// check if the List of tasks is null and throw Exception
		if (tasks == null)
			throw new Exception(Constants.NULL_TASK_LIST_MESSAGE);

		if (!tasks.isEmpty()) {
			// Create new StringBuilder instance/object
			StringBuilder taskList = new StringBuilder();

			System.out.println("****************************");
			System.out.println("My Tasks List");
			System.out.println("****************************");

			for (int k = 0; k < tasks.size(); k++) {

				taskList.append(k + 1 + ". ");

				taskList.append(tasks.get(k));

				// Press enter to make new line after every task except the last task listed
				if (k != (tasks.size() - 1)) {
					taskList.append("\n");
				}
			}

			// print the StringBuilder taskList to the screen
			System.out.println(taskList.toString());

			System.out.println("****************************\n");
			System.out.println();

		} else {
			System.out.println("****************************");
			System.out.println("My Tasks List");
			System.out.println("****************************");
			System.out.println(Constants.EMPTY_TASK_LIST_MESSAGE);
			System.out.println("****************************\n");
			pressEnterKeyToContinue();

		}

		PressEnter.pressEnter();

	}

	public static void verifyChoice(final int choice) throws Exception {
		// the value for "choice" must be an integer value 1-5
		if (choice <= 0 || choice > tasks.size()) {

			// throws exception if integer value is negative or greater than tasks.size()
			throw new Exception("Constants.INVALID_INPUT_MESSAGE");
		}
	}

	public static void pressEnterKeyToContinue() {
		System.out.println("Press Enter key to return to Main Menu...");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		s.nextLine();
	}

}
