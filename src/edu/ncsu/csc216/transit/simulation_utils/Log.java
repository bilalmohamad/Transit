/**
 * 
 */
package edu.ncsu.csc216.transit.simulation_utils;

import edu.ncsu.csc216.transit.airport.travelers.Passenger;

/**
 * The Log class acts as a logging mechanism for the simulation, with behaviors specified by the interface it implements, Reporter.
 * 
 * @author Bilal Mohamad
 */
public class Log implements Reporter {
	
	/** Double value used for converting from seconds to minutes */
	public static final double SEC_TO_MIN = 60.0;

	/** Number of passengers completed security checks*/
	private int numCompleted;
	
	/** Total time of all the passengers spent waiting */
	private int totalWaitTime;
	
	/** Total time of all the passengers spent being processed */
	private int totalProcessTime;
	
	
	/**
	 * Constructor for Log objects
	 */
	public Log() {
		numCompleted = 0;
		totalWaitTime = 0;
		totalProcessTime = 0;
	}
	
	
	/**
	 * Retrieves the number of Passengers completed security checks
	 * 
	 * @return the number of Passengers that have completed security checks
	 */
	public int getNumCompleted() {
		return numCompleted;
	}
	
	
	/**
	 * Logs the data of the specified Passenger
	 * 
	 * @param person	the Passenger object that is being logged
	 */
	public void logData (Passenger person) {
		totalWaitTime += person.getWaitTime();
		totalProcessTime += person.getProcessTime();
		numCompleted++;
	}
	
	
	/**
	 * Calculates the average time spent waiting by all the passengers
	 * 
	 * @return the average wait time
	 */
	public double averageWaitTime() {
		if (numCompleted == 0) {
			return 0.0;
		}
		double avg = (double) totalWaitTime / SEC_TO_MIN / (double) numCompleted;
		return avg;
	}
	
	
	/**
	 * Calculates the average time spent being processed by all the passengers
	 * 
	 * @return the average process time
	 */
	public double averageProcessTime() {
		if (numCompleted == 0) {
			return 0.0;
		}
		double avg = (double) totalProcessTime / SEC_TO_MIN / (double) numCompleted;
		return avg;
	}
}
