package chapter3;

class ArrayInsertionSort {
	private int[] arr;
	private int numberOfElements;
	
	public ArrayInsertionSort(int max) {
		arr = new int[max];
		numberOfElements = 0;
	}
	
	public void insert(int value) {
		arr[numberOfElements++] = value;
	}
	
	public void insertionSort() {	
		// sorted portion is 0 through i at end of loop
		for (int i = 1; i < numberOfElements; i++) {
			int temp = arr[i];
			int j = i;
			
			while (j > 0 && arr[j - 1] >= temp) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
	}
	
	// Added for Project3_2
	// requires a sorted array
	public int median() {		
		// consider 0-based indexing
		if (numberOfElements % 2 == 1) {
			return arr[numberOfElements / 2]; 
		}
		else {
			return (arr[numberOfElements / 2] + arr[numberOfElements / 2 - 1]) / 2;
		}
	}
	
	// Added for Project3_3
	// nested if statement avoids unnecessary assignment of array elements
	public void noDups() {
		int nextAvailable = 1;
		int numDups = 0;
			
		for (int i = 1; i < numberOfElements; i++) {
			if (arr[i] != arr[i - 1]) {
				if (arr[nextAvailable] != arr[i]) {
					arr[nextAvailable] = arr[i];
				}
				nextAvailable++;
			}
			else {
				numDups++;
			}
		}
		numberOfElements -= numDups; 
	}
	
	public boolean isEmpty() {
		return numberOfElements == 0;
	} 
	
	public void display() {
		if (isEmpty()) {
			System.out.println("--");
		}
		else {
			for(int i = 0; i < numberOfElements; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		} 
	}
}