/* Directions:
 * To the insertSort.java program (Listing 3.3), add a method called noDups()
 * that removes duplicates from a previously sorted array without disrupting
 * the order. (You can use the insertionSort() method to sort the data, or you
 * can simply use main() to insert the data in sorted order.) One can imagine
 * schemes in which all the items from the place where a duplicate was
 * discovered to the end of the array would be shifted down one space every
 * time a duplicate was discovered, but this would lead to slow O(n^2) time, at
 * least when there were a lot of duplicates. In your algorithm, make sure no
 * item is moved more than once, no matter how many duplicates there are. This
 * will give you an algorithm with O(n) time.
 */

package chapter3;

import java.util.Random;

public class Project3_3 {
	public static void main(String[] args) {
		final int SIZE = 8;
		ArrayInsertionSort arr = new ArrayInsertionSort(SIZE);
		Random rand = new Random();
		
		// fill w/ random values from 0-7
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		
		arr.insertionSort();
		arr.display();
		
		arr.noDups();
		arr.display();
	}
}