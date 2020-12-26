/**
 * 
 */
package edu.ncsu.csc216.transit.airport.travelers;

import java.awt.Color;

import edu.ncsu.csc216.transit.airport.TransitGroup;
import edu.ncsu.csc216.transit.simulation_utils.Reporter;

/**
 * Abstract parent class for the different types of Passengers such as
 * OrdinaryPassenger, TrustedTraveler, and FastTrackPassenger
 * 
 * @author Bilal Mohamad
 * */
public abstract class Passenger {
	
	/** The minimum time required to process a passenger */
	public static final int MIN_PROCESS_TIME = 20;
	
	/** The time required for the Passenger to arrive */
	private int arrivalTime;
	
	/** The time the Passenger had to wait in the security line */
	private int waitTime;
	
	/** The time it took for the Passenger to be processed */
	private int processTime;
	
	/** The index of line that the Passenger is in */
	private int lineIndex;
	
	/** The boolean value is false until the passenger selects and joins a security line, then it becomes true.*/
	private boolean waitingProcessing;
	
	/** The Reporter object containing the Passenger information to be logged */
	private Reporter myLog;
	
	
	/**
	 * Constructor for the Passenger class
	 * 
	 * @param arrivalTime	the time it took for the Passenger to arrive
	 * @param processTime	the time it took for the Passenger to be processed
	 * @param myLog			the Reporter object containing the Passenger information
	 */
	public Passenger(int arrivalTime, int processTime, Reporter myLog) {
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		this.myLog = myLog;		
		lineIndex = -1;
	}

	/**
	 * Retrieves the arrivalTime
	 * 
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Retrieves the waitTime
	 * 
	 * @return the waitTime
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Sets the waitTime to the specified waitTime if the passenger joins a security line
	 * 
	 * @param waitTime the waitTime to set
	 */
	public void setWaitTime(int waitTime) {
		/*if (waitingProcessing) {
			this.waitTime = waitTime;
		}*/
		
		//TODO Double-check which code works correctly
		this.waitingProcessing = true;
		this.waitTime = waitTime;
	}

	/**
	 * Retrieves the processTime
	 * 
	 * @return the processTime
	 */
	public int getProcessTime() {
		return processTime;
	}

	/**
	 * Retrieves the lineIndex
	 * 
	 * @return the lineIndex
	 */
	public int getLineIndex() {
		return lineIndex;
	}
	
	
	/**
	 * Determines if the Passenger is waiting in the security line or not
	 * 
	 * @return	true	if the Passenger if waiting in the security line
	 * 			false	if the Passenger is not
	 */
	public boolean isWaitingInSecurityLine() {
		waitingProcessing = lineIndex > -1;
		return waitingProcessing;
	}
	
	/**
	 * Clears the security line containing Passengers
	 */
	public void clearSecurity() {
		myLog.logData(this);
	}
	
	/**
	 * Sets the lineIndex to the specified lineIndex
	 * 
	 * @param lineIndex int value to set the lineIndex to
	 */
	protected void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;		
	}
	
	/**
	 * Abstract method that will put the Passenger in line
	 * 
	 * @param group	the Passenger type group
	 */
	abstract public void getInLine(TransitGroup group);
	
	/**
	 * Abstract method that will retrieve the color corresponding to the type of Passenger.
	 * 
	 * @return the color corresponding to the type of Passengers
	 */
	abstract public Color getColor();
	
	
	
	
	

}
