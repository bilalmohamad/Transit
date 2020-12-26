/**
 * 
 */
package edu.ncsu.csc216.transit.airport.travelers;

import java.awt.Color;

import edu.ncsu.csc216.transit.airport.TransitGroup;
import edu.ncsu.csc216.transit.airport.security.SecurityArea;
import edu.ncsu.csc216.transit.simulation_utils.Reporter;

/**
 * 	Child class representing passengers in the TrustedTraveler Passenger group
 * 
 * @author Bilal Mohamad
 */
public class TrustedTraveler extends Passenger {
	
	/** The color corresponding to the passenger */
	private Color color;
	
	/** The maximum process time the type of passenger can have */ 
	public static final int MAX_EXPECTED_PROCESS_TIME = 180;
	
	/** The mid-range value indicating whether if the color should be "lighter" */
	public static final int HALFWAY_POINT_IN_RANGE = 100;
	
	/** The Color object for Light Green */
	public static final Color LIGHT_GREEN = new Color(153, 255, 153);
	
	
	/**
	 * The constructor for the FastTrackPassenger class
	 * 
	 * @param arrivalTime	the time it took for the Passenger to arrive
	 * @param processTime	the time it took for the Passenger to be processed
	 * @param myLog			the Reporter object containing the Passenger information
	 */
	public TrustedTraveler (int arrivalTime, int processTime, Reporter myLog) {
		super(arrivalTime, processTime, myLog);
		color = null;
	}
	
	
	/**
	 * Retrieves the color corresponding to the type of Passenger.
	 * 
	 * @return the color corresponding to the type of Passengers
	 */
	public Color getColor() {
		if (getProcessTime() >= MIN_PROCESS_TIME && getProcessTime() < HALFWAY_POINT_IN_RANGE ) {
			color = LIGHT_GREEN;
		}
		if (getProcessTime() >= HALFWAY_POINT_IN_RANGE && getProcessTime() <= MAX_EXPECTED_PROCESS_TIME ) {
			color = Color.GREEN;
		}
		
		return color;
	}
	
	
	/**
	 * Puts the passenger in a line based on their TransitGroup
	 * 
	 * @param group 	the group type of the passenger
	 */
	public void getInLine(TransitGroup group) {
		setLineIndex(pickLine(group));
		SecurityArea security = (SecurityArea) group;
		
		security.addToLine(getLineIndex(), group.nextToGo());
	}
	
	
	/**
	 * Picks the best line for that the type of passenger is allowed to go in
	 * 
	 * @param group		the group type of the passenger
	 * 
	 * @return the line number of the line that was selected
	 */
	private int pickLine(TransitGroup group) {
		SecurityArea security = (SecurityArea) group;
		int shortest;
		if((security.shortestTSAPreLine() * 2) >  security.shortestRegularLine()
				&& security.lengthOfLine(security.shortestTSAPreLine()) > 0) {
			shortest = security.shortestRegularLine();
		}
		else {
			shortest = security.shortestTSAPreLine();
		}
		
		return shortest;
	}

}
