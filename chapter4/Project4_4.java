/* Directions:
 * The priority queue shown in Listing 4.6 features fast removal of the 
 * high-priority item but slow insertion of new items. Write a program with a 
 * revised PriorityQ class that has fast O(1) insertion time but slower removal
 * of the high-priority item. Include a method that displays the contents of 
 * the priority queue, as suggested in Programming Project 4.1.
 */

package chapter4;

import java.util.Random;

public class Project4_4 {

	public static void main(String[] args) {
		final int SIZE = 8;
		PriorityQ queue = new PriorityQ(SIZE);
		Random rand = new Random();
		
		try {
			for (int i = 0; i < SIZE; i++) {
				queue.insert(rand.nextInt(8));
			}
			queue.display();
			
			queue.remove();
			queue.remove();
			queue.remove();
			queue.display();
			
			queue.insert(8);
			queue.insert(9);
			queue.display();
			
			System.out.println(queue.peekMin());
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

}

class PriorityQ {
	private int maxSize;
	private int[] queueArray;     
	private int numberOfItems;
	
	public PriorityQ(int max) {
		maxSize = max;
		queueArray = new int[maxSize];
		numberOfItems = 0;
	}
	
	// Insert in constant time
	public void insert(int value) 
			throws Exception {
		if (isFull()) {
			throw new Exception("Queue is full");
		}
		else {
			queueArray[numberOfItems++] = value;
		}
	}
	
	// Remove in linear time
	public int remove() 
			throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue is empty");
		}
		else {
			int minIndex = getMinIndex();
			int min = queueArray[minIndex];
			
			for (int i = minIndex; i < numberOfItems - 1; i++) {
				queueArray[i] = queueArray[i + 1];
			}
			numberOfItems--;
			return min;
		}
	}
	
	public int peekMin() {
		return queueArray[getMinIndex()];
	}
	
	private int getMinIndex() {
		int minIndex = 0;
		
		for (int i = 1; i < numberOfItems; i++) {
			if (queueArray[i] < queueArray[minIndex]) {
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public boolean isEmpty() {
		return numberOfItems == 0;
	}
	
	public boolean isFull() {
		return numberOfItems == maxSize;
	}
	
	public void display() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for (int i = 0; i < numberOfItems; i++) {
				System.out.print(queueArray[i] + " ");
			}
			System.out.println();
		} 
	}
}