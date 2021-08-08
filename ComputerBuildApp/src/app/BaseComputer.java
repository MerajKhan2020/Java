/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Meraj
 */
public class BaseComputer {
    
    private String basecomputer;
    private double cost;
	
    public BaseComputer (String base, double cost) {
        this.basecomputer = base;
	this.cost = cost;
	}

	
	public String getDescription() {
		return basecomputer;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getReceipt() {
		return String.format("%-30s", basecomputer + " Base Computer") + String.format("%5.2f", cost);
	}
   
            
   

    
}
