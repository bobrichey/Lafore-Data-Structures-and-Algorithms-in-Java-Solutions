/* Directions:
 * In Chapter 8, “Binary Trees,” we’ll look at binary trees, where every branch 
 * has (potentially) exactly two sub-branches. If we draw a binary tree on the 
 * screen using characters, we might have 1 branch on the top row, 2 on the 
 * next row, then 4, 8, 16, and so on. Here’s what that looks like for a tree 
 * 16 characters wide:
 * 
 * --------X------- 
 * ----X-------X--- 
 * --X---X---X---X- 
 * -X-X-X-X-X-X-X-X
 * XXXXXXXXXXXXXXXX
 * 
 * (Note that the bottom line should be shifted a half character-width right, 
 * but there’s nothing we can do about that with character-mode graphics.) You 
 * can draw this tree using a recursive makeBranches() method with arguments 
 * left and right, which are the endpoints of a horizontal range. When you 
 * first enter the routine, left is 0 and right is the number of characters 
 * (including dashes) in all the lines, minus 1. You draw an X in the center of 
 * this range. Then the method calls itself twice: once for the left half of 
 * the range and once for the right half. Return when the range gets too small. 
 * You will probably want to put all the dashes and Xs into an array and 
 * display the array all at once, perhaps with a display() method. Write a 
 * main() program to draw the tree by calling makeBranches() and display(). 
 * Allow main() to determine the line length of the display (32, 64, or 
 * whatever). Ensure that the array that holds the characters for display is no 
 * larger than it needs to be. What is the relationship of the number of lines 
 * (five in the picture here) to the line width?
 */

package chapter6;

public class Project6_2 {
	public static void main(String[] args) {
		int numLeaves = 64; // MUST BE A POWER OF 2, ELSE log2() fails
		char[][] tree = new char[(log2(numLeaves) + 1)][numLeaves];
		makeBranches(0, numLeaves-1, tree, 0);
		display(tree);
	}
	
	public static void makeBranches(int left, int right, char[][] arr, int row) {
		if (left == right) {
			arr[row][left] = 'x';
			return;
		}
		else {
			int mid = (left + right) / 2;
			
			for (int i = left; i <= right; i++) {
				if (i == mid) {
					arr[row][i] = 'x';
				} 
				else {
					arr[row][i] = '-';
				}
			}
			row++;
			
			makeBranches(left, mid, arr, row);
			makeBranches(mid + 1, right, arr, row);
		}
	}
	
	public static int log2(double num) {
		return (int)(Math.log(num)/Math.log(2));
	} 
	
	public static void display(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}