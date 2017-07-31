/* Directions:
 * The Josephus Problem is a famous mathematical puzzle that goes back to 
 * ancient times. There are many stories to go with the puzzle. One is that 
 * Josephus was one of a group of Jews who were about to be captured by the 
 * Romans. Rather than be enslaved, they chose to commit suicide. They arranged
 * themselves in a circle and, starting at a certain person, started counting 
 * off around the circle. Every nth person had to leave the circle and commit 
 * suicide. Josephus decided he didn’t want to die, so he arranged the rules so
 * he would be the last person left. If there were (say) 20 people, and he was 
 * the seventh person from the start of the circle, what number should he tell 
 * them to use for counting off? The problem is made much more complicated 
 * because the circle shrinks as the counting continues.
 * 
 * Create an application that uses a circular linked list (like that in 
 * Programming Project 5.3) to model this problem. Inputs are the number of 
 * people in the circle, the number used for counting off, and the number of 
 * the person where counting starts (usually 1). The output is the list of 
 * persons being eliminated. When a person drops out of the circle, counting 
 * starts again from the person who was on his left (assuming you go around 
 * clockwise). Here’s an example. There are seven people numbered 1 through 7, 
 * and you start at 1 and count off by threes. People will be eliminated in the 
 * order 4, 1, 6, 5, 7, 3. Number 2 will be left.
 */

package chapter5;

import java.util.Random;

public class Project5_5 {
	public static void main(String[] args) {
		// set up table
		System.out.println("                  "
				+ "People  |  Count  |  Start  |  Output");
		System.out.print("Textbook Example:    " + 7 + "         " + 3
				+ "         " + 1 + "       ");
		// textbook example input
		josephus(7, 3, 1);
		
		// display three more random inputs/outputs
		System.out.print(" Random Examples:    ");
		
		Random r = new Random();
		final int MAX = 8;
		
		int x = r.nextInt(MAX) + 1;
		int y = r.nextInt(MAX);
		int z = r.nextInt(x) + 1;
		
		System.out.print(x + "         " + y + "         " + z + "       ");
			josephus(x, y, z);
		
		for (int i = 0; i < 2; i++) {
			x = r.nextInt(MAX) + 1;
			y = r.nextInt(MAX);
			z = r.nextInt(x) + 1;
			
			System.out.print("                     " + x + "         " + y 
				+ "         " + z + "       ");
			josephus(x, y, z);
		}
	}
	
	public static void josephus(int numPeople, int countOff, int start) {
		CircularLinkedList circle = new CircularLinkedList();
		
		for (int i = 1; i <= numPeople; i++) {
			circle.insert(i);
		}
		
		circle.find(start);
		
		while (circle.getSize() > 1) {
			for (int i = 0; i < countOff; i++) {
				circle.step();
			}
			System.out.print(circle.deleteCurrent().data + " ");
			circle.step(); // advance to the left of person eliminated
		}
		System.out.print(circle.getCurrent().data);
		System.out.println();
	}
}