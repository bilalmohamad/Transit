/**
 * 
 */
package edu.ncsu.csc216.transit.airport.entrance;

import edu.ncsu.csc216.transit.airport.TransitGroup;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;
import edu.ncsu.csc216.transit.airport.travelers.PassengerQueue;
import edu.ncsu.csc216.transit.simulation_utils.Reporter;

/**
 * This class represents the passengers that have not yet entered the checkpoint lines.
 * 
 * @author Bilal Mohamad
 */
public class PreSecurity implements TransitGroup {
	
	
	private PassengerQueue outsideSecurity;
	
	/**
	 * Constructor for the PreSecurity class
	 * 
	 * @param numOfPassengers		int value for the time
	 * @param log					Reporter object containing passenger group activity
	 * 
	 * @throws IllegalArgumentException	if the number of passengers is less than one
	 */
	public PreSecurity(int numOfPassengers, Reporter log) {
		
		if (numOfPassengers < 1) {
			throw new IllegalArgumentException("There must be at least one passenger.");
		}
		
		outsideSecurity = new PassengerQueue();
		
		for (int i = 0; i < numOfPassengers; i++) {
			
			Passenger person = Ticketing.generatePassenger(log);
			outsideSecurity.add(person);
		}	
	}
	
	
	/**
	 * Determines the next departure time.
	 * 
	 * @return time required for the next departure
	 */
	public int departTimeNext() {
		if (outsideSecurity.isEmpty()) {
			return Integer.MAX_VALUE;
		}
		return outsideSecurity.front().getArrivalTime();
	}
	
	
	/**
	 * Determines the next passenger to leave Pre-Security
	 * 
	 * @return next Passenger to go
	 */
	public Passenger nextToGo() {
		return outsideSecurity.front();
	}
	
	
	/**
	 * Determines if there contains another value in what is being viewed
	 *  
	 * @return	true	if it has something next
	 * 			false	if it does not have something next
	 */
	public boolean hasNext() {
		if (outsideSecurity.isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Removes the next Passenger
	 * 
	 * @return the passenger that was removed
	 */
	public Passenger removeNext() {
		if (outsideSecurity.isEmpty()) {
			return null;
		}
		return outsideSecurity.remove();
	}

}
