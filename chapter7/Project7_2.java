/* Directions:
 * 
 * Modify the quickSort2.java program (Listing 7.4) to count the number of 
 * copies and comparisons it makes during a sort and then display the totals.
 * This program should duplicate the performance of the QuickSort2 Workshop 
 * applet, so the copies and comparisons for inversely sorted data should
 * agree. (Remember that a swap is three copies.)
 */

package chapter7;

import java.util.concurrent.ThreadLocalRandom;

public class Project7_2 {
	public static void main(String[] args) {
		int size = 12;
		ArrayQuicksort arr = new ArrayQuicksort(size);
		
		for (int i = 0; i < size; i++) {
			arr.insert(ThreadLocalRandom.current().nextInt(1, 99 + 1));			
		}
				
		//arr.display();
		arr.quicksort();
		//arr.makePartition(0, size - 1, 5);
		//arr.display();
		arr.displayQuicksortStats();
	}
}

class ArrayQuicksort {
	private int[] array;
	private int numberOfElements;
	
	// added fields for exercise 7.2
	private int numberOfSwaps;
	private int numberOfComparisons = 0;
	
	public ArrayQuicksort (int max) {
		array = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		array[numberOfElements++] = value;
	}
	
	public void quicksort() {
		recursiveQuicksort(0, numberOfElements - 1);
	}
	
	public void recursiveQuicksort(int leftEnd, int rightEnd) {	
		int partitionSize = rightEnd - leftEnd + 1;
			
		//numberOfComparisons++;
		if (partitionSize <= 3) {
			manualSort(leftEnd, rightEnd);
		}
		else {
			int pivot = findMedianOf3(leftEnd, rightEnd);
			int partition = makePartition(leftEnd, rightEnd, pivot);
			recursiveQuicksort(leftEnd, partition - 1);
			recursiveQuicksort(partition + 1, rightEnd);
		}
	}
	
	public int findMedianOf3(int leftEnd, int rightEnd) {
		int mid = (leftEnd + rightEnd) / 2;
		
		if (array[leftEnd] > array[mid]) {
			swap(leftEnd, mid);
		}
		if (array[leftEnd] > array[rightEnd]) {
			swap(leftEnd, rightEnd);
		}
		if (array[mid] > array[rightEnd]) {
			swap(mid, rightEnd);
		}
		numberOfComparisons += 3;
		
		// mid becomes pivot, place on right and return its value
		swap(mid, rightEnd - 1);
		return array[rightEnd - 1];
	}
	
	public int makePartition(int leftEnd, int rightEnd, int pivot) {
		int leftPointer = leftEnd;
		int rightPointer = rightEnd - 1; // begin at pivot
		
		while (true) {
			while (array[++leftPointer] < pivot && ++numberOfComparisons > 0) {
				// nop numberOfComparisons++;
			}
		
			while (array[--rightPointer] > pivot && ++numberOfComparisons > 0) {
				// nop numberOfComparisons++;
			}
		
			// break if pointers cross, otherwise swap elements
			//numberOfComparisons++;
			if (leftPointer >= rightPointer) {				
				break;
			}
			else {				
				swap(leftPointer, rightPointer);
			}
		}
		// place pivot in its sorted position
		swap(leftPointer, rightEnd - 1);
		return leftPointer;
	}
	
	public void manualSort(int leftEnd, int rightEnd) {
		int size = (rightEnd - leftEnd) + 1;
		
		//numberOfComparisons++;
		if (size == 1) {			
			return;
		}
		//numberOfComparisons++;
		if (size == 2) {			
			if (array[leftEnd] > array[rightEnd]) {
				swap(leftEnd, rightEnd);
			}
		}
		else {
			// manual sort three items, rightEnd - 1 is center item
			if (array[leftEnd] > array[rightEnd - 1]) {
				swap(leftEnd, rightEnd - 1);
			}
			if (array[leftEnd] > array[rightEnd]) {
				swap(leftEnd, rightEnd);
			}
			if (array[rightEnd - 1] > array[rightEnd]) {
				swap(rightEnd - 1, rightEnd);
			}
			numberOfComparisons += 3;
		}
	}
	
	public void swap(int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		
		// A swap counts for three copies
		numberOfSwaps++;
	}
	
	public void display() {
		for(int i = 0; i < numberOfElements; i++) {
			System.out.printf("%3d", array[i]);
		}
		System.out.print("\n\n");
	}
	
	public void displayQuicksortStats() {
		System.out.println("Number of comparisons: " + numberOfComparisons);
		System.out.println("Number of swaps: " + numberOfSwaps);
	}
}