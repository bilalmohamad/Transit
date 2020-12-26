package edu.ncsu.csc216.transit.airport.travelers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.security.SecurityArea;
import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the TrustedTraveler class.
 * 
 * @author Bilal Mohamad
 */
public class TrustedTravelerTest {

	/** Creates TrustedTraveler objects for testing */
	public static final TrustedTraveler PASSENGER_LOW = new TrustedTraveler(20, 80, new Log());
	/** Creates TrustedTraveler objects for testing */
	public static final TrustedTraveler PASSENGER_HIGH = new TrustedTraveler(30, 120, new Log());
	/** Creates TrustedTraveler objects for testing */
	public static final TrustedTraveler PASSENGER_LOWER_BOUND = new TrustedTraveler(40, 19, new Log());
	/** Creates TrustedTraveler objects for testing */
	public static final TrustedTraveler PASSENGER_UPPER_BOUND = new TrustedTraveler(50, 181, new Log());
	
	/** The Color object for Light Green */
	public static final Color LIGHT_GREEN = new Color(153, 255, 153);
	
	
	/**
	 * testTrustedTraveler() will test the methods within the TrustedTraveler class
	 */
	@Test
	public void testTrustedTraveler() {
		
		//Tests the getColor() method in varying processTime ranges
		assertEquals(LIGHT_GREEN, PASSENGER_LOW.getColor());
		assertEquals(Color.GREEN, PASSENGER_HIGH.getColor());
		assertNull(PASSENGER_LOWER_BOUND.getColor());
		assertNull(PASSENGER_UPPER_BOUND.getColor());
		
		
		//Tests the getInLine() method\
		SecurityArea security = new SecurityArea(5);

		assertFalse(PASSENGER_LOW.isWaitingInSecurityLine());
		
		PASSENGER_LOW.getInLine(security);
		assertEquals(true, PASSENGER_LOW.isWaitingInSecurityLine());
	}
}
