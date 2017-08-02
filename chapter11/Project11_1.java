/* Directions:
 * Modify the hash.java program (Listing 11.1) to use quadratic probing.
 */

package chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project11_1 {
	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		int aKey, size, n, keysPerCell;

		System.out.println("Enter size of hash table: ");
		size = getInt();

		System.out.println("Enter initial number of items:");
		n = getInt();

		keysPerCell = 10;

		// Make table
		HashTable theHashTable = new HashTable(size);

		// Insert data
		for (int i = 0; i < n; i++) {
			aKey = (int) (Math.random() * keysPerCell * size);
			aDataItem = new DataItem(aKey);
			theHashTable.insert(aDataItem);
		}

		// Interact with user
		while (true) {
			System.out.print("Enter the first letter of show, insert, delete, or find: ");
			char choice = getChar();

			switch (choice) {
			case 's':
				theHashTable.display();
				break;
			case 'i':
				System.out.print("Enter key value to insert: ");
				aKey = getInt();
				aDataItem = new DataItem(aKey);
				theHashTable.insert(aDataItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getInt();
				theHashTable.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getInt();
				aDataItem = theHashTable.find(aKey);
				if (aDataItem != null) {
					System.out.println("Found " + aKey);
				} else
					System.out.println("Could not find " + aKey);
				break;
			default:
				System.out.print("Invalid entry\n");
			}
		}
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

class HashTable {
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem; // for deleted items

	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); // deleted items have key of -1
	}

	public int hashFunction(int key) {
		return key % arraySize;
	}

	// Modified for Project11_1
	public void insert(DataItem item) { // assumes table isn't full
		int key = item.getKey();
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
			System.out.println(probeCount);
			hashValue += probeCount * probeCount;
			probeCount++;

			hashValue %= arraySize;
		}
		hashArray[hashValue] = item;
	}

	// Modified for Project11_1
	public DataItem find(int key) {
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				return hashArray[hashValue];
			}
			hashValue += probeCount * probeCount;
			probeCount++;

			hashValue %= arraySize;
		}
		return null;
	}

	// Modified for Project11_1
	public DataItem delete(int key) {
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				DataItem temp = hashArray[hashValue];
				hashArray[hashValue] = nonItem;
				return temp;
			}
			hashValue += probeCount * probeCount;
			probeCount++;

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
	}
}

class DataItem {
	private int data;

	public DataItem(int data) {
		this.data = data;
	}

	public int getKey() {
		return data;
	}
}