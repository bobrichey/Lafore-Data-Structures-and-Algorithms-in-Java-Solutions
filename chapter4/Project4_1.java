/* Directions:
 * Write a method for the Queue class in the queue.java program (Listing 4.4)
 * that displays the contents of the queue. Note that this does not mean simply
 * displaying the contents of the underlying array. You should show the queue
 * contents from the first item inserted to the last, without indicating to the
 * viewer whether the sequence is broken by wrapping around the end of the
 * array. Be careful that one item and no items display properly, no matter
 * where front and rear are.
 */

package chapter4;

import java.util.Random;

public class Project4_1 {

	public static void main(String[] args) {
		final int SIZE = 8;
		Queue queue = new Queue(SIZE);
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
			queue.insert(8);
			queue.display();
			
			System.out.println(queue.peekFront());
			System.out.println(queue.peekRear());
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

}

class Queue {
	private int maxSize;
	private int[] queueArray;
	private int front;
	private int rear;       
	private int numberOfItems;
	
	public Queue(int max) {
		maxSize = max;
		queueArray = new int[maxSize];
		front = 0;
		rear = -1;
		numberOfItems = 0;
	}
	
	public void insert(int value) 
			throws Exception {
		if (isFull()) {
			throw new Exception("Queue is full");
		}
		else {
			if (rear == maxSize - 1) {
				rear = -1;
			}
			queueArray[++rear] = value;
			numberOfItems++;
		}
	}
	
	public int remove() 
			throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue is empty");
		}
		else {
			int temp = queueArray[front++];
			if (front == maxSize) {
				front = 0;
			}
			numberOfItems--;
			return temp;
		}
	}
	
	public int peekFront() {
		return queueArray[front];
	}
	
	public int peekRear() {
		return queueArray[rear];
	}
	
	public boolean isEmpty() {
		return numberOfItems == 0;
	}
	
	public boolean isFull() {
		return numberOfItems == maxSize;
	}
	
	public int getsSize() {
		return numberOfItems;
	}
	
	public void display() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for (int i = 0; i < numberOfItems; i++) {
				System.out.print(queueArray[(i + front) % maxSize] + " ");
			}
			System.out.println();
		}
	}
	
	// extra method for displaying entire array
	public void displayQueueArray() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for (int i = 0; i < queueArray.length; i++) {
				System.out.print(queueArray[i] + " ");
			}
			System.out.println();
		} 
	}
}