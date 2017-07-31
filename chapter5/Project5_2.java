/* Directions:
 * Implement a deque based on a doubly linked list. (See Programming Project 
 * 4.2 in the preceding chapter.) The user should be able to carry out the 
 * normal operations on the deque.
 */

package chapter5;
public class Project5_2 {
	public static void main(String[] args) {
		Deque d = new Deque();
		
		d.display();
		
		for (int i = 0; i < 8; i++) {
			d.insertLeft(i);
		}
		d.display();
		
		for (int i = 0; i < 8; i++) {
			d.insertRight(i);
		}
		d.display();
		
		d.removeLeft();
		d.removeRight();
		
		d.display();
		
		System.out.println(d.peekLeft());
		System.out.println(d.peekRight());
		
		while (!d.isEmpty()) {
			d.removeLeft();
		}
		d.display();
	}
}

class Deque {
	DoubleLinkedList list;
	
	public Deque() {
		list = new DoubleLinkedList();
	}
	
	public void insertLeft(int value) {
		list.insertFirst(value);
	}
	
	public int removeLeft() {
		return list.removeFirst().data;
	}
	
	public void insertRight(int value) {
		list.insertLast(value);
	}
	
	public int removeRight() {
		return list.removeLast().data;
	}
	
	public int peekLeft() {
		return list.peekFirst().data;
	}
	
	public int peekRight() {
		return list.peekLast().data;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void display() {
		list.displayFirstToLast();
	}
}