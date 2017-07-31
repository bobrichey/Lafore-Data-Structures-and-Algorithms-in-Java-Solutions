package chapter5;

public class SortedList {
	Link first;
	
	public SortedList() {
		first = null;
	}
	
	public void insert(int n) {
		Link newLink = new Link(n);
		
		Link previous = null;
		Link current = first;
		
		while (current != null && current.data < n) {
			previous = current;
			current = current.next;
		}
		
		if (previous == null) {
			first = newLink;
		} 
		else {
			previous.next = newLink;
		} 
		newLink.next = current;
	}
	
	public Link getFirst() {
		return first;
	}
	
	public Link deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void display() {
		Link current = first;
		
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
}
