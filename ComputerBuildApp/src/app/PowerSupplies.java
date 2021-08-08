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
public class PowerSupplies {
    private String powersupplyType;
	private double cost;
	
	public PowerSupplies (String PowerSupplies, double cost) {
		this.powersupplyType = PowerSupplies;
		this.cost = cost;
	}

	public String getDescription() {
		return powersupplyType;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getReceipt() {
		return String.format("%-30s", powersupplyType + " Power Supplies") + String.format("%5.2f", cost);
	}
}
