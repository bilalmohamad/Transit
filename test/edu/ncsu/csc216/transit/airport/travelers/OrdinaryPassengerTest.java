package edu.ncsu.csc216.transit.airport.travelers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.security.SecurityArea;
import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the OrdinaryPassenger class.
 * 
 * @author Bilal Mohamad
 */
public class OrdinaryPassengerTest {
	
	/** Creates OrdinaryPassenger objects for testing */
	public static final OrdinaryPassenger PASSENGER_LOW = new OrdinaryPassenger(20, 80, new Log());
	/** Creates OrdinaryPassenger objects for testing */
	public static final OrdinaryPassenger PASSENGER_HIGH = new OrdinaryPassenger(30, 100, new Log());
	/** Creates OrdinaryPassenger objects for testing */
	public static final OrdinaryPassenger PASSENGER_LOWER_BOUND = new OrdinaryPassenger(40, 19, new Log());
	/** Creates OrdinaryPassenger objects for testing */
	public static final OrdinaryPassenger PASSENGER_UPPER_BOUND = new OrdinaryPassenger(50, 151, new Log());
	
	/** The Color object for Light Red */
	public static final Color LIGHT_RED = new Color(255, 153, 153);
	
	
	/**
	 * testOrdinaryPassenger() will test the methods within the OrdinaryPassenger class
	 */
	@Test
	public void testOrdinaryPassenger() {
		
		//Tests the getColor() method in varying processTime ranges
		assertEquals(LIGHT_RED, PASSENGER_LOW.getColor());
		assertEquals(Color.RED, PASSENGER_HIGH.getColor());
		assertNull(PASSENGER_LOWER_BOUND.getColor());
		assertNull(PASSENGER_UPPER_BOUND.getColor());
		
		
		//Tests the getInLine() method\
		SecurityArea security = new SecurityArea(5);

		assertFalse(PASSENGER_LOW.isWaitingInSecurityLine());
		
		PASSENGER_LOW.getInLine(security);
		assertEquals(true, PASSENGER_LOW.isWaitingInSecurityLine());
		
		//assertEquals(3, security.shortestRegularLine());
	}
}
