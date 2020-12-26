package edu.ncsu.csc216.transit.airport.entrance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.simulation_utils.Log;
/**
 * Tests the PreSecurity class.
 * 
 * @author Bilal Mohamad
 */
public class PreSecurityTest {
	
	/** Number of Passengers used for testing */
	public static final int NUMBER_OF_PASSENGERS = 100;

	/**
	 * testPreSecurity() will test the methods within the PreSecurity class
	 */
	@Test
	public void testPreSecurity() {
		Log log = new Log();
		PreSecurity preSec = new PreSecurity(NUMBER_OF_PASSENGERS, log);
		
		
		//Attempts to check if the each of the passengers exist and their departureTime;
		//afterwards, they are removed
		for (int i = 0; i < NUMBER_OF_PASSENGERS; i++) {
			assertEquals(preSec.hasNext(), true);
			
			Passenger person = preSec.nextToGo();
			assertNotNull(person);
			assertEquals(preSec.departTimeNext(), person.getArrivalTime());
			assertNotNull(preSec.removeNext());
		}
		
		//Checks if the methods function properly with an emtpy PreSecurity
		assertEquals(preSec.departTimeNext(), Integer.MAX_VALUE);
		assertFalse(preSec.hasNext());
		assertEquals(preSec.nextToGo(), null);
		assertEquals(preSec.removeNext(), null);
		
		
		//Checks the throws in the constructor by setting the numOfPassengers to a value less than 0
		try {
			@SuppressWarnings("unused")
			PreSecurity invalidPS = new PreSecurity(0, log);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("There must be at least one passenger.", e.getMessage());
		}
	}
}
