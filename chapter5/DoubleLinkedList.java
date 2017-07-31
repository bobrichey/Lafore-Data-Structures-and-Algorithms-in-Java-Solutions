package chapter5;

public class DoubleLinkedList {
	private DoubleLink first;
	private DoubleLink last;
	
	public DoubleLinkedList() {
		first = null;
		last = null;
	}
	
	public void insertFirst(int n) {
		DoubleLink newLink = new DoubleLink(n);
		
		if (isEmpty()) {
			last = newLink;
		} 
		else {
			first.previous = newLink;
			newLink.next = first;
		}
		first = newLink;
	}
	
	public void insertLast(int n) {
		DoubleLink newLink = new DoubleLink(n);
		
		if (isEmpty()) {
			first = newLink;
		} 
		else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}
	
	// assume non-empty
	public DoubleLink removeFirst() {
		DoubleLink temp = first;
		
		first = first.next;
		
		if (isEmpty()) {
			last = null;
		}
		else {
			first.previous = null;
		}
		return temp;
	}
	
	// assume non-empty
	public DoubleLink removeLast() {
		DoubleLink temp = last;
		
		last = last.previous;
		
		if (isEmpty()) {
			first = null;
		}
		else {
			last.next = null;
		}
		return temp;
	}
	
	public DoubleLink peekFirst() {
		return first;
	}
	
	public DoubleLink peekLast() {
		return last;
	}
	
	public boolean isEmpty() {
		return first == null || last == null;
	}
	
	public void displayFirstToLast() {
		DoubleLink current = first;
		
		if (isEmpty()) {
			System.out.println("--");
		} 
		else {
			while (current != null) {
				System.out.print(current.data + " ");
				current = current.next;
			}
			System.out.println();
		}
	}
	
	public void displayLastToFirst() {
		DoubleLink current = last;
		
		if (isEmpty()) {
			System.out.println("--");
		} 
		else {
			while (current != null) {
				System.out.print(current.data + " ");
				current = current.previous;
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		DoubleLinkedList d = new DoubleLinkedList();
		d.displayFirstToLast();
		d.displayLastToFirst();
		
		d.insertFirst(5);
		d.displayFirstToLast();
		d.displayLastToFirst();
		
		d.removeLast();
		d.displayFirstToLast();
		d.displayLastToFirst();
		
		d.insertFirst(5);
		d.insertFirst(6);
		d.displayFirstToLast();
		d.displayLastToFirst();
		
		d.removeLast();
		d.displayFirstToLast();
		d.displayLastToFirst();
		
		d.removeLast();
		d.displayFirstToLast();
		d.displayLastToFirst();
	}
}
