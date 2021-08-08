//Student Name: MD Meraj Khan
//Student ID: mkhan85
//Assesment 2: Task2
//Class: Test

package test;


public class Test {
    public static void main (String[] args){
        Circle2D c1 = new Circle2D(2,2,2.5);
        
        
       
        System.out.println("Area is " + c1.getArea());
        System.out.println("Perimeter is " + c1.getPerimeter());
        System.out.println("c1 contains point (3, 3)? " + c1.contains(3,3));
        System.out.println("c1 contains circle Circle2D?(4,5,8.5) " + c1.contains(new Circle2D(4,5,8.5)));
        System.out.println( "c1 overlaps circle Circle2D?(3,5,0.3) " + c1.overlaps(new Circle2D(3,5,0.3)));
        
        
        
    }
    
    
}