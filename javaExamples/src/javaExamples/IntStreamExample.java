package javaExamples;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class demonstrates what you can do with IntStream. 
 * IntStream is similar to a for-loop. 
 * 
 * @author samderdritte
 *
 */
public class IntStreamExample {
	
    private static final String EMPTY_CELL = "EMPTY";
    
    /**
     * This method takes an ArrayList of Strings and returns the indices of objects
     * according to a filter-criteria. In this case, we filter all entries which contain 
     * the string "EMPTY".
     * 
     * @param players	A List of Strings, some of which are "EMPTY"
     * @return emptyCells	A List of Integers. The indices of the "EMPTY" cells.
     */
    public static List<Integer> getEmptyCells(List<String> players) {
    	
    	List<Integer> emptyCells = IntStream.range(0, players.size())
                .boxed()
                .filter(player -> players.get(player).equals(EMPTY_CELL))
                .collect(Collectors.toList());
    	
    	return emptyCells;
    }
    
    /**
     * This method counts the number of "EMPTY" cells in the List.
     * 
     * @param players	A List of Strings, some of which are "EMPTY"
     * @return sumEmptyCells	The sum of "EMPTY" cells.
     */
    public static int countEmptyCells(List<String> players) {
    	
    	long sumEmptyCells = IntStream.range(0, players.size())
                .boxed()
                .filter(player -> players.get(player).equals(EMPTY_CELL))
                .count();
    	
    	return (int) sumEmptyCells;
    }

	public static void main(String[] args) {
		
		// create a list of String-elements
        List<String> players = new ArrayList<String>();
        players.add("Messi");
        players.add("Ronaldo");
        players.add(EMPTY_CELL);
        players.add(EMPTY_CELL);
        players.add("Sanchez");
        players.add(EMPTY_CELL);
        players.add("Neuer");
        
        System.out.println("The players are: " + players);

        // call the method on the list - now we get the indices of the "empty" cells
        System.out.println(getEmptyCells(players));
        
        // call the method to count the "empty" cells
        System.out.println(countEmptyCells(players));
        
        players.set(getEmptyCells(players).get(0), "Rooney");
        // you can fill the empty cells by addressing the first element of the empty cells consecutively
        players.set(getEmptyCells(players).get(0), "Alba");
        
        System.out.println("The players are: " + players);
        System.out.println(countEmptyCells(players));
        
	}

}