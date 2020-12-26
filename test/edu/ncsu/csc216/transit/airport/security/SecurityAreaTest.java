package edu.ncsu.csc216.transit.airport.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.travelers.FastTrackPassenger;
import edu.ncsu.csc216.transit.airport.travelers.OrdinaryPassenger;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.airport.travelers.TrustedTraveler;
import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the SecurityArea class.
 * 
 * @author Bilal Mohamad
 */
public class SecurityAreaTest {
	
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_1 = new OrdinaryPassenger(20, 100, new Log());
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_2 = new OrdinaryPassenger(30, 120, new Log());
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_3 = new OrdinaryPassenger(40, 140, new Log());
	/** TrustedPassenger object used for testing */
	public static final Passenger PASSENGER_4 = new TrustedTraveler(50, 160, new Log());
	/** FastTrackPassenger object used for testing */
	public static final Passenger PASSENGER_5 = new FastTrackPassenger(60, 180, new Log());
	/** FastTrackPassenger object used for testing */
	public static final Passenger PASSENGER_6 = new FastTrackPassenger(70, 200, new Log());
	
	/** The string to display when the number of checkpoints is out of range */
	public static final String ERROR_CHECKPOINTS = "Number of checkpoints must be at least 3 and at most 17.";
	
	/** The string to display when the index is creates an error */
	public static final String ERROR_INDEX = "Index out of range for this security area";
	
	
	/**
	 * testSecurityArea() will test the methods within the SecurityArea class
	 */
	@Test
	public void testSecurityArea() {
		SecurityArea sa = new SecurityArea(5);
		
		
		//Checks if the length of each of the blank lines is empty
		assertEquals(0, sa.lengthOfLine(0));
		assertEquals(0, sa.lengthOfLine(1));
		assertEquals(0, sa.lengthOfLine(2));
		assertEquals(0, sa.lengthOfLine(3));
		assertEquals(0, sa.lengthOfLine(4));
		
		
		//Tests nextToGo() while all the lines are empty
		assertEquals(sa.nextToGo(), null);
		
		
		//Tests removeNext() while the all the lines are empty
		assertEquals(sa.removeNext(), null);
		
		
		//Tests each of the shortestXXLine methods to see that they select the correct line when they are all empty
		assertEquals(2, sa.shortestRegularLine());
		assertEquals(0, sa.shortestFastTrackLine());
		assertEquals(4, sa.shortestTSAPreLine());
		
		
		//Tests shortestRegualarLine()
		sa.addToLine(2, PASSENGER_1);
		assertEquals(3, sa.shortestRegularLine());
		
		
		//Tests shortestFastTrackLine()
		sa.addToLine(0, PASSENGER_5);
		assertEquals(1, sa.shortestFastTrackLine());
		
		sa.addToLine(1, PASSENGER_6);
		assertEquals(3, sa.shortestFastTrackLine());
		
		
		//Tests shortestTSAPreLine()
		sa.addToLine(4, PASSENGER_4);
		assertEquals(3, sa.shortestTSAPreLine());
		
		
		//Adds remaining passengers
		sa.addToLine(sa.shortestRegularLine(), PASSENGER_2);
		assertEquals(2, sa.shortestRegularLine());
		sa.addToLine(sa.shortestRegularLine(), PASSENGER_3);
		assertEquals(3, sa.shortestRegularLine());
		
		
		
		//Tests departTimeNext() and nextToGo()
		assertEquals(sa.nextToGo().getWaitTime(), sa.departTimeNext());
		assertEquals(sa.nextToGo().getWaitTime(), sa.departTimeNext());
		
		
		//Test removeNext()
		assertEquals(sa.nextToGo(), sa.removeNext());
		assertEquals(sa.nextToGo(), sa.removeNext());
		
		
		//Tests an invalid number of gates (Lower limit of range)
		try {
			@SuppressWarnings("unused")
			SecurityArea invalidSA = new SecurityArea(2);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			assertEquals(ERROR_CHECKPOINTS, e.getMessage());
		}
		
		
		//Tests an invalid number of gates (Upper limit of range)
		try {
			@SuppressWarnings("unused")
			SecurityArea invalidSA = new SecurityArea(18);
			fail();
		}
		catch (IndexOutOfBoundsException e) {
			assertEquals(ERROR_CHECKPOINTS, e.getMessage());
		}
		
		
		//Tests an invalid index of a line
		try {
			sa.lengthOfLine(-1);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals(ERROR_INDEX, e.getMessage());
		}
		
		
		//Tests adding a Passenger to an invalid index number
		try {
			sa.addToLine(-1, PASSENGER_1);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals(ERROR_INDEX, e.getMessage());
		}		
	}
}
