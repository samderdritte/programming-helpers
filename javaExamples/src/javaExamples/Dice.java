package javaExamples;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This class simulates the roll of a die/dice.
 * @author samderdritte
 *
 */
public class Dice {
	
	/**
	 * Rolls a given amount of 6-sided dice.
	 * Returns a sorted array of the rolls.
	 * @param numDice	The number of dice to roll.
	 * @return An array of the rolls sorted from highest to lowest.
	 */
	public static Integer[] rollDice(int numDice) {
		Integer[] result = new Integer[numDice];		
		Random random = new Random();		
		for (int i=0; i<numDice;i++) {
			result[i] = random.nextInt(6)+1;
		}
		Arrays.sort(result, Collections.reverseOrder()); //comment line if results should not be sorted
		return result;
	}
	
	/**
	 * Roll a single 6-sided die.
	 * @return The result of the roll.
	 */
	public static int rollDie() {
		Random random = new Random();
		return random.nextInt(6)+1;
	}
	
	public static void main(String[] args) {
		
		Integer[] fiveRolls = rollDice(5);
		System.out.print("Multiple dice: ");
		for(int roll : fiveRolls) {
			System.out.print(roll + " ");			
		}
			
		System.out.println("\nSingle die: " + rollDie());
		
		
	}
}