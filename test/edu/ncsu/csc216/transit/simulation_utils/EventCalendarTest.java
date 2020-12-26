package edu.ncsu.csc216.transit.simulation_utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.entrance.PreSecurity;

/**
 * Tests the EventCalendar class.
 * 
 * @author Bilal Mohamad
 */
public class EventCalendarTest {

	
	/**
	 * testEventCalendar() will test the methods within the EventCalendar class
	 */
	@Test
	public void testEventCalendar() {
		
		PreSecurity highPriority = new PreSecurity(2, new Log());
		PreSecurity lowPriority = new PreSecurity(2, new Log());
		
		EventCalendar ec = new EventCalendar(highPriority, lowPriority);
				
		assertEquals(highPriority.nextToGo(), ec.nextToAct());
		
		assertEquals(highPriority.nextToGo(), ec.nextToAct());
		
		assertEquals(lowPriority.nextToGo(), ec.nextToAct());
		
		assertEquals(lowPriority.nextToGo(), ec.nextToAct());
		
		try {
			ec.nextToAct();
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Both Passengers are null!", e.getMessage());
		}
		
		
		
	}
}
