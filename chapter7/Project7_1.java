/* Directions:
 * 
 * Modify the partition.java program (Listing 7.2) so that the 
 * partitionIt() method always uses the highest-index (right) element as 
 * the pivot, rather than an arbitrary number. (This is similar to what 
 * happens in the quickSort1.java program in Listing 7.3.) Make sure your 
 * routine will work for arrays of three or fewer elements. To do so, you
 * may need a few extra statements.
 */

package chapter7;

import java.util.Random;
import java.util.Scanner;

public class Project7_1 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter array size: "); 
		
		final int SIZE = input.nextInt();
		ArrayPartition arr = new ArrayPartition(SIZE);
		
		Random rand = new Random();
		
		for (int i = 0; i < SIZE; i++) {
			arr.insert(rand.nextInt(99));
		}
		
		arr.display();
		
		int partitionIndex = arr.makePartition(0, arr.getSize() - 1);
		System.out.println("Partition is at index " + partitionIndex); 
		
		arr.display();
		input.close();
	}
}

class ArrayPartition {
	private int[] array;
	private int numberOfElements;
	
	public ArrayPartition(int max) {
		array = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		array[numberOfElements++] = value;
	}
	
	public int getSize() {
		return numberOfElements;
	}
	
	public boolean isEmpty() {
		return numberOfElements == 0;
	}
	
	public int makePartition(int left, int right) {
		int leftPointer = left - 1;
		int rightPointer = right;
		int pivot = array[right];
		
		if (getSize() == 1) {
			return right;
		}
		else {
			while (true) {
				// no need to check leftPointer < right because pivot == right
				while (array[++leftPointer] < pivot) {
					// nop
				}
				while (array[--rightPointer] > pivot && rightPointer > left) {
					// nop
				}
				if (leftPointer >= rightPointer) {
					break;
				}
				else {
					swap(leftPointer, rightPointer);
				}
			}
			swap(leftPointer, right);
			return leftPointer;
		} 
	}
	
	public void swap(int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	public void display() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for(int i = 0; i < numberOfElements; i++) {
				System.out.print(array[i] + " ");
			}
			System.out.println();
		} 
	}
}