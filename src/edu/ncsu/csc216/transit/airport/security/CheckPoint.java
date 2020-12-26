/**
 * 
 */
package edu.ncsu.csc216.transit.airport.security;

import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.airport.travelers.PassengerQueue;

/**
 * The class representing a security checkpoint and its corresponding waiting line.
 * 
 * @author Bilal Mohamad
 *
 */
public class CheckPoint {
	
	/** The time that the last passenger in line will clear security and leave the line. */
	private int timeWhenAvailable;
	
	private PassengerQueue line;
	
	/**
	 * Constructor for the CheckPoint class
	 */
	public CheckPoint() {
		this.line = new PassengerQueue();
		timeWhenAvailable = 0;
	}
	
	/**
	 * Determines the size of the line
	 * 
	 * @return the size of the line
	 */
	public int size() {
		return line.size();
	}
	
	
	/**
	 * Removes a Passenger from the line
	 * 
	 * @return the Passenger removed from the line
	 */
	public Passenger removeFromLine() {
		return line.remove();
	}
	
	
	/**
	 * Determines if the checkpoint has following value
	 * 
	 * @return	true	if there is a value next
	 * 			false	if there was not a value next
	 */
	public boolean hasNext() {
		if (line.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Determines the next departure time
	 * 
	 * @return the sequential departure time
	 */
	public int departTimeNext() {
		if (line.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		else {
			return line.front().getWaitTime();
		}
	}
	
	
	/**
	 * Determines the next Passenger to go
	 * 
	 * @return the next Passenger to go
	 */
	public Passenger nextToGo() {
		return line.front();
	}
	
	/**
	 * Adds a Passenger to a line
	 * 
	 * @param person	Passenger object being added to the line
	 */
	public void addToLine(Passenger person) {
		if(person != null) {
			timeWhenAvailable += person.getArrivalTime() + person.getProcessTime();
			person.setWaitTime(timeWhenAvailable);
			line.add(person);
		}
	}
}
