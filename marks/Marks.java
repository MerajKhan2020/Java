package marks;

//Student Name: MD Meraj Khan
//Student ID: mkhan85
//Assesment 2: Task1
//Class: Marks

import java.util.Random;

/**
 * A class that provides a random array of marks, approximately normally
 * distributed.
 *
 * @author Ken Lodge
 */
public class Marks {

    private static final int NMARKS = 125;
    private static final double mean = 65.0;
    private static final double std = 15.0;

    /**
     * Returns an array of NMARKS integer marks approximately normally
     * distributed, with specified mean (mean) and standard deviation (std), and
     * with values outside 0..100 removed.
     *
     * @return the array of marks.
     */
    public static int[] getMarks() {
        Random rand = new Random(1001L);

        int mark;
        int[] theMarks = new int[NMARKS];
        int n = 0;
        while (n < NMARKS) {
            mark = (int) Math.round(std * rand.nextGaussian() + mean);
            if (mark >= 0 && mark <= 100) {
                theMarks[n++] = mark;
            }
        }
        return theMarks;
    }

    /**
     * Test code
     *
     * @param args not used
     */
        
    public static void main(String[] args) {
        int[] testMarks = getMarks();
        for (int n = 0; n < testMarks.length; n++) {
            System.out.print(testMarks[n] + " ");
            if (n % 10 == 9) {
                System.out.println();
            }
        }
        
        ProcessMarks pm = new ProcessMarks(); 
        
       
        System.out.println();
        System.out.println();
        
        
        System.out.println("Maximum " + pm.max(testMarks)); // This shows the maximum of the array of marks. 

        System.out.println("Minimum " + pm.min(testMarks));  // This shows the minimum of the array of marks. 
        
        System.out.println("Range " + pm.range(testMarks));  // This shows the Range of the array of marks. 

        System.out.println("Mean " + pm.mean(testMarks)); // This shows the mean of the array of marks. 

        System.out.println("Median " + pm.median(testMarks)); // This shows the median of the array of marks. 

        System.out.println("Mode " + pm.mode(testMarks)); // This shows the Mode of the array of marks. 
        
        System.out.println();
        System.out.println();
        
    //No 5  *****************************************************************************************************        
        System.out.println("Grades: ");
        char[] temp = new char[pm.grades(testMarks).length];
        temp = pm.grades(testMarks);
        for(int i = 0; i < temp.length; i++){
            System.out.print(temp[i] + " ");
                if (i % 30 == 29) { 
                    //this allows to have the results printed with 30 values each line
                    System.out.println();
                }
            }    
        
        
        System.out.println();
        System.out.println();
        
    
    //No 6  *****************************************************************************************************
        System.out.println("Grade Distribution: ");      
        int[] grade1 = new int[pm.gradeDistn(testMarks).length];
        grade1 = pm.gradeDistn(testMarks);
        System.out.println("A: "+grade1[0]);
        System.out.println("B: "+grade1[1]);
        System.out.println("C: "+grade1[2]);
        System.out.println("D: "+grade1[3]);
        System.out.println("E: "+grade1[4]);
        System.out.println("F: "+grade1[5]);
        // gradeDistn returns value in sorted order. Such as the first int value of the array is the total number of grade A. The 2nd one is total number of grade B and so on.
      
    }                
                
                               
        
}
     
    

