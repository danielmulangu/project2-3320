//Name: Daniel Mulangu Kaseya
//ID: 72826452
//Email: dkaseya@unomaha.edu

import java.util.*;

public class SortingArray
{
    //Bubble sort
	public static void bubbleSort(int [] array) {
		int size = array.length;
		int i=0, j=0;
		for(i=0; i<size-1; i++){
			for(j=0; j<size-i-1;j++){
               if(array[j]> array[j+1]){
				swap(array, j, j+1);
			   }
			}
		}
	}

    //SelectionSort
	public static void selectionSort(int [] array) {
		int size = array.length;
		int i=0,j=0, min_idx = 0;
		for(i=0;i<size-1;i++){
			min_idx=i;
			for(j=i+1;j<size;j++){
				if(array[min_idx]>array[j]){min_idx=j;}
			}
			swap(array, i, min_idx);
		}
	}

    //InsertionSort
	public static void insertionSort(int [] array) {
		int size = array.length;
		int i=0;
		for(i=1; i<size; ++i){
			int key= array[i];
			int j=i-1;
			while(j>=0 && array[j] > key){
				array[j+1]=array[j];
				j-=1;
			}
			array[j+1]=key;


		}
    }

    //Shell sort
    public static void shellSort(int [] array) {
		int gap,i=0,j=0;
		int size = array.length;
		gap=size/2;
		while(gap>=1){
			for(i=gap;i<size;i++){
				j=i;
				while(j>=gap && array[j]<array[j-gap]){
					swap(array, j-gap, j);
					j-=gap;
				}
                
			}
			gap/=2;
		}
		
    }

    //Merge sort
    public static void mergeSort(int [] array, int[] temp, int left, int right) {
		if(left<right){
			int center = (left + right)/2;
			mergeSort(array, temp, left, center);
			mergeSort(array, temp, center+1, right);
			merge(array, temp, left, center, right);
			
		}
    }

    public static void merge(int [] array, int[] temp, int left,int center, int right) {
		int l= left;
		int r = center +1;
		int i = left;
		while(l<=center && r<=right){
			if(array[l]< array[r] ){
				temp[i++]=array[l++];

			}
			else{
				temp[i++]= array[r++];
			}

		}
		while(l<=center) {temp[i++]= array[l++];}
		while(r<=right) { temp[i++]= array[r++];}
		for(i=left;i<=right;i++){
			array[i] = temp[i];
		}
    }

    //Quick sort
    public static void quickSort(int [] array, int left, int right) {
		if(left<right){
			int pivot = array[(left +right)/2];
			int l=left;
			int r=right;

			while(l<=r){
				while(array[l]<pivot) {l++;}
				while(array[r]>pivot) {r--;}
				if (l<=r){
					swap(array, l, r);
					l++;
					r--;
				}
			}
			quickSort(array, left, l-1);
			quickSort(array, l, right);
		}
    }

	public static void swap(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

	public static void printArray(int [] array) {
        int n = array.length;
        for (int index = 0; index < n; index++) {
            System.out.print(array[index]);
            System.out.print(' ');
        }
    }

	public static int[] generateRandomNumbers(int cnt, int type) {
		//This function will generate Integer array of which size is cnt. 
		//The range of the generated numbers is 0 ~ cnt
		//type: 1: totally random numbers, 2: sorted_numbers, 3: reverse-order_numbers, else: not supported
		int[] data = new int[cnt];
		Random random = new Random();
		if(type==1){
			
			int temp,i=0;
			
			while(i<cnt){
				boolean flag=false;
				temp=random.nextInt();
				//Prevent duplicate number
				for(int j=0;j<i;j++){
					if(temp==data[j]){ flag=true; break; }
					}

					if(flag==false){
						data[i] = temp;
					}
					else{
						i--;
					}
				
				i++;
			}
		}
		else if(type==2){
			int temp,i=0;
			while(i<cnt){
				boolean flag=false;
				temp=random.nextInt(cnt+1);
				//Prevent duplicate number to be added to the array
				for(int j=0;j<i;j++){
					if(temp==data[j]){ flag=true; break; }
					}

					if(flag==false){
						data[i] = temp;
					}
					else{
						i--;
					}
				
				i++;
			}
			Arrays.sort(data);
		}
		else if(type==3){
			int temp,i=0;
			while(i<cnt){
				boolean flag=false;
				temp=random.nextInt(cnt+1);
				//Prevent duplicate number to be added to the array
				for(int j=0;j<i;j++){
					if(temp==data[j]){ flag=true; break; }
					}

					if(flag==false){
						data[i] = temp;
					}
					else{
						i--;
					}
				
				i++;
			}
			Arrays.sort(data);
			for(int j=0;j<cnt/2;j++){
				swap(data, j, cnt-j-1);
			}
		}
		else {
			System.out.println("Not supported, wrong first parameter. Please try again");
		}
		return data;
	}

    public static void main(String[] args) {
		// The first argument, i.e., args[0] and args[1] will be used as a prameter for the generateRandomNumbers function below. 
		// You must receive parameters from the command lines like below.
		// java SortingArray 3 1000000 6 (create reverse-order numbers (0-1000000 and sort it using QuickSort) 
		
          //Get the inputs from command line 
	    Scanner x= new Scanner(System.in);
		int input = x.nextInt();
		Scanner y = new Scanner(System.in);
		int counter = y.nextInt();
		Scanner z= new Scanner(System.in);
		int sorting_type = z.nextInt();
         // Verfying the inputs
		if(counter<=0){System.out.println("Wrong  input size number, please try again");}
		else{

			int [] array = generateRandomNumbers( counter ,input  );

			//Print numbers only when the cnt is less than 100
			if(array.length < 100) {
	        System.out.print("\t Before sort: ");
	        printArray(array);
	        System.out.println("\n");
			}
			// Timer Start
			 long Timer_Start = System.nanoTime();

			// Sorting method will be provided as the second parameter for main args[2]
			// 1: Bubble Sort
			// 2: Selection Sort
			// 3: Insertion Sort
			// 4: Shell Sort
			// 5: Merge Sort
			// 6: Quick Sort
			// Else: Not supported
			
				if(sorting_type==1){
					bubbleSort(array);
				}
				else if(sorting_type==2){
					selectionSort(array);
				}
				else if(sorting_type==3){
					insertionSort(array);
				}
				else if(sorting_type==4){
					shellSort(array);
				}
				else if(sorting_type==5){
					int [] temp = new int[counter];
					mergeSort(array, temp, 0, counter-1);
				}
				else if(sorting_type==6){
					quickSort(array, 0, counter-1);
				}
				else{
					System.out.println("Not supported, Wrong 3rd parameter. Try again");
				}
				


			

			//Timer end
			long Timer_End = System.nanoTime();

			//Print elapsed time for sorting. 
			long elapsed_Time = (Timer_End - Timer_Start) ; // Obtain the time elapsed 

			System.out.println("Elapsed Time is:"+ elapsed_Time+" nanoseconds");
			//Print numbers only when the cnt is less than 100
			if(array.length < 100) {
				System.out.print("\t After sort: ");
				printArray(array);
				System.out.println("\n");
			}
		}

			

		

		
    }
}