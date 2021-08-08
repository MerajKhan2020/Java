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
public class CPUs {
        private String cpusType;
	private double cost;
	
	public CPUs (String CPUs, double cost) {
		this.cpusType = CPUs;
		this.cost = cost;
	}

	public String getDescription() {
		return cpusType;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getReceipt() {
		return String.format("%-30s", cpusType + " Mother Board") + String.format("%5.2f", cost);
	}
}
