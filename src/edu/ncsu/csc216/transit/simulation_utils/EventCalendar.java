/**
 * 
 */
package edu.ncsu.csc216.transit.simulation_utils;

import edu.ncsu.csc216.transit.airport.TransitGroup;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;

/**
 * The EventCalendar class determines which Passenger is the next to act
 * 
 * @author Bilal Mohamad
 */
public class EventCalendar {

	private TransitGroup highPriority;
	
	private TransitGroup lowPriority;
	/**
	 * Constructor for the EventCalendar class
	 * 
	 * @param highPriority		the group type of the first Passenger
	 * @param lowPriority		the group type of the next Passenger
	 */
	public EventCalendar(TransitGroup highPriority, TransitGroup lowPriority) {
		this.highPriority = highPriority;
		this.lowPriority = lowPriority;
	}
	
	/**
	 * Determines which Passenger is the next to act
	 * 
	 * @throws IllegalArgumentException if both Passengers of priority are null
	 * 
	 * @return the Passenger that is next to act
	 */
	public Passenger nextToAct() {
		Passenger person1 = highPriority.nextToGo();
		Passenger person2 = lowPriority.nextToGo();
		
		if (person1 == null && person2 == null) {
			throw new IllegalArgumentException("Both Passengers are null!");
		}
		else {
			if (lowPriority.departTimeNext() < highPriority.departTimeNext()) {
				return lowPriority.removeNext();
			}
			
			else if (lowPriority.departTimeNext() > highPriority.departTimeNext()) {
				return highPriority.removeNext();
			}
			
			else if(lowPriority.departTimeNext() == highPriority.departTimeNext() && person1.getLineIndex() > person2.getLineIndex()) {
				return lowPriority.removeNext();
			}
		}
		
		return highPriority.removeNext();
	}
}
