/* Directions:
 * In the bubbleSort.java program (Listing 3.1) and the BubbleSort Workshop
 * applet, the in index always goes from left to right, finding the largest
 * item and carrying it toward out on the right. Modify the bubbleSort() method
 * so that it’s bidirectional. This means the in index will first carry the
 * largest item from left to right as before, but when it reaches out, it will
 * reverse and carry the smallest item from right to left. You’ll need two
 * outer indexes, one on the right (the old out) and another on the left.
 */

package chapter3;

import java.util.Random;

public class Project3_1 {
	public static void main(String[] args) {
		final int SIZE = 8;
		ArrayBubbleSort arr = new ArrayBubbleSort(SIZE);
		Random rand = new Random();
		
		// fill w/ values from 0-7
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(8));
		}
		arr.display();
		
		arr.bubbleSort();
		arr.display();
	}
}

class ArrayBubbleSort {
	private int[] arr;
	private int numberOfElements;
	
	public ArrayBubbleSort(int max) {
		arr = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		arr[numberOfElements++] = value;
	}
	
	public void bubbleSort() {
		int pointer = 0;
		
		for (int leftEnd = 0, rightEnd = numberOfElements - 1; 
			leftEnd < rightEnd; leftEnd++, rightEnd--) {
			
			while (pointer < rightEnd) {
				if (arr[pointer] > arr[pointer + 1]) {
					swap(pointer, pointer+1);
				}
				pointer++;
			}
			while (pointer > leftEnd) {
				if (arr[pointer] < arr[pointer - 1]) {
					swap(pointer, pointer-1);
				}
				pointer--;
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