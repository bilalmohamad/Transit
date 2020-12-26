/**
 * 
 */
package edu.ncsu.csc216.transit.airport.security;

import java.util.ArrayList;

import edu.ncsu.csc216.transit.airport.TransitGroup;
import edu.ncsu.csc216.transit.airport.travelers.Passenger;

/**
 * The class representing the entire TSA security area, including all of the checkpoints and their lines.
 * 
 * @author Bilal Mohamad
 */
public class SecurityArea implements TransitGroup {
	
	/** The value for the max number of checkpoints */
	public static final int MAX_CHECKPOINTS = 17;
	
	/** The value for the min number of checkpoints */
	public static final int MIN_CHECKPOINTS = 3;
	
	/** The string to display when the number of checkpoints is out of range */
	public static final String ERROR_CHECKPOINTS = "Number of checkpoints must be at least 3 and at most 17.";
	
	/** The string to display when the index is creates an error */
	public static final String ERROR_INDEX = "Index out of range for this security area";
	
	/** The largest index of the Fast Track Passenger area */
	private int largestFastIndex;
	
	/** The TSA PreCheck index number */
	private int tsaPreIndex;
	
	private ArrayList <CheckPoint> check;
	
	 /**
	  * Constructs SecurityArea objects using an int for the number of gates there are
	  * 
	  * @param checkpoints	int value for the number of checkpoint gates
	  * 
	  * @throws IndexOutOfBoundsException	if the number of checkpoints is out of range
	  */
	public SecurityArea(int checkpoints) {
		check = new ArrayList<CheckPoint>();
		
		if (numGatesOK(checkpoints)) {
			for (int i = 0; i < checkpoints; i++) {
				check.add(new CheckPoint());
			}
			
			tsaPreIndex = checkpoints - 1;
			largestFastIndex = (int) Math.ceil(checkpoints / (double) 3.0);
		}
		else {
			throw new IndexOutOfBoundsException(ERROR_CHECKPOINTS);
		}
	}
	
	/**
	 * Determines if the number of checkpoint gates is within the valid range
	 * 
	 * @param gates		int value for the number of checkpoint gates
	 * 
	 * @return	true	if gates is within the range of 3-17
	 * 			false	if the gates is not within the range of 3-17
	 */
	private boolean numGatesOK(int gates) {
		return gates >= MIN_CHECKPOINTS && gates <= MAX_CHECKPOINTS;
	}
	
	
	/**
	 * Adds the specified Passenger to the specified line
	 * 
	 * @param line		int value representing the line to add the Passenger
	 * @param person	Passenger object being added to the line
	 * 
	 * @throws IllegalArgumentException	if the passenger index is out of range
	 */
	public void addToLine(int line, Passenger person) {
		if (line < 0 || line >= check.size()) {
			throw new IllegalArgumentException(ERROR_INDEX);
		}
		else {
			check.get(line).addToLine(person);
		}
	}	
	
	
	 /**
	  * Determines the shortest line that accepts all types of Passengers
	  * 
	  * @return the number representing the shortest line that any Passenger can access
	  */
	public int shortestRegularLine() {
		return shortestLineInRange(largestFastIndex, tsaPreIndex - 1);
	}
	
	 /**
	  * Determines the shortest line that accepts only FastTrack Passengers
	  * 
	  * @return the number representing the shortest line that only FastTrack Passengers have access to
	  */
	public int shortestFastTrackLine() {
		return shortestLineInRange(0, tsaPreIndex - 1);
	}

	
	 /**
	  * Determines the shortest line that accepts only TSA Pre-Check Passengers
	  * 
	  * @return the number representing the shortest line that only TSA Pre-Check Passengers have access to
	  */
	public int shortestTSAPreLine() {
		if (lengthOfLine(tsaPreIndex) <= lengthOfLine(shortestLineInRange(largestFastIndex + 1, tsaPreIndex)) * 2) {
			return tsaPreIndex;
		}
		else {
			return shortestLineInRange(0, tsaPreIndex);
		}
	}
	
	
	/**
	 * Determines the length of the line based on the number of Passengers in the line
	 * 
	 * @param line	the int value representing the line number being observed
	 * 
	 * @throws IllegalArgumentException if the index is out of range
	 * 
	 * @return the length of the line based on the number of number of people in the line
	 */
	public int lengthOfLine(int line) {
		try{
			return check.get(line).size();
		}
		catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException(ERROR_INDEX);
		}
	}
	
	/**
	 * Determines the next departure time.
	 * 
	 * @return time required for the next departure
	 */
	public int departTimeNext() {
		return check.get(lineWithNextToClear()).departTimeNext();
	}
	
	
	/**
	 * Determines the next passenger to leave Pre-Security
	 * 
	 * @return next Passenger to go
	 */
	public Passenger nextToGo() {
		if (lineWithNextToClear() == 0) {
			return null;
		}
		else {
			return check.get(lineWithNextToClear()).nextToGo();
		}
	}
	
	
	/**
	 * Removes the next Passenger
	 * 
	 * @return the passenger that was removed
	 */
	public Passenger removeNext() {
		if (lineWithNextToClear() == 0) {
			return null;
		}
		else {
			return check.get(lineWithNextToClear()).removeFromLine();
		}
	}
	
	
	/**
	 * Determines the shortest line based on the specified range made by the parameters
	 * 
	 * @param min	the int value for the lower bound of the specified range
	 * @param max	the int value for the upper bound of the specified range
	 * 
	 * @return the line number with the least number of people
	 */
	private int shortestLineInRange(int min, int max) {
		int shortest = min;
		int small = check.get(min).size();
		
		for (int i = min; i <= max; i++) {
			
			if (check.get(i).size() == 0) {
				return i;
			}
			
			if (small > check.get(i).size()) {
				small = check.get(i).size();
				shortest = i;
			}
		}
		return shortest;
	}
	
	
	/**
	 * Determines the next line number to clear out next
	 * 
	 * @return the line number to be cleared
	 */
	private int lineWithNextToClear() {
		int max = Integer.MAX_VALUE;
		int index = 0;
		
		for (int i = 0; i < tsaPreIndex; i++) {
			if (check.get(i).departTimeNext() < max) {
				max = check.get(i).departTimeNext();
				index = i;
			}
		}
		return index;
	}
}
