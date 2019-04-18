/* Name: Alyssa Gibson
 * Creation Date: 04.11.2018
 * Completion Date: 04.23.2018
 * CSC161*1 Programming Assignment #2
 * 		Prompt: Write software to organize given employee data.
 */
public abstract class Employee {
	
	String name, address, IDnum, bossID, year;
	
	// constructor
	public Employee (String n, String a, String idn, String bid, String y) {
		name = n;
		address = a;
		IDnum = idn;
		bossID = bid;
		year = y;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getIDnum() {
		return IDnum;
	}
	
	public String getBossID() {
		return bossID;
	}
	
	public String getYear() {
		return year;
	}
	
	public abstract String getGrossWeeklyPay();
		
}
