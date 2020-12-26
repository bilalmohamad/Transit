package edu.ncsu.csc216.transit.airport.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.travelers.FastTrackPassenger;
import edu.ncsu.csc216.transit.airport.travelers.OrdinaryPassenger;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.airport.travelers.TrustedTraveler;
import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the CheckPoint class.
 * 
 * @author Bilal Mohamad
 */
public class CheckPointTest {
	
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_1 = new OrdinaryPassenger(20, 100, new Log());
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_2 = new OrdinaryPassenger(30, 100, new Log());
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_3 = new OrdinaryPassenger(40, 100, new Log());
	/** TrustedTraveler object used for testing */
	public static final Passenger PASSENGER_4 = new TrustedTraveler(50, 100, new Log());
	/** FastTrackPassenger object used for testing */
	public static final Passenger PASSENGER_5 = new FastTrackPassenger(60, 100, new Log());
	/** FastTrackPassenger object used for testing */
	public static final Passenger PASSENGER_6 = new FastTrackPassenger(70, 100, new Log());
	
	
	
	/**
	 * testCheckPoint() will test the methods within the CheckPoint class
	 */
	@Test
	public void testCheckPoint() {
		CheckPoint cp = new CheckPoint();
		
		//Checks if the CheckPoint is empty
		assertEquals(0, cp.size());
		
		
		//Adds Passengers to the line at the checkpoint
		cp.addToLine(PASSENGER_1);		
		cp.addToLine(PASSENGER_2);
		cp.addToLine(PASSENGER_3);
		cp.addToLine(PASSENGER_4);
		cp.addToLine(PASSENGER_5);
		cp.addToLine(PASSENGER_6);
		
		
		//Checks if the size of the line is correct after adding Passengers
		assertEquals(cp.size(), 6);
		
		
		//Checks if the Passenger exists, is next to go, has a departure time, and is removed from the line
		assertEquals(cp.hasNext(), true);
		assertEquals(cp.nextToGo(), PASSENGER_1);
		assertEquals(cp.departTimeNext(), PASSENGER_1.getWaitTime());
		assertEquals(cp.removeFromLine(), PASSENGER_1);
		
		
		//Checks if the Passenger exists, is next to go, has a departure time, and is removed from the line
		assertEquals(cp.hasNext(), true);
		assertEquals(cp.nextToGo(), PASSENGER_2);
		assertEquals(cp.departTimeNext(), PASSENGER_2.getWaitTime());
		assertEquals(cp.removeFromLine(), PASSENGER_2);
		
		
		//Checks if the Passenger exists, is next to go, has a departure time, and is removed from the line
		assertEquals(cp.hasNext(), true);
		assertEquals(cp.nextToGo(), PASSENGER_3);
		assertEquals(cp.departTimeNext(), PASSENGER_3.getWaitTime());
		assertEquals(cp.removeFromLine(), PASSENGER_3);
		
		
		//Checks if the Passenger exists, is next to go, has a departure time, and is removed from the line
		assertEquals(cp.hasNext(), true);
		assertEquals(cp.nextToGo(), PASSENGER_4);
		assertEquals(cp.departTimeNext(), PASSENGER_4.getWaitTime());
		assertEquals(cp.removeFromLine(), PASSENGER_4);
		
		
		//Checks if the Passenger exists, is next to go, has a departure time, and is removed from the line
		assertEquals(cp.hasNext(), true);
		assertEquals(cp.nextToGo(), PASSENGER_5);
		assertEquals(cp.departTimeNext(), PASSENGER_5.getWaitTime());
		assertEquals(cp.removeFromLine(), PASSENGER_5);
		
		
		//Checks if the Passenger exists, is next to go, has a departure time, and is removed from the line
		assertEquals(cp.hasNext(), true);
		assertEquals(cp.nextToGo(), PASSENGER_6);
		assertEquals(cp.departTimeNext(), PASSENGER_6.getWaitTime());
		assertEquals(cp.removeFromLine(), PASSENGER_6);
		
		
		//Tests the hasNext() and departTimeNext() methods when the CheckPoint is empty
		assertFalse(cp.hasNext());
		assertEquals(Integer.MAX_VALUE, cp.departTimeNext());
		
	}
}
