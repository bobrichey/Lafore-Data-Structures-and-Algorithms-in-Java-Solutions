/* Directions:
 * Implement a linear probe hash table that stores strings. You’ll need a hash 
 * function that converts a string to an index number; see the section “Hashing 
 * Strings” in this chapter. Assume the strings will be lowercase words, so 26 
 * characters will suffice.
 */

package chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Project11_2 {
	public static void main(String[] args) throws IOException {
		System.out.print("Enter size of hash table: ");
		int size = getInt();
		System.out.println();

		System.out.print("Enter initial number of items: ");
		int n = getInt();
		System.out.println();

		// Make table
		StringHashTable theStringHashTable = new StringHashTable(size);

		String aKey;
		StringItem aStringItem;
		Random rand = new Random();

		// Insert data
		for (int i = 0; i < n; i++) {
			aKey = makeRandomString(rand);
			aStringItem = new StringItem(aKey);
			theStringHashTable.insert(aStringItem);
		}

		// Interact with user
		while (true) {
			System.out.print("Enter the first letter of show, insert, delete, or find: ");
			char choice = getChar();

			switch (choice) {
			case 's':
				theStringHashTable.display();
				break;
			case 'i':
				System.out.print("Enter key value to insert: ");
				aKey = getString();
				aStringItem = new StringItem(aKey);
				theStringHashTable.insert(aStringItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getString();
				theStringHashTable.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getString();
				aStringItem = theStringHashTable.find(aKey);
				if (aStringItem != null) {
					System.out.println("Found " + aKey);
				} else
					System.out.println("Could not find " + aKey);
				break;
			default:
				System.out.print("Invalid entry\n");
			}
		}
	}

	// Creates a random string of 1 to 6 characters
	private static String makeRandomString(Random rand) {
		String s = "";
		int stringLength = rand.nextInt(6) + 1;

		// Append random characters a-z
		for (int i = 0; i < stringLength; i++) {
			s += (char) (rand.nextInt(26) + 97);
		}
		return s;
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}

class StringHashTable {
	private StringItem[] hashArray;
	private int arraySize;
	private StringItem nonItem; // for deleted items

	public StringHashTable(int size) {
		arraySize = size;
		hashArray = new StringItem[arraySize];
		nonItem = new StringItem("**"); // deleted items have key of -1
	}

	// based on Horner's method (see page 564)
	public int hashFunction(String key) {
		int hashValue = 0;

		for (int i = 0; i < key.length(); i++) {
			int letter = key.charAt(i) - 96; // 'a' = 1
			hashValue = (hashValue * 26 + letter) % arraySize;
		}
		return hashValue;
	}

	public void insert(StringItem item) { // assumes table isn't full
		String key = item.getKey();
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != "**") {
			++hashValue;
			hashValue %= arraySize;
		}
		hashArray[hashValue] = item;
	}

	public StringItem find(String key) {
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey().equals(key)) {
				return hashArray[hashValue];
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	public StringItem delete(String key) {
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey().equals(key)) {
				StringItem temp = hashArray[hashValue];
				hashArray[hashValue] = nonItem;
				return temp;
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	public void display() {
		System.out.print("Table: ");

		for (int i = 0; i < arraySize; i++) {
			if (hashArray[i] != null) {
				System.out.print(hashArray[i].getKey() + " ");
			} else {
				System.out.print("** ");
			}
		}
		System.out.println();
		System.out.println();
	}
}

class StringItem {
	private String data;

	public StringItem(String data) {
		this.data = data;
	}

	public String getKey() {
		return data;
	}
}