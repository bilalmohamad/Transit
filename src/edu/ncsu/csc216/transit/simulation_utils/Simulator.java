/**
 * 
 */
package edu.ncsu.csc216.transit.simulation_utils;

import java.awt.Color;

import edu.ncsu.csc216.transit.airport.TransitGroup;
import edu.ncsu.csc216.transit.airport.entrance.PreSecurity;
import edu.ncsu.csc216.transit.airport.entrance.Ticketing;
import edu.ncsu.csc216.transit.airport.security.SecurityArea;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;

/**
 * The Simulator class will run the security simulation, stepping through the acts of each Passenger
 * 
 * @author Bilal Mohamad
 */
public class Simulator {
	
	/** Sum of the percentages of the different types of passengers */
	public static final int SUM = 100;
	
	/** The number of Passengers */
	private int numPassengers;
	
	/** The TransitGroup that represents passengers who have not yet entered a checkpoint security line. */
	private TransitGroup inTicketing;
	
	/** The TransitGroup that represents passengers who are in a checkpoint security line. */
	private TransitGroup inSecurity;
	
	/** The Reporter to record the passenger statistics. */
	private Reporter log;
	
	/** The Passenger object most recently returned by myCalendar.nextToAct(). */
	private Passenger currentPassenger;
	
	/** The EventCalendar object used for obtaining the currentPassenger */
	private EventCalendar myCalendar;
	
	
	
	/**
	 * Constructor for the Simulator class
	 * 
	 * @param checkPoints		the number of checkpoints entered by the user
	 * @param numPassengers		the number of passengers entered by the user
	 * @param trusted			the percentage of trusted passengers entered by the user
	 * @param fastTrack			the percentage of fastTrack passengers entered by the user
	 * @param ordinary			the percentage of ordinary passengers entered by the user
	 * 
	 * @throws IllegalArgumentException if there are less than one passenger
	 */
	public Simulator(int checkPoints, int numPassengers, int trusted, int fastTrack, int ordinary) {
		checkParameters(checkPoints, trusted, fastTrack, ordinary);
		log = new Log();
		if (numPassengers < 1) {
			throw new IllegalArgumentException("There must be at least one passenger.");
		}
		this.numPassengers = numPassengers;

		
		setUp(trusted, fastTrack, ordinary);
		inTicketing = new PreSecurity(numPassengers, log);
		inSecurity = new SecurityArea(checkPoints);
		currentPassenger = null;
		myCalendar = new EventCalendar(inTicketing, inSecurity);
	}
	
	
	/**
	 * Checks the parameters of all the values entered by user to determine if they are valid
	 * 
	 * @param checkPoints		the number of checkpoints entered by the user
	 * @param trusted			the percentage of trusted passengers entered by the user
	 * @param fastTrack			the percentage of fastTrack passengers entered by the user
	 * @param ordinary			the percentage of ordinary passengers entered by the user
	 * 
	 * @throws IllegalArgumentException	if the number of checkpoints is out range
	 * @throws IllegalArgumentException if the percentages for the different types of passengers is less than 0
	 * @throws IllegalArgumentException if the sum of the percentages of the different types of passengers does not equal 100
	 */
	private void checkParameters(int checkPoints, int trusted, int fastTrack, int ordinary) {
		if (checkPoints < 3 || checkPoints > 17) {
			throw new IllegalArgumentException("Number of checkpoints must be at least 3 and at most 17.");
		}
		
		if (trusted < 0 || fastTrack < 0 || ordinary < 0) {
			throw new IllegalArgumentException("There must be at least one passenger.");
		}
		
		if (trusted + fastTrack + ordinary != SUM) {
			throw new IllegalArgumentException("Percents must sum to 100.");
		}
	}
	
	
	/**
	 * Sets up the simulation so that it can be ran based on the values entered by the user
	 * 
	 * @param trusted			the percentage of trusted passengers entered by the user
	 * @param fastTrack			the percentage of fastTrack passengers entered by the user
	 * @param ordinary			the percentage of ordinary passengers entered by the user
	 */
	private void setUp(int trusted, int fastTrack, int ordinary) {
		Ticketing.setDistribution(trusted, fastTrack);
	}
	
	
	/**
	 * Retrieves the Reporter
	 * 
	 * @return the Reporter
	 */
	public Reporter getReporter() {
		return log;
	}
	
	
	/**
	 * Steps through the next act that the Passenger must undergo
	 * 
	 * @throws IllegalArgumentException if the current passenger being observed is null
	 */
	public void step() {
		currentPassenger = myCalendar.nextToAct();
		
		if (currentPassenger == null) {
			throw new IllegalArgumentException();
		}
		
		if (currentPassenger.isWaitingInSecurityLine()) {
			currentPassenger.clearSecurity();
			numPassengers--;
		}
		else {
			currentPassenger.getInLine(inSecurity);
		}
		
		/*
		if (inTicketing.nextToGo().equals(currentPassenger)) {
			currentPassenger = inSecurity.nextToGo();
		}
		else {
			if (inSecurity.nextToGo().equals(currentPassenger)) {
				inSecurity.removeNext();
			}
		}*/
	}
	
	
	/**
	 * Checks if there are more steps that the Passenger must undergo
	 * 
	 * @return	true	if there are remaining steps
	 * 			false	if there are not
	 */
	public boolean moreSteps() {
		return numPassengers != 0;
	}
	
	
	/**
	 * Retreives the current index of the current Passenger
	 * 
	 * @return the current index of the current Passenger
	 */
	public int getCurrentIndex() {
		return currentPassenger.getLineIndex();
	}
	
	
	/**
	 * Retrieves the color of the current Passenger
	 * 
	 * @return the color of the current Passenger
	 */
	public Color getCurrentPassengerColor() {
		return currentPassenger.getColor();
	}
	
	
	/**
	 * Checks if the passenger has fully cleared security
	 * 
	 * @return	true	if the passenger cleared security
	 * 			false	if the passenger has not
	 */
	public boolean passengerClearedSecurity() {
		if (currentPassenger.isWaitingInSecurityLine()) {
			return true;
		}
		return false;
	}

}
