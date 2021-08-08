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
public class Cases {
        private String caseType;
	private double cost;
	
	public Cases (String Cases, double cost) {
		this.caseType = Cases;
		this.cost = cost;
	}

	public String getDescription() {
		return caseType;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String getReceipt() {
		return String.format("%-30s", caseType + " Cases") + String.format("%5.2f", cost);
	}
}
