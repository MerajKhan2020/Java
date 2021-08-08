//Student Name: MD Meraj Khan
//Student ID: mkhan85
//Assesment 1: Task2

package entryfee;

import java.util.Scanner;

public class EntryFee {
    //variables
         
    public static void main(String[] args) {
        //Below is the list of variables that we will use. 
        int numChild; //number of child tickets
        int numAdult; //number of adult tickets
        int numSenior; //number of senior tickets
        double ticketTotal; //total price of all tickets in a single group
        int totalAdults; //total number of adult (adults = seniors). used later in calculation
        
        int childPriceA = 5; //this is full ticket price for an unaccompained child
        int childPriceB = 2; //this is ticket price for a child accompained by an adult
        int adultPrice = 10; // full ticket price of an adult
        int seniorPrice = 8; // full price of a senior person
        int grandTotal = 0; //grand total. this is to calculate the total amount of tickets sold at the end of the day. 
        //Price difference between Full Children and accompanied children tickets = $3. we will use this figure later at the calculation. 
          
        Scanner scan = new Scanner(System.in);        
        boolean groupChoice = true;
        while (groupChoice){ //using while loop to calculate multiple user entry.
            System.out.println("Enter a group?" + "(Yes=1/No=0):");
            int userInt = scan.nextInt();
            if (userInt == 1) 
            {
                System.out.println("Enter the Number of children (age 6-15):");
                numChild = scan.nextInt();
                System.out.println("Enter the Number of Adults (age 16-59):");
                numAdult = scan.nextInt();
                System.out.println("Enter the Number of Seniors (age 60+):");
                numSenior = scan.nextInt();
                
                totalAdults = (numAdult + numSenior);   // we are calculating total number of adutls so that we can use it later at the primary calculation below.         
                          
                if (numChild > totalAdults)
                    ticketTotal = (((numChild * childPriceA)- (totalAdults * 3))+ (numAdult * adultPrice)+ (numSenior * seniorPrice)) ;
                else
                    ticketTotal = ((numChild * childPriceB)+
                    (numAdult * adultPrice)+ (numSenior * seniorPrice));
            
            System.out.println("total entry charge is "+ ticketTotal); // this is the total price of a single group
            grandTotal += ticketTotal; //passes on the ticket total to be stored into the memory untill user decides to end transaction.
            } 
            
            if (userInt == 0)
            {
            groupChoice = false; // if user selects this option, the while loop ends and program calculates the grand total. 
            }         
                       
        }
        
    System.out.println("Total takings " + grandTotal);  // this is where user gets the grand total of all the group sale tickets.                  
        
    }
    
}
