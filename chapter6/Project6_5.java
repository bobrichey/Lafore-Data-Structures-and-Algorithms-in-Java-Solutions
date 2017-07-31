/* Directions:
 * Implement a recursive approach to showing all the teams that can be created 
 * from a group (n things taken k at a time). Write the recursive showTeams() 
 * method and a main() method to prompt the user for the group size and the 
 * team size to provide arguments for showTeam(), which then displays all the 
 * possible combinations.
 */

package chapter6;

import java.util.Scanner;

public class Project6_5 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter group size: ");
		int groupSize = input.nextInt();
		
		System.out.print("Enter team size: ");
		int teamSize = input.nextInt();
		
		char[] group = makeArr(groupSize);
		
		showTeams(groupSize, teamSize, group, "", teamSize);
		input.close();
	}
	
	// store in reverse order for more intuitive display of teams (ex. C, B, A)
	public static char[] makeArr(int groupSize) {
		char[] arr = new char[groupSize];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char)('A' + groupSize - i - 1);
		}
		return arr;
	}

	public static void showTeams(int n, int k, char[] arr, String team, int teamSize) {
		if (n < 1 || k < 1 || n < k) {
			return;
		}
		if (team.length() == teamSize - 1) {
			System.out.println(team + arr[n-1]);
		}
		showTeams(n - 1, k - 1, arr, team + arr[n-1], teamSize);
		showTeams(n - 1, k, arr, team, teamSize);
	}
}