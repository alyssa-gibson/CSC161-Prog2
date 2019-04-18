/* Name: Alyssa Gibson
 * Creation Date: 04.11.2018
 * Completion Date: 04.23.2018
 * CSC161*1 Programming Assignment #2
 * 		Prompt: Write software to organize given employee data.
 */

public class Supervisor extends Salaried {
	// Supervisors get put into this custom object type.
	String bonus;

	public Supervisor(String t, String n, String a, String idn, String bid, String y, String s, String b) {
		super(t, n, a, idn, bid, y, s);
		bonus = b;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String getGrossWeeklyPay() {
		// The gross weekly pay of a supervisor employee can be calculated
		//		with the following formula:
		//		((salary)/52) + ((bonus)/52)
		double retVal = (Double.parseDouble(salary)/52) + (Double.parseDouble(bonus)/52);
		String retValS = new java.text.DecimalFormat("###,###.00").format(retVal);
		return retValS;
	}
	
	@Override
	public String toString() {
		// By overriding toString, I am able to use this function for my own
		//		custom object type, without it spitting out jibberish.
		String result = String.format("%-20s   %6s\t  %7s\t%4s", name, IDnum, getGrossWeeklyPay(), year); 
		return result;
				
	}

}
