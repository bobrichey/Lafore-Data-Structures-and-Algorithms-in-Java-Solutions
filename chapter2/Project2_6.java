/* Directions:
 * Write a noDups() method for the HighArray class of the highArray.java 
 * program (Listing 2.3). This method should remove all duplicates from the array. 
 * That is, if three items with the key 17 appear in the array, noDups() should 
 * remove two of them. Don’t worry about maintaining the order of the items. 
 * One approach is to first compare every item with all the other items and 
 * overwrite any duplicates with a null (or a distinctive value that isn’t used 
 * for real keys). Then remove all the nulls. Of course, the array size will be 
 * reduced.
 */

package chapter2;

import java.util.Random;

public class Project2_6 {
	public static void main(String[] args) {
		final int SIZE = 16;
		HighArray arr = new HighArray(SIZE);
		Random rand = new Random();
		
		// fill w/ values from 0-7
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		arr.noDups();
		arr.display();
	}
}