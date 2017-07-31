/* Directions:
 * Queues are often used to simulate the flow of people, cars, airplanes, 
 * transactions, and so on. Write a program that models checkout lines at a 
 * supermarket, using the Queue class from the queue.java program 
 * (Listing 4.4). Several lines of customers should be displayed; you can use 
 * the display() method of Programming Project 4.1. You can add a new customer 
 * by pressing a key. You’ll need to determine how the customer will decide 
 * which line to join. The checkers will take random amounts of time to process
 * each customer (presumably depending on how many groceries the customer has). 
 * Once checked out, the customer is removed from the line. For simplicity, you
 * can simulate the passing of time by pressing a key. Perhaps every key press 
 * indicates the passage of one minute. (Java, of course, has more 
 * sophisticated ways to handle time.)
 */

package chapter4;

import java.util.Random;
import java.util.Scanner;

public class Project4_5 {

	public static void main(String[] args) {
		final int NUM_LINES = 4;
		final int SIZE = 8;
		
		CheckoutLine lines[] = new CheckoutLine[NUM_LINES];
		
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new CheckoutLine(SIZE);
		}
		Random rand = new Random();
		Scanner input = new Scanner(System.in);		
		String s;
		
		System.out.println("Press \"i\" to insert an item");
		System.out.println("Press \"a\" to advance time");
		System.out.println("Press \"q\" to quit");
		System.out.println();
		
		do {
			printCheckoutLines(lines);

			s = input.nextLine();
			
			if (s.equals("i")) {
				insertItem(lines, rand);
			}
			if (s.equals("a")) {
				advanceTime(lines);
			}
		} while (!s.equals("q"));
			
		input.close();
	}
	
	// decide which line is shortest via linear search
	public static void insertItem(CheckoutLine[] lines, Random rand) {
		int minWeightIndex = 0;
		int minWeight = lines[minWeightIndex].getWeight();
		
		for (int i = 0; i < lines.length; i++) {
			if (!lines[i].isFull() && lines[i].getWeight() < minWeight) {
				minWeightIndex = i;
				minWeight = lines[minWeightIndex].getWeight();
			}
		}
		
		lines[minWeightIndex].insert(rand.nextInt(8) + 1);
	}
	
	// checkout 1 item from front of each queue
	public static void advanceTime(CheckoutLine[] lines) {
		for (int i = 0; i < lines.length; i++) {
			lines[i].decrementFront();
		}
	}
	
	public static void printCheckoutLines(CheckoutLine[] lines) {
		for (int i = 0; i < lines.length; i++) {
			System.out.print("Queue " + (i+1) + ": ");
			lines[i].display();
		}
	}
}

class CheckoutLine {
	private int maxSize;
	private int[] queueArray;
	private int front;
	private int rear;       
	private int numberOfItems;
	private int weight;
	
	public CheckoutLine(int max) {
		maxSize = max;
		queueArray = new int[maxSize];
		front = 0;
		rear = -1;
		numberOfItems = 0;
		weight = 0;
	}
	
	public void insert(int value) {
		if (!isFull()) {
			if (rear == maxSize - 1) {
				rear = -1;
			}
			queueArray[++rear] = value;
			numberOfItems++;
			weight += value;
		}
	}
	
	public int remove() {
		if (isEmpty()) {
			return -1;
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
	
	public int getWeight() {
		return weight;
	}
	
	public void decrementFront() {
		if (!isEmpty()) {
			queueArray[front] -= 1;
			weight -= 1;
			
			if (queueArray[front] == 0) {
				remove();
			}
		}
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