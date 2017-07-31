/* Directions:
 * Write a program that implements a stack class that is based on the Deque
 * class in Programming Project 4.2. This stack class should have the same 
 * methods and capabilities as the StackX class in the stack.java program 
 * (Listing 4.1).
 */

package chapter4;

public class Project4_3 {

	public static void main(String[] args) {
		final int SIZE = 8;
		DequeStack stack = new DequeStack(SIZE);

		try {
			for (int i = 0; i < SIZE; i++) {
				stack.insertLeft(i);
			}
			stack.display();
			
			stack.removeLeft();
			stack.insertLeft(5);
			stack.display();
			
			System.out.println(stack.peekFront());
			stack.display();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

}

class DequeStack {
	private int maxSize;
	private int[] dequeArray;
	private int left;
	private int right;       
	private int numberOfItems;
	
	public DequeStack(int max) {
		maxSize = max;
		dequeArray = new int[maxSize];
		left = 1;
		right = 0;
		numberOfItems = 0;
	}
	
	// push
	public void insertLeft(int value)  
			throws Exception {
		if (isFull()) {
			throw new Exception("Stack is full");
		}
		else {
			if (left == 0) {
				left = maxSize;
			}
			dequeArray[--left] = value;
			numberOfItems++;
		}
	}
	
	// pop
	public int removeLeft() 
			throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue is empty");
		}
		else {
			int temp = dequeArray[left++];
			if (left == maxSize) {
				left = 0;
			}
			numberOfItems--;
			return temp;
		}
	}
	
	// peek
	public int peekFront() {
		return dequeArray[left];
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
				System.out.print(dequeArray[(i + left) % maxSize] + " ");
			}
			System.out.println();
		}
	}
}