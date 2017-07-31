/* Directions:
 *
 * Implement a radix sort as described in the last section of this chapter. 
 * It should handle variable amounts of data and variable numbers of digits 
 * in the key. You could make the number-base variable as well (so it can be
 * something other than 10), but it will be hard to see what’s happening 
 * unless you develop a routine to print values in different bases.
 */

package chapter7;

import java.util.concurrent.ThreadLocalRandom;

public class Project7_5 {
	public static void main(String[] args) {
		int size = 15;
		ArrayRadixSort arr = new ArrayRadixSort(size);
		
		for (int i = 0; i < size; i++) {
			arr.insert(ThreadLocalRandom.current().nextInt(1, 99 + 1));
		}
			
		arr.display();
		arr.radixSort();
		arr.display();
	}
}

class ArrayRadixSort {
	private int[] array;
	private int numberOfElements;
	
	public ArrayRadixSort (int max) {
		array = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		array[numberOfElements++] = value;
	}
	
	public void radixSort() {
		int radix = 10;
		
		// create array of linked lists
		DoubleEndList[] lists = new DoubleEndList[radix];
		
		for (int i = 0; i < lists.length; i++) {
			lists[i] = new DoubleEndList();
		}
		
		// establish number of digits in largest key
		int max = getMax(array);
		int division = 1; // 1, 10, 100, etc
		
		while (max / division > 0) {
			// copy array contents into lists by digit value
			for (int i = 0; i < numberOfElements; i++) {
				int digit = (array[i] / division) % radix;
				lists[digit].insertLast(array[i]);
			}
			
			// copy list contents back into array
			int index = 0;
			
			for (int i = 0; i < lists.length; i++) {				
				while (!lists[i].isEmpty()) {
					array[index++] = lists[i].deleteFirst();
				}
			}
			division *= radix;
		}
	}
	
	public int getMax(int[] array) {
		int max = array[0];
		
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	
	public void display() {
		for(int i = 0; i < numberOfElements; i++) {
			System.out.printf("%3d", array[i]);
		}
		System.out.print("\n\n");
	}
}

class DoubleEndList {
	private Link first;
	private Link last;
	
	public DoubleEndList() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		 return first == null;
	}
	
	public void insertFirst(int value) {
		Link newLink = new Link(value);
		
		if (isEmpty()) {
			last = newLink;
		}
		newLink.next = first;
		first = newLink;
	}
	
	public int deleteFirst() {		
		int temp = first.data;
		first = first.next;
		
		if (isEmpty()) {
			last = null;
		}
		return temp;
	}
	
	public void insertLast(int value) {
		Link newLink = new Link(value);
		
		if (isEmpty()) {
			first = newLink;
		}
		else {
			last.next = newLink;
		}
		last = newLink;
	}
	
	public void displayList() {
		Link current = first;
		
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
		System.out.println();
	}
}

class Link {
	int data;
	Link next;
	
	public Link(int value) {
		data = value;
	}
	
	public void displayLink() {
		System.out.printf("%3d ", data);
	}
}