package edu.ncsu.csc216.transit.simulation_utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.Test;

/**
 * Tests the Log class.
 * 
 * @author Bilal Mohamad
 */
public class SimulatorTest {
	
	/** The Color object for Light Blue */
	public static final Color LIGHT_BLUE = new Color(153, 153, 255);
	
	
	/**
	 * testSimulator() will test the methods within the Simulator class
	 */
	@Test
	public void testSimulator() {
		Simulator simTest = new Simulator(10, 100, 33, 33, 34);
		
		assertNotNull(simTest);
		simTest.step();
		
		
		assertTrue(simTest.getCurrentIndex() >= 0);				//ERROR
		assertNotNull(simTest.getCurrentPassengerColor());		//ERROR
		assertTrue(simTest.passengerClearedSecurity());
		assertTrue(simTest.moreSteps());
		
		
		
		
	}


	/**
	 * testSimulator() will test the methods within the Simulator class that will be invalid
	 */
	@Test
	public void testInvalidSimulator() {
		
		Simulator simTest = new Simulator(10, 100, 33, 33, 34);
		assertNotNull(simTest);
		
		@SuppressWarnings("unused")
		Simulator simInvalidTestCheckpoints1;
		@SuppressWarnings("unused")
		Simulator simInvalidTestCheckpoints2;
		@SuppressWarnings("unused")
		Simulator simInvalidTestPassengers;
		@SuppressWarnings("unused")
		Simulator simInvalidTestSum;
		@SuppressWarnings("unused")
		Simulator simInvalidPercentage;
		
		try {
			simInvalidTestCheckpoints1 = new Simulator(2, 100, 33, 33, 34);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Number of checkpoints must be at least 3 and at most 17.", e.getMessage());
		}
		
		
		try {
			simInvalidTestCheckpoints2 = new Simulator(18, 100, 33, 33, 34);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Number of checkpoints must be at least 3 and at most 17.", e.getMessage());
		}
		
		
		try {
			simInvalidTestPassengers = new Simulator(15, 0, 33, 33, 34);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("There must be at least one passenger.", e.getMessage());
		}
		
		
		try {
			simInvalidTestSum = new Simulator(12, 50, 30, 33, 34);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("Percents must sum to 100.", e.getMessage());
		}
		
		
		try {
			simInvalidTestSum = new Simulator(12, 50, 30, 33, -1);
			fail();
		}
		catch (IllegalArgumentException e) {
			assertEquals("There must be at least one passenger.", e.getMessage());
		}	
	}

}
