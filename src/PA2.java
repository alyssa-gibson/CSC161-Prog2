/* Name: Alyssa Gibson
 * Creation Date: 04.11.2018
 * Completion Date: 04.23.2018
 * CSC161*1 Programming Assignment #2
 * 		Prompt: Write software to organize given employee data.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PA2 {
	// PA2 = Programming Assignment 2

	public static void main(String[] args) throws FileNotFoundException {
		// Main method reads the file then calls a MainProgram method
		// that allows for the main program itself to loop until input
		// X is given.
		// ----Read file begin----
		
		ArrayList<Hourly> hou = new ArrayList<Hourly>();
		ArrayList<Salaried> sal = new ArrayList<Salaried>();
		ArrayList<Supervisor> sup = new ArrayList<Supervisor>();
		File file = new File("employeeData.txt");
		Scanner inputFile = new Scanner(file);
		String info = "";
		String[] arr = null;
		int i = 0;
		while (inputFile.hasNext()) {
			info = inputFile.nextLine();
			arr = info.split(";");
			i = 0;
			ArrayList<String> temp = new ArrayList<String>();
			for(i = 0; i < arr.length; i++) {
				if (arr[i].equals("Hourly") || arr[i].equals("Salaried") || arr[i].equals("Supervisor")) {
					temp.clear();
					temp.add(arr[i]);
				}
				
				else {
					temp.add(arr[i]);
				}
				
			}
			
			if (temp.indexOf("Hourly") == 0) {
				hou.add(MakeHourly(temp));
			}
			
			else if (temp.indexOf("Salaried") == 0) {
				sal.add(MakeSalaried(temp));
			}
			
			else if (temp.indexOf("Supervisor") == 0) {
				sup.add(MakeSupervisor(temp));
			}
		
		}
		
		inputFile.close();
		// -----Read file finished----
		// Main Program can now start.
		MainProgram(hou, sal, sup);
	}
	
	public static void MainProgram(ArrayList<Hourly> hou, ArrayList<Salaried> sal, ArrayList<Supervisor> sup) {
		// Takes user input and tells the program what functions to call based on initial
		// user input.
		Scanner kb = new Scanner(System.in);
		String uin = " ";
		// uin = user input, used to control the sorting methods that 
		//		the program will use to bring up employees
		while (uin.charAt(0) != 'A' && uin.charAt(0) != 'B' && uin.charAt(0) != 'X') {
			System.out.println("\n\tEmployee Lookup Program");
			System.out.println("A) Find all employees with a given title");
			System.out.println("B) Find a single employee");
			System.out.println("X) Exit the program:");
			System.out.println("\tEnter your choice:");
			uin = kb.next();
			uin = uin.toUpperCase();
			// Allows for user input to understand "a" as Choice A
			//		and likewise for B and X.
				if (uin.charAt(0) != 'A' && uin.charAt(0) != 'B' && uin.charAt(0) != 'X') {
					System.out.println("\nPlease input one of the following options:");
					
				}
		}
		
		if (uin.charAt(0) == 'A') {
			TitleSearch(hou, sal, sup);
		}
		
		else if (uin.charAt(0) == 'B') {
			IDSearch(hou, sal, sup);
		}
		
		else if (uin.charAt(0) == 'X') {
			System.exit(0);
		}
		
	}
	
	public static void TitleSearch(ArrayList<Hourly> h, ArrayList<Salaried> s, ArrayList<Supervisor> su) {
		// This method searches given a title-- a.k.a choice A
		// then displays them.
		Scanner kb = new Scanner(System.in);
		int uin = 0;
		// initialize user input as 0 so that the while loop
		//		can correctly begin.
		while (uin != 1 && uin != 2 && uin != 3) {
			System.out.println("\tTitle Lookup");
			System.out.println("Show employees with which given title?");
			System.out.println("1) Hourly Employee");
			System.out.println("2) Salaried");
			System.out.println("3) Supervisor");
			System.out.println("\tEnter 1, 2, or 3:");
			uin = kb.nextInt();
				if (uin != 1 && uin != 2 && uin != 3) {
					System.out.println("\nPlease input one of the following options:\n");
				}
				// Input 1 = user wants to display hourly employees-
				else if (uin == 1) {
					System.out.println("Name" + "\t\t\tID" + "\tGross Weekly" + "\tEmployed" + "\n\t\t\t\t   Pay\t\tSince\n");
					for (int i = 0; i < h.size(); i++) {
						System.out.print(h.get(i).toString());
					}
				}// Hourly display finish
				// Input 2 = user wants to display salaried employees-
				else if (uin == 2) {
					System.out.println("Name" + "\t\t\tID" + "\tGross Weekly" + "\tEmployed" + "\n\t\t\t\t   Pay\t\tSince\n");
					for (int i = 0; i < s.size(); i++) {
						System.out.println(s.get(i).toString());
					}	
				}// Salaried display finish
				// Input 3 = user wants to display supervisor employees-
				else if (uin == 3) {
					System.out.println("Name" + "\t\t\tID" + "\tGross Weekly" + "\tEmployed" + "   Direct Reports" +"\n\t\t\t\t   Pay\t\tSince\n");
					for (int i = 0; i < su.size(); i++) {
						System.out.println(su.get(i).toString() + "\t  " + FindDirectReports(su.get(i), su, h, s));
					}
				}// Supervisor display finish
		}
		MainProgram(h, s, su);
	}
	
	public static void IDSearch(ArrayList<Hourly> h, ArrayList<Salaried> s, ArrayList<Supervisor> su) {
		// This method searches for a single employee given an ID-
		//		a.k.a. choice B
		Scanner kb = new Scanner(System.in);
		boolean flag = false;
		String uin = "";
		System.out.println("Enter the ID of the employee:");
		uin = kb.next();
		// takes in the user input
		// Search supervisor employees-
		for (int i = 0; i < su.size(); i++) {
			if (su.get(i).IDnum.equals(uin)) {
				System.out.println("  " + su.get(i).getName() + "  " 
						+ su.get(i).getIDnum() + " " + su.get(i).getTitle()
						+ ", employed since " + su.get(i).getYear());
				flag = true;
			}
		}// Supervisor search finish
		// Search salaried employees-
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i).IDnum.equals(uin)) {
				System.out.println("  " + s.get(i).getName() + "  " 
						+ s.get(i).getIDnum() + " " + s.get(i).getTitle()
						+ ", employed since " + s.get(i).getYear());
				flag = true;
			}
		}// Search salaried finish
		// Search hourly employees-
		for (int i = 0; i < h.size(); i++) {
			if (h.get(i).IDnum.equals(uin)) {
				System.out.println("  " + h.get(i).getName() + "  " 
						+ h.get(i).getIDnum() + " " + h.get(i).getTitle()
						+ ", employed since " + h.get(i).getYear());
				flag = true;
			}
		}// Search hourly finish
		
		if (flag == false) {
			System.out.println("Sorry, no employee with ID " + uin + " was found.");
		}
		
		MainProgram(h, s, su);
	}
	
	public static String FindDirectReports(Supervisor su, ArrayList<Supervisor> sup, ArrayList<Hourly> h, ArrayList<Salaried> s) {
		// This function serves to find and print the direct reports
		//		that each supervisor manages.
		// Search Hourly employees--
		String dr = "";
		// dr = direct reports.
		// String dr will be built as the for loops below run
		// 		then be returned at the end for each Supervisor su.
		for (int i = 0; i < h.size(); i++) {
			String b = h.get(i).bossID;
			if (b.equals(su.getIDnum())) {
				dr = dr + " " + h.get(i).IDnum;
			}
		} // Hourly search finish
		// Search Salaried employees--
		for (int i = 0; i < s.size(); i++) {
			String b = s.get(i).bossID;
			if (b.equals(su.getIDnum())) {
				dr = dr + " " + s.get(i).IDnum;
			}
		} // Salaried search finish
		// Search Supervisor IDs- to provide info to Jo Mellor.
		for (int i = 0; i < sup.size(); i++) {
			String b = sup.get(i).bossID;
			if (b.equals(su.getIDnum())) {
				dr = dr + " " + sup.get(i).IDnum;
			}
		} // Supervisor search finish
		
		return dr;
	}

	public static Hourly MakeHourly (ArrayList<String> pass) {
		// Changes an ArrayList String to ArrayList Hourly
		Hourly h = new Hourly(pass.get(0), pass.get(1), pass.get(2), pass.get(3), pass.get(4), pass.get(5), pass.get(6), pass.get(7));
		return h;
	}
	
	public static Salaried MakeSalaried (ArrayList<String> pass) {
		// Changes an ArrayList String to ArrayList Salaried
		Salaried s = new Salaried(pass.get(0), pass.get(1), pass.get(2), pass.get(3), pass.get(4), pass.get(5), pass.get(6));
		return s;
	}
	
	public static Supervisor MakeSupervisor (ArrayList<String> pass) {
		// Changes an ArrayList String to ArrayList Supervisor
		Supervisor s = new Supervisor(pass.get(0), pass.get(1), pass.get(2), pass.get(3), pass.get(4), pass.get(5), pass.get(6), pass.get(7));
		return s;
	}
	

}
