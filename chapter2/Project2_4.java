/* Directions:
 * Modify the orderedArray.java program (Listing 2.4) so that the insert() and
 * delete() routines, as well as find(), use a binary search, as suggested in 
 * the text.
 */

package chapter2;

import java.util.Random;

public class Project2_4 {
	public static void main(String[] args) {
		final int SIZE = 16;
		OrderedArray arr = new OrderedArray(SIZE);		
		Random rand = new Random();
		
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		
		// delete one occurrence of each value in array
		for (int i = 0; i < 8; i++) {
			arr.delete(i);
		}
		arr.display();
	}
}