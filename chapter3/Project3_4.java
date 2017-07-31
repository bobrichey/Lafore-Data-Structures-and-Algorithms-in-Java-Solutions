/* Directions:
 * Another simple sort is the odd-even sort. The idea is to repeatedly make two
 * passes through the array. On the first pass you look at all the pairs of
 * items, a[j] and a[j+1], where j is odd (j = 1, 3, 5, ...). If their key
 * values are out of order, you swap them. On the second pass you do the same
 * for all the even values (j = 2, 4, 6, ...). You do these two passes
 * repeatedly until the array is sorted. Replace the bubbleSort() method in
 * bubbleSort.java (Listing 3.1) with an oddEvenSort() method. Make sure it
 * works for varying amounts of data. You’ll need to figure out how many times
 * to do the two passes.
 * The odd-even sort is actually useful in a multiprocessing environment, where
 * a separate processor can operate on each odd pair simultaneously and then on
 * each even pair. Because the odd pairs are independent of each other, each
 * pair can be checked—and swapped, if necessary—by a different processor. This
 * makes for a very fast sort.
 */

package chapter3;

import java.util.Random;

public class Project3_4 {
	public static void main(String[] args) {
		final int SIZE = 8;
		ArrayOddEven arr = new ArrayOddEven(SIZE);
		Random rand = new Random();
		
		// fill w/ values from 0-7
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		
		arr.oddEvenSort();
		arr.display();
	}
}

class ArrayOddEven {
	private int[] arr;
	private int numberOfElements;
	
	public ArrayOddEven(int max) {
		arr = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		arr[numberOfElements++] = value;
	}
	
	// arrays w/ odd number of elements require n / 2 + 1 passes
	public void oddEvenSort() {		
		for (int i = 0; i < numberOfElements / 2 + numberOfElements % 2; i++) {
			for (int j = 1; j < numberOfElements - 1; j += 2) {
				if (arr[j] > arr[j + 1]) {
					swap(j, j+1);
				}
			}
			for (int j = 0; j < numberOfElements - 1; j += 2) {
				if (arr[j] > arr[j + 1]) {
					swap(j, j+1);
				}
			}
		}
	}
	
	private void swap(int one, int two) {
		int temp = arr[one]; 
		arr[one] = arr[two]; 
		arr[two] = temp;
	}
	
	public boolean isEmpty() {
		return numberOfElements == 0;
	} 
	
	public void display() {
		if (isEmpty()) {
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