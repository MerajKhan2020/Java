
//Student Name: MD Meraj Khan
//Student ID: mkhan85
//Assesment 2: Task2
//Class: Circle2D

package test;
 

public class Circle2D {
    
    private double x;
    private double y;
    private double radius;
    
   
    public double getx(){
        return x;
    }        
    public double gety(){
        return y;
    }        
    public double getradius(){
        return radius;
    }   
    
 //A no-arg constructor that creates a default circle with (0, 0) for (x, y) and 3 for radius.           
    public Circle2D(){
        this(0,0,3);
    }
    
//A constructor that constructs a circle with the specified x, y, and radius.    
    public Circle2D (double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
//A method getArea() that returns the area of the circle
    public double getArea(){
        return Math.PI*Math.pow(this.radius,2);
    }

//A method getPerimeter() that returns the perimeter of the circle    
    public double getPerimeter (){
        return Math.PI*2*(this.radius);
    }

//A method contains(double x, double y) that returns true if the specified point (x, y) is inside this circle    


    public boolean contains(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) 
                < this.radius;
    }
    
//A method contains(Circle2D circle) that returns true if the specified circle is inside this circle

    public boolean contains(Circle2D circle) {
        return Math.sqrt(Math.pow(circle.getx() - x, 2) + 
				 Math.pow(circle.gety() - y, 2)) <= Math.abs(radius);
    }


//A method overlaps(Circle2D circle) that returns true if the specified circle overlaps with this circle.    
    
    public boolean overlaps (Circle2D circle) {
        return Math.sqrt(Math.pow(circle.getx() - x, 2) + 
				 Math.pow(circle.gety() - y, 2)) 
				 <= Math.abs(radius + circle.getradius());
    }
}