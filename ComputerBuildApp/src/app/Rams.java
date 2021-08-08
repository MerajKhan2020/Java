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
public class Rams {
        private String Rams;
	private double cost;
	
	public Rams (String ramType, double cost) {
		this.Rams = ramType;
		this.cost = cost;
	}

	public String getDescription() {
		return Rams;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getReceipt() {
		return String.format("%-30s", Rams + " Rams") + String.format("%5.2f", cost);
	}
        
        
        
}
