package edu.ncsu.csc216.transit.airport.travelers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.transit.simulation_utils.Log;

/**
 * Tests the PassengerQueue class.
 * 
 * @author Bilal Mohamad
 */
public class PassengerQueueTest {
	
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
	
	
	/**
	 * testPassengerQueue() will test the methods within the PassengerQueue class
	 */
	@Test
	public void testPassengerQueue() {
		
		//Creates a PassengerQueue object used for testing
		PassengerQueue pq = new PassengerQueue();
		
		
		//Checks if the new PassengerQueue is empty and its size is 0
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		
		
		//Adds Passengers to the PassengerQueue
		pq.add(PASSENGER_1);		
		pq.add(PASSENGER_2);
		pq.add(PASSENGER_3);
		pq.add(PASSENGER_4);
		pq.add(PASSENGER_5);
		pq.add(PASSENGER_6);
		
		
		//Checks the size after adding Passengers
		assertEquals(6, pq.size());
		
		
		//Checks what Passenger is at the front of the PassengerQueue, then checks if the passenger was correclty removed
		assertEquals(PASSENGER_1, pq.front());
		assertEquals(PASSENGER_1, pq.remove());
		
		assertEquals(PASSENGER_2, pq.front());
		assertEquals(PASSENGER_2, pq.remove());
		
		assertEquals(PASSENGER_3, pq.front());
		assertEquals(PASSENGER_3, pq.remove());
		
		assertEquals(PASSENGER_4, pq.front());
		assertEquals(PASSENGER_4, pq.remove());
		
		assertEquals(PASSENGER_5, pq.front());
		assertEquals(PASSENGER_5, pq.remove());
		
		assertEquals(PASSENGER_6, pq.front());
		assertEquals(PASSENGER_6, pq.remove());
		
		
		//Checks if the PassengerQueue is empty and its size is 0 after removing all the Passengers
		assertTrue(pq.isEmpty());
		assertEquals(0, pq.size());
		
		
	}

}
