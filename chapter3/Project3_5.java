/* Directions:
 * Modify the insertionSort() method in insertSort.java (Listing 3.3) so it
 * counts the number of copies and the number of comparisons it makes during a
 * sort and displays the totals. To count comparisons, you’ll need to break up
 * the double condition in the inner while loop. Use this program to measure
 * the number of copies and comparisons for different amounts of inversely
 * sorted data. Do the results verify O(n^2) efficiency? Do the same for
 * almost-sorted data (only a few items out of place). What can you deduce
 * about the efficiency of this algorithm for almost-sorted data?
 *
 * Answer: results are consistent with O(n^2) running time. Testing suggests
 * nearly sorted arrays require comparatively few copies.
 */

package chapter3;

import java.util.Random;

public class Project3_5 {
	public static void main(String[] args) {
		final int SIZE = 8;
		
		ArrayInsertionSortWithCounts arr1 = new ArrayInsertionSortWithCounts(SIZE);
		Random rand = new Random();
		
		arr1.insert(0);
		arr1.insert(1);
		arr1.insert(rand.nextInt(8));
		arr1.insert(2);
		
		arr1.insert(3);
		arr1.insert(rand.nextInt(8));
		arr1.insert(rand.nextInt(8));
		arr1.insert(8);
		
		ArrayInsertionSortWithCounts arr2 = new ArrayInsertionSortWithCounts(SIZE);
		
		for (int i = 8; i > 0; i--) {
			arr2.insert(i);
		}
		
		arr1.display();
		arr1.insertionSort();
		arr1.display();
		System.out.printf("Comparisons: %d, Copies: %d\n", 
			arr1.getComparisons(), arr1.getCopies());
		
		System.out.println();
		
		arr2.display();
		arr2.insertionSort();
		arr2.display();
		System.out.printf("Comparisons: %d, Copies: %d\n", 
			arr2.getComparisons(), arr2.getCopies());
	}
}

class ArrayInsertionSortWithCounts {
	private int[] arr;
	private int numberOfElements;
	private int numberOfComparisons;
	private int numberOfCopies;
	
	public ArrayInsertionSortWithCounts(int max) {
		arr = new int[max];
		numberOfElements = 0;
		numberOfComparisons = 0;
		numberOfCopies = 0;
	}
	
	public void insert(int value) {
		arr[numberOfElements++] = value;
	}
	
	public int getComparisons() {
		return numberOfComparisons;
	}
	
	public int getCopies() {
		return numberOfCopies;
	}
	
	public void insertionSort() {
		// sorted portion is 0 through i at end of each iteration
		for (int i = 1; i < numberOfElements; i++) {
			int temp = arr[i];
			int j = i;
			
			while (j > 0) {
				if (arr[j - 1] <= temp) {
					numberOfComparisons++;
					break;
				}
				numberOfComparisons++;
				arr[j] = arr[j - 1];
				numberOfCopies++;
				j--;
			}
			arr[j] = temp;
		}
	}
	
	public void display() {
		if (numberOfElements == 0) {
			System.out.println("--");
		}
		else {
			for(int i = 0; i < numberOfElements; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		} 
	}
}