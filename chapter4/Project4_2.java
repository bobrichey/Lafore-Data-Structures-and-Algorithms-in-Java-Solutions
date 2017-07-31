/* Directions:
 * Create a Deque class based on the discussion of deques (double-ended queues)
 * in this chapter. It should include insertLeft(), insertRight(),
 * removeLeft(), removeRight(), isEmpty(), and isFull() methods. It will need 
 * to support wrap- around at the end of the array, as queues do.
 */

package chapter4;

public class Project4_2 {

	public static void main(String[] args) {
		final int SIZE = 8;
		Deque deque = new Deque(SIZE);

		try {
			for (int i = 0; i < 3; i++) {
				deque.insertLeft(i);
				deque.insertRight(i);
			}
			deque.display();
			
			deque.removeLeft();
			deque.insertLeft(5);
			deque.display();
			
			deque.removeRight();
			deque.insertLeft(4);
			deque.display();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

}

class Deque {
	private int maxSize;
	private int[] dequeArray;
	private int left;
	private int right;       
	private int numberOfItems;
	
	public Deque(int max) {
		maxSize = max;
		dequeArray = new int[maxSize];
		left = 1;
		right = 0;
		numberOfItems = 0;
	}
	
	public void insertLeft(int value)  
			throws Exception {
		if (isFull()) {
			throw new Exception("Queue is full");
		}
		else {
			if (left == 0) {
				left = maxSize;
			}
			dequeArray[--left] = value;
			numberOfItems++;
		}
	}
	
	public void insertRight(int value)  
			throws Exception {
		if (isFull()) {
			throw new Exception("Queue is full");
		}
		else {
			if (right == maxSize - 1) {
				right = -1;
			}
			dequeArray[++right] = value;
			numberOfItems++;
		}
	}
	
	public int removeRight() 
			throws Exception {
		if (isEmpty()) {
			throw new Exception("Queue is empty");
		}
		else {
			int temp = dequeArray[right++];
			if (right == maxSize) {
				right = 0;
			}
			numberOfItems--;
			return temp;
		}
	}
	
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
	
	public int peekFront() {
		return dequeArray[left];
	}
	
	public int peekRear() {
		return dequeArray[right];
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
	
	// extra method for displaying entire array
	public void displayDequeArray() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for (int i = 0; i < dequeArray.length; i++) {
				System.out.print(dequeArray[i] + " ");
			}
			System.out.println();
			for (int i = 0; i < dequeArray.length; i++) {
				if (i == left) {
					System.out.print("L ");
				}
				if (i == right) {
					System.out.print("R ");
				}
				System.out.print("  ");
			}
			System.out.println();
		} 
	}
}