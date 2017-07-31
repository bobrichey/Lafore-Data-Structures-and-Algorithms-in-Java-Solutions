/* Directions:
 * Add a method called median() to the ArrayIns class in the insertSort.java
 * program (Listing 3.3). This method should return the median value in the
 * array. (Recall that in a group of numbers half are larger than the median
 * and half are smaller.) Do it the easy way.
 */

package chapter3;

import java.util.Random;

public class Project3_2 {
	public static void main(String[] args) {
		final int SIZE = 7;
		ArrayInsertionSort arr = new ArrayInsertionSort(SIZE);
		Random rand = new Random();
		
		// fill w/ values from 0-7
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		
		arr.insertionSort();
		System.out.println(arr.median());
		arr.display();
	}
}