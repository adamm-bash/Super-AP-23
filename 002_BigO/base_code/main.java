import java.util.*;
import java.time.*;
import java.lang.*;


class main {
	static final long createdNano = System.nanoTime();

	public static void main(String args[]) {
	
		long start = System.nanoTime();
		long finish = System.nanoTime();
		System.out.println("Created: " + createdNano);
		
		int [] times = {10, 100, 1000, 10000, 100000, 1000000};
		int [] nums = new int[100];


		System.out.println("-------------------Test-------------------");
		System.out.println("");
		for(int i : times) {
			System.out.println("Interval: " + i);
			
			nums = new int[i];
			start = System.nanoTime();

			//  Put your method between these two comments
			search(nums);
			//

			finish = System.nanoTime();
			System.out.println("Started: " + start);
			System.out.println("Finished: " + finish);
			System.out.println("Duration: " + (finish-start));
			System.out.println("");
		}
	}


	static boolean search(int[] input) 
	{
		int rando = (int)Math.random() * 200000;
		for(int i = 0; i < input.length; i++) 
		{
			if(input[i] == rando)
			{
				return true;
			}
		}
		return false;
	}

	static void bubbleSort(int[] input)
	{
		for (int i=0;i<input.length-1;++i)
		{
			for(int j=0;j<input.length-i-1; ++j)
			{
				if(input[j+1]<input[j])
				{

					int swap = input[j];
					input[j] = input[j+1];
					input[j+1] = swap;
				}
			}
		}
	}

	static void insertionSort(int[] array)
	{
		int n = array.length;  
		for (int j = 1; j < n; j++) 
		{  
				int key = array[j];  
				int i = j-1;  
				while ( (i > -1) && ( array [i] > key ) ) {  
						array [i+1] = array [i];  
						i--;  
				}  
				array[i+1] = key;
		}
	} 

	static void selectionSort(int[] arr)
{ 
		for (int i = 0; i < arr.length - 1; i++)  
		{  
				int index = i;  
				for (int j = i + 1; j < arr.length; j++){  
						if (arr[j] < arr[index]){  
								index = j;//searching for lowest index
						
						}  
				}  
				int smallerNumber = arr[index];   
				arr[index] = arr[i];  
				arr[i] = smallerNumber;  
		}

	} 
}
