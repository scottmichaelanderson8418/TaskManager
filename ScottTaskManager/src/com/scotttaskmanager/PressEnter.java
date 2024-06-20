/**
 * 
 */
package com.scotttaskmanager;

import java.util.Scanner;

/**
 * 
 */
public class PressEnter {

	private static final Scanner scanner = new Scanner(System.in);

	public static void pressEnter() {
		System.out.println("*** Press Enter ***");
		scanner.nextLine();
	}

}
