package edu.ncsu.csc216.transit.airport.entrance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the Ticketing class.
 * 
 * @author Bilal Mohamad
 */
public class TicketingTest {

	
	/**
	 * testTicketing() will test the methods within the Ticketing class
	 */
	@Test
	public void testTicketing() {
		
		Ticketing ticket = null;
		ticket = new Ticketing();
		assertNotNull(ticket);
		
		
		Ticketing.resetFactory();
		Ticketing.setDistribution(10, 10);
		Passenger person1 = Ticketing.generatePassenger(new Log());
		assertNotNull(person1);
		assertTrue(person1.getArrivalTime() <= 15);
		assertTrue(person1.getArrivalTime() >= 1);
		
		Ticketing.resetFactory();
		Ticketing.setDistribution(20, 10);
		Passenger person2 = Ticketing.generatePassenger(new Log());
		assertNotNull(person2);
		assertTrue(person2.getArrivalTime() <= 15);
		assertTrue(person2.getArrivalTime() >= 1);
	}

}