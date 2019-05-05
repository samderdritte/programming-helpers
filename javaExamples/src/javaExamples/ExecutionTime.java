package javaExamples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Simple example of how you can measure the execution time of a method.
 * Just wrap the method in startTime and endTime and then calculate their difference.
 * @author samuelspycher
 *
 */
public class ExecutionTime {
	
	public static void main(String[] args) {
		
		/**
		 * Use this code to simply measure the execution time of a given code.
		 */
		long startTime = System.nanoTime();
	    
		Dice.rollDie(); // the method you want to measure
	    long endTime = System.nanoTime();
	    
	    long duration = (endTime - startTime)/1000000;

	    String message1 = "The execution took less than 1 millisecond.";
	    String message2 = "The execution took " + duration + " milliseconds.";
	    
	    String output = (duration == 0) ? message1 : message2;
	    System.out.println(output);
	    
	    
	    
	    /**
	     * Use the code below if you want the program to terminate after a given time.
	     */
	    final ExecutorService service = Executors.newSingleThreadExecutor();
	    
	    try {
	    	final Future<Object> f = service.submit(() -> {
	    		// Do you long running calculation here
                Thread.sleep(1200); // Simulate some delay (in milliseconds)
                return "42";
	    	});
	    	
	    	System.out.println(f.get(1000, TimeUnit.MILLISECONDS));
	    	// the timeout is set to 1000 Milliseconds (= 1 second), change if you want a different threshold
	    } catch (final TimeoutException e) {
	    	//run this code if execution took too long
	    	System.err.println("Calculation took to long");
	    	
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
	}
}
