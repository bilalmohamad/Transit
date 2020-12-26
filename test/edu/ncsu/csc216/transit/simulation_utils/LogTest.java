package edu.ncsu.csc216.transit.simulation_utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.ncsu.csc216.transit.airport.travelers.FastTrackPassenger;
import edu.ncsu.csc216.transit.airport.travelers.OrdinaryPassenger;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.airport.travelers.TrustedTraveler;

/**
 * Tests the Log class.
 * 
 * @author Bilal Mohamad
 */
public class LogTest {
	
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_1 = new OrdinaryPassenger(20, 100, new Log());
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_2 = new OrdinaryPassenger(30, 120, new Log());
	/** OrdinaryPassenger object used for testing */
	public static final Passenger PASSENGER_3 = new OrdinaryPassenger(40, 140, new Log());
	/** TrustedTraveler object used for testing */
	public static final Passenger PASSENGER_4 = new TrustedTraveler(50, 160, new Log());
	/** FastTrackPassenger object used for testing */
	public static final Passenger PASSENGER_5 = new FastTrackPassenger(60, 180, new Log());
	/** FastTrackPassenger object used for testing */
	public static final Passenger PASSENGER_6 = new FastTrackPassenger(70, 300, new Log());

	
	/**
	 * testLog() will test the methods within the Log class
	 */
	@Test
	public void testLog() {
		Log logTester = new Log();
		
		assertEquals(0, logTester.getNumCompleted());
		
		logTester.logData(PASSENGER_1);
		logTester.logData(PASSENGER_2);
		logTester.logData(PASSENGER_3);
		logTester.logData(PASSENGER_4);
		logTester.logData(PASSENGER_5);
		logTester.logData(PASSENGER_6);
		
		PASSENGER_1.clearSecurity();
		PASSENGER_2.clearSecurity();
		PASSENGER_3.clearSecurity();
		PASSENGER_4.clearSecurity();
		PASSENGER_5.clearSecurity();
		PASSENGER_6.clearSecurity();
		
		double avg = 800.0 / 60.0 / 4.8;
		assertEquals(avg, logTester.averageProcessTime(), 0.001);
	}
		
	/**
	 * testAverageWaitTime() will test the average wait time of all the passengers
	 */
	@Test
	public void testAverageWaitTime() {
		Log logTester = new Log();
		
		assertEquals(0, logTester.getNumCompleted());
		
		logTester.logData(PASSENGER_1);
		logTester.logData(PASSENGER_2);
		logTester.logData(PASSENGER_3);
		logTester.logData(PASSENGER_4);
		logTester.logData(PASSENGER_5);
		logTester.logData(PASSENGER_6);
		
		
		/*double timeWhenAvailable = 0.0;
		timeWhenAvailable += PASSENGER_1.getArrivalTime() + PASSENGER_1.getProcessTime();
		timeWhenAvailable += PASSENGER_2.getArrivalTime() + PASSENGER_2.getProcessTime();
		timeWhenAvailable += PASSENGER_3.getArrivalTime() + PASSENGER_3.getProcessTime();
		timeWhenAvailable += PASSENGER_4.getArrivalTime() + PASSENGER_4.getProcessTime();
		timeWhenAvailable += PASSENGER_5.getArrivalTime() + PASSENGER_5.getProcessTime();
		timeWhenAvailable += PASSENGER_6.getArrivalTime() + PASSENGER_6.getProcessTime();*/
		
//		double avg = timeWhenAvailable / 60.0 / 6.0;
		
		assertEquals(0, logTester.averageWaitTime(), 0.001);
	}
}
