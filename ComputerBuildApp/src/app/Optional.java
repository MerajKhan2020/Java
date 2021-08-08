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
public class Optional implements IComputer{
    String description;
    double cost;
    IComputer computer;
	
	public Optional (String description, double cost, IComputer computer) {
		this.description = description;
		this.cost = cost;
                this.computer = computer;
	}

	public String getDescription() {
		return description+ ", " + computer.getDescription();
	}
	
	public double getCost() {
		return cost+computer.getCost();
	}
	
	public String getReceipt() {
		String receiptLine =  String.format("%-30s", description ) + String.format("%5.2f", cost);
		return receiptLine + "\n" + computer.getReceipt();
	}
}
