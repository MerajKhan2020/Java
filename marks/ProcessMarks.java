//Student Name: MD Meraj Khan
//Student ID: mkhan85
//Assesment 2: Task1
//Class: ProcessMarks

package marks;

import java.util.Arrays;

/**
 *
 * @author Meraj Khan
 */
public class ProcessMarks {
    
//This returns the Maximum Mark of the array of Marks   
	
    
	public int max(int[] arr)
	{
   	int highest = arr[0];
   	for(int i = 0; i < arr.length; i++)
   	{
       	if(arr[i] > highest)
       	{
           	highest = arr[i];
       	}
   	}
  	 
   	return highest;
	}
	
//This returns the Minimum Mark of the array of Marks
    
	
	public int min(int[] arr)
	{
    	int lowest = arr[0];
    	for(int i = 0; i < arr.length; i++)
    	{
        	if(arr[i] < lowest)
        	{
            	lowest = arr[i];
        	}
    	}
    	return lowest;
	}
    
//This returns the Range of the array of Marks
    
      	 
	public int range(int[] arr)
	{
    	return max(arr) - min(arr);
	}
    
//This returns the mean of the array of Marks
    
	    
	public double mean(int[] arr)
	{
    	double sum = 0;
    	for(int i = 0; i < arr.length; i++)
        	sum+=arr[i];
    
    	return sum/arr.length;
	}
    
//This returns the Median value of the array of Marks
    
	    
	public int median(int[] arr)
	{
    	int[] newArr = new int[arr.length];
    	newArr = arr;
    	Arrays.sort(newArr);
   	
    	return newArr[(newArr.length + 1)/2];
	}
    
    
//This returns the mode of the array of Marks
    
	    
	public int mode(int[] arr) {
    	int mode = arr[0];
    	int maxCount = 0;
    	for (int i = 0; i < arr.length; i++) {
        	int value = arr[i];
        	int count = 1;
        	for (int j = 0; j < arr.length; j++) {
            	if (arr[j] == value) count++;
            	if (count > maxCount) {
                	mode = value;
                	maxCount = count;
                	}
            	}
        	}
    	return mode;
	}


//This returns the Grades of the array of Marks
    
    
	public char[] grades(int[] arr)
	{
    	char a = 'A';
    	char b = 'B';
    	char c = 'C';
    	char d = 'D';
    	char e = 'E';
    	char f = 'F';
    	char[] newarr = new char[arr.length];
    	 
   	 
    	for(int i = 0; i < arr.length; i++)
    	{
        	if(arr[i] > 89)
        	{
            	newarr[i] = a;
        	}
        	else if(arr[i] > 74)
        	{
            	newarr[i] = b;
        	}
        	else if(arr[i] > 59)
        	{
            	newarr[i] = c;
        	}
        	else if(arr[i] > 49)
        	{
            	newarr[i] = d;
        	}
        	else if(arr[i] > 44)
        	{
            	newarr[i] = e;
        	}
        	else
        	{
            	newarr[i] = f;
        	}
    	}
    	return newarr;
   	 
	}
    
        
//This returns the total number of each grades in a form of array with all Int values. 
//It returns values in sorted order. Such as the first int value of the array is the total number of grade A. The 2nd one is total number of grade B and so on.        
        
	
        public int[] gradeDistn(int[] arr)
	{
    	int[] newarr = new int[arr.length];
    	char[] gradesArray = new char[arr.length];
    	newarr = arr;
    	Arrays.sort(newarr);
    	int countA = 0;
    	int countB = 0;
    	int countC = 0;
    	int countD = 0;
    	int countE = 0;
    	int countF = 0;
   	 
    	int[] countArray = new int[6];
   	 
   	gradesArray = grades(newarr);
   	for(int i = 0; i < gradesArray.length; i++)
   	{
       	if(gradesArray[i] == 'A')
       	{
           	countA++;
           	countArray[0] = countA;                 	 
 	 
       	}
       	else if(gradesArray[i] == 'B')
       	{
           	countB++;
           	countArray[1] = countB;
       	}
       	else if(gradesArray[i] == 'C')
       	{
           	countC++;
           	countArray[2] = countC;
       	}
       	else if(gradesArray[i] == 'D')
       	{
           	countD++;
           	countArray[3] = countD;
       	}
       	else if(gradesArray[i] == 'E')
       	{
           	countE++;
           	countArray[4] = countE;
       	}
       	else if(gradesArray[i] == 'F')
       	{
           	countF++;
           	countArray[5] = countF;
       	}
   	}
  	 
  	 
   	return countArray;
	}
    
}


