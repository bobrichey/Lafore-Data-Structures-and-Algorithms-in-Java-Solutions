/* Directions:
 * Add a merge() method to the OrdArray class in the orderedArray.java program
 * (Listing 2.4) so that you can merge two ordered source arrays into an 
 * ordered destination array. Write code in main() that inserts some random 
 * numbers into the two source arrays, invokes merge(), and displays the 
 * contents of the resulting destination array. The source arrays may hold 
 * different numbers of data items. In your algorithm you will need to compare
 * the keys of the source arrays, picking the smallest one to copy to the 
 * destination. You’ll also need to handle the situation when one source array
 * exhausts its contents before the other.
 */

package chapter2;

import java.util.Random;

public class Project2_5 {
	public static void main(String[] args) {
		final int SIZE_1 = 8;
		final int SIZE_2 = 4;
		
		OrderedArray arr1 = new OrderedArray(SIZE_1);	
		OrderedArray arr2 = new OrderedArray(SIZE_2);		
			
		Random rand = new Random();
		
		for (int i = 0; i < SIZE_1; i++) {
			arr1.insert(rand.nextInt(10));
		}
		for (int i = 0; i < SIZE_2; i++) {
			arr2.insert(rand.nextInt(10));
		}
		arr1.display();
		arr2.display();
		
		int[] arr3 = OrderedArray.merge(arr1, arr2);
		
		for (int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		System.out.println();
	}
}