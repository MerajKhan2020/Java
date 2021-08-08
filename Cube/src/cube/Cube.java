//Student Name: MD Meraj Khan
//Student ID: mkhan85
//Assesment 1: Task1

package cube;

import java.util.Scanner;

public class Cube {

    public static void main(String[] args) {
        //Variables  
         int a; //a represents the smaller number
         int b; //b represents the higher number. We are to assume that both numbers are going to be whole positive numbers.  

    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to Cubic Program!"); //welcome massage
    System.out.println("Please enter the smaller '(+)'number: "); //this is the prompt for user to enter the first whole positive number
    a = in.nextInt();
    System.out.print("Please enter the higher '(+)'number: "); //This is the prompt for user to enter 2nd number which is higher than the first number
    b = in.nextInt();
        
    for(a=a;a<=b;a++) //this means that 'a' is the first number where the calculation will start. "a" is smaller than "b". a++ means increment by 1.
        {
            System.out.println("Number is : " +a+" and cube of " +a+" is : "+(a*a*a));// this prints out the final output.
        }
    }
             
    }