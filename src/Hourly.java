/* Name: Alyssa Gibson
 * Creation Date: 04.11.2018
 * Completion Date: 04.23.2018
 * CSC161*1 Programming Assignment #2
 * 		Prompt: Write software to organize given employee data.
 */

public class Hourly extends Employee {
	// Hourly Employees get put into this custom object type.
	String title, pay, hours;

	public Hourly(String t, String n, String a, String idn, String bid, String y, String p, String h) {
		// t = title
		// n = name
		// a = address
		// idn = ID number
		// bid = Boss' ID number
		// y = year started
		// p = hourly wage
		// h = hours per week worked
		super(n, a, idn, bid, y);
		title = t;
		pay = p;
		hours = h;
	}
	
	public String getTitle() {
		return title + " Employee";
		// So it reads "Hourly Employee" instead of "Hourly"
	}
	
	@Override
	public String getGrossWeeklyPay() {
		// The gross weekly pay of an hourly employee can be calculated
		//		using the formula:
		//		(pay per hour)*(hours worked per week)
		String result = String.format("%7.2f", Double.parseDouble(pay)*(Double.parseDouble(hours)));
		return result;
		
	}

	@Override
	public String toString() {
		// By overriding toString, I am able to use this function for my own
		//		custom object type, without it spitting out jibberish.
		String result = String.format("%-20s   %6s\t  %7s\t%4s\n", name, IDnum, getGrossWeeklyPay(), year); 
		return result;
		
	}
	
	

}
