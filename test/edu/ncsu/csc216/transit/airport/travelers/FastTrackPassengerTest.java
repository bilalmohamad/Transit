package edu.ncsu.csc216.transit.airport.travelers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.security.SecurityArea;
import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the FastTrackPassenger class.
 * 
 * @author Bilal Mohamad
 */
public class FastTrackPassengerTest {

	/** Creates FastTrackPassenger objects for testing */
	public static final FastTrackPassenger PASSENGER_LOW = new FastTrackPassenger(20, 80, new Log());
	/** Creates FastTrackPassenger objects for testing */
	public static final FastTrackPassenger PASSENGER_HIGH = new FastTrackPassenger(30, 200, new Log());
	/** Creates FastTrackPassenger objects for testing */
	public static final FastTrackPassenger PASSENGER_LOWER_BOUND = new FastTrackPassenger(40, 19, new Log());
	/** Creates FastTrackPassenger objects for testing */
	public static final FastTrackPassenger PASSENGER_UPPER_BOUND = new FastTrackPassenger(50, 331, new Log());
	
	/** The Color object for Light Blue */
	public static final Color LIGHT_BLUE = new Color(153, 153, 255);
	
	
	/**
	 * testFastTrackPassenger() will test the methods within the FastTrackPassenger class
	 */
	@Test
	public void testFastTrackPassenger() {
		
		//Tests the getColor() method in varying processTime ranges
		assertEquals(LIGHT_BLUE, PASSENGER_LOW.getColor());
		assertEquals(Color.BLUE, PASSENGER_HIGH.getColor());
		assertNull(PASSENGER_LOWER_BOUND.getColor());
		assertNull(PASSENGER_UPPER_BOUND.getColor());
		
		
		//Tests the getInLine() method\
		SecurityArea security = new SecurityArea(5);

		assertFalse(PASSENGER_LOW.isWaitingInSecurityLine());
		
		PASSENGER_LOW.getInLine(security);
		assertEquals(true, PASSENGER_LOW.isWaitingInSecurityLine());
	}
}
