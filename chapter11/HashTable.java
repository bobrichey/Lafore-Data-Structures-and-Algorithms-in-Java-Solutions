package chapter11;

class HashTable {
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem nonItem; // for deleted items
	private double loadFactor; // Added for Project11_4
	private int numberOfItems; // Added for Project11_4

	public HashTable(int size) {
		arraySize = size;
		hashArray = new DataItem[arraySize];
		nonItem = new DataItem(-1); // deleted items have key of -1
		numberOfItems = 0;
		setLoadFactor();
	}

	public int hashFunction(int key) {
		return key % arraySize;
	}

	// Added for Project11_4
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

	// Added for Project11_4
	private void setLoadFactor() {
		loadFactor = (double) numberOfItems / arraySize;
	}

	// Added for Project11_4
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

	// Modified for Project11_1, Project11_4
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