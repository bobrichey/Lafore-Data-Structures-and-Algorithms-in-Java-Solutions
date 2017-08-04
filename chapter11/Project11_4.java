/* Directions:
 * Write a rehash() method for the hash.java program. It should be called by 
 * insert() to move the entire hash table to an array about twice as large 
 * whenever the load factor exceeds 0.5. The new array size should be a prime 
 * number. Refer to the section “Expanding the Array” in this chapter. Don’t 
 * forget you’ll need to handle items that have been “deleted,” that is, written 
 * over with –1.
 */

package chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Project11_4 {
	public static void main(String[] args) throws IOException {
		DataItem aDataItem;
		int aKey, size, n, keysPerCell;

		System.out.print("Enter size of hash table: ");
		size = getInt();

		System.out.print("Enter initial number of items: ");
		n = getInt();
		System.out.println();

		keysPerCell = 10;

		// Make table
		HashTable3 theHashTable = new HashTable3(size);

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

// Includes members for rehashing, uses quadratic probing
class HashTable3 {
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem; // for deleted items
	private double loadFactor; // Added for Project11_4
	private int numberOfItems; // Added for Project11_4

	public HashTable3(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); // deleted items have key of -1
		numberOfItems = 0;
		setLoadFactor();
	}

	public int hashFunction(int key) {
		return key % arraySize;
	}

	// -------------------------Added for Project11_4-------------------------//

	private void rehash() {
		// Copy existing table
		DataItem[] temp = new DataItem[arraySize];
		System.arraycopy(hashArray, 0, temp, 0, hashArray.length);

		// Create expanded table
		arraySize = getPrime(arraySize * 2);
		DataItem[] newHashArray = new DataItem[arraySize];
		hashArray = newHashArray;
		numberOfItems = 0;

		System.out.println("Table resized to " + hashArray.length);

		// Insert items into new table
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null && temp[i].getKey() != -1) {
				insert(temp[i]);
			}
		}
		System.out.printf("New load factor: %.2f\n\n", getLoadFactor());
	}

	private void setLoadFactor() {
		loadFactor = (double) numberOfItems / arraySize;
	}

	private double getLoadFactor() {
		return loadFactor;
	}

	private int getPrime(int min) {
		int n = min + 1;

		while (true) {
			if (isPrime(n)) {
				return n;
			}
			n++;
		}
	}

	private boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	// -----------------------------------------------------------------------//

	// Modified for Project11_4
	public void insert(DataItem item) { // assumes table isn't full
		int key = item.getKey();
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
			hashValue += probeCount * probeCount;
			probeCount++;

			hashValue %= arraySize;
		}
		hashArray[hashValue] = item;

		numberOfItems++;
		setLoadFactor();

		if (loadFactor > 0.5) {
			System.out.printf("Current load factor: %.2f\n", getLoadFactor());
			rehash();
		}
	}

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

	// Modified for Project11_4
	public DataItem delete(int key) {
		int hashValue = hashFunction(key);
		int probeCount = 1;

		while (hashArray[hashValue] != null) {
			if (hashArray[hashValue].getKey() == key) {
				DataItem temp = hashArray[hashValue];
				hashArray[hashValue] = nonItem;

				numberOfItems--;
				setLoadFactor();

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
		System.out.println();
	}
}