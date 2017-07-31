/* Directions:
 * Here’s an interesting way to remove duplicates from an array. The insertion 
 * sort uses a loop-within-a-loop algorithm that compares every item in the 
 * array with every other item. If you want to remove duplicates, this is one 
 * way to start. (See also Exercise 2.6 in Chapter 2.) Modify the 
 * insertionSort() method in the insertSort.java program so that it removes 
 * duplicates as it sorts. Here’s one approach: When a duplicate is found, 
 * write over one of the duplicated items with a key value less than any 
 * normally used (such as –1, if all the normal keys are positive). Then the 
 * normal insertion sort algorithm, treating this new key like any other item, 
 * will put it at index 0. From now on the algorithm can ignore this item. The 
 * next duplicate will go at index 1, and so on. When the sort is finished, all 
 * the removed dups (now represented by –1 values) will be found at the 
 * beginning of the array. The array can then be resized and shifted down so it 
 * starts at 0.
 */

package chapter3;

import java.util.Random;

public class Project3_6 {
	public static void main(String[] args) {
		final int SIZE = 8;
		ArrayInsertionSortNoDups arr = new ArrayInsertionSortNoDups(SIZE);
		Random rand = new Random();
		
		// fill w/ values from 0-7
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		
		arr.insertionSortNoDupes();
		arr.display();
	}
}

class ArrayInsertionSortNoDups {
	private int[] arr;
	private int numberOfElements;
	
	public ArrayInsertionSortNoDups(int max) {
		arr = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		arr[numberOfElements++] = value;
	}
	
	// Removes duplicate keys while performing an insertion sort
	public void insertionSortNoDupes() {	
		markDupes();
		
		for (int i = 1; i < numberOfElements; i++) {
			int temp = arr[i];
			int j = i;
			
			while (j > 0 && arr[j - 1] >= temp) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
		removeDupes();
	}
	
	// sets duplicate keys to -1
	private void markDupes() {
		for (int i = 0; i < numberOfElements; i++) {
			for (int j = i + 1; j < numberOfElements; j++) {
				if (arr[j] == -1) {
					continue;
				}
				if (arr[j] == arr[i]) {
					arr[i] = -1;
				}
			}
		}
	}
	
	// removes all duplicates (marked as -1) from array
	private void removeDupes() {
		int x = 0;
		
		while (arr[x] == -1) {
			x++;
		}
		for (int i = 0; i < numberOfElements - x; i++) {
			arr[i] = arr[i + x];
		}
		numberOfElements -= x;
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