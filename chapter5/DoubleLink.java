package chapter5;

public class DoubleLink {
	int data;
	DoubleLink next;
	DoubleLink previous;
	
	public DoubleLink(int data) {
		this.data = data;
		next = null;
		previous = null;
	}
}
