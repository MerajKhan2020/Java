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
public class Computer implements IComputer{
	
	private Cases cases;
	private MotherBoard motherboard;
	private CPUs cpus;
        private PowerSupplies powersupplies;
        private Rams Rams;
        private Storage storage;
        private BaseComputer basecomputer;
        
        
	
	public Computer (BaseComputer basecomputer, Cases cases,MotherBoard motherboard,CPUs cpus,PowerSupplies powersupplies,Rams Rams,Storage storage) {
		
		this.cases = cases;
		this.motherboard = motherboard;
		this.cpus = cpus;
                this.powersupplies = powersupplies;
                this.Rams = Rams;
                this.storage = storage;
                this.basecomputer = basecomputer;
                
		
	}
	
	public String getDescription() {
		return basecomputer.getDescription()+"/n"+ cases.getDescription() + "/n"+ motherboard.getDescription()+ "/n"+cpus.getDescription()+"/n"+powersupplies.getDescription()
                       + "/n"+Rams.getDescription()+"/n"+storage.getDescription();
	}
	
	public double getCost() {
		return basecomputer.getCost()+cases.getCost()+motherboard.getCost()+cpus.getCost()+powersupplies.getCost()+Rams.getCost()+storage.getCost();
	}
	
	public String getReceipt() {
		return   basecomputer.getReceipt()+"/n"+ cases.getReceipt() + "\n" + motherboard.getReceipt() + "\n" + cpus.getReceipt()+ "\n" + powersupplies.getReceipt()+ "\n" + Rams.getReceipt()+ "\n" + storage.getReceipt();
	}
}
