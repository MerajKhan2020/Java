/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.List;

/**
 *
 * @author Meraj
 */
public class ComputerBuilder {
    
    private BaseComputerFactory basecomputerFactory;
    private CasesFactory casesFactory;
    private MotherboardFactory motherboardFactory;
    private CPUsFactory cpusFactory;
    private PowerSuppliesFactory powersuppliesFactory;
    private RamFactory ramFactory;
    private StorageFactory storageFactory;
    private OptionalFactory optionalFactory;
    
    private BaseComputer basecomputer;
    private Cases cases;
    private MotherBoard motherboard;
    private CPUs cpus;
    private PowerSupplies powersupplies;
    private Rams Rams;
    private Storage storage;
    
    private List<String> optionals;
    
    public ComputerBuilder (BaseComputerFactory basecomputerFactory,  CasesFactory casesFactory,
    MotherboardFactory motherboardFactory,
    CPUsFactory cpusFactory,
    PowerSuppliesFactory powersuppliesFactory,
    RamFactory ramFactory,
    StorageFactory storageFactory,
    OptionalFactory optionalFactory){
        this.motherboardFactory = motherboardFactory;
        this.casesFactory = casesFactory;
        this.cpusFactory = cpusFactory;
        this.powersuppliesFactory = powersuppliesFactory;
        this.ramFactory = ramFactory;
        this.storageFactory = storageFactory;
        this.basecomputerFactory = basecomputerFactory;
        this.optionalFactory = optionalFactory;
    }
    
    	public IComputer build() {
		IComputer computer = new Computer(basecomputer,cases,motherboard,cpus,powersupplies,Rams,storage);
		
		for (String t : optionals) {
			computer = optionalFactory.addOptional(t, computer);
		}
		return computer;
	}
	
	
	public ComputerBuilder setBaseComputer(String baseString) {
		basecomputer = basecomputerFactory.addBase(baseString);
		return this;
	}

	public ComputerBuilder setCases(String casesString) {
		cases = casesFactory.addCase(casesString);
		return this;
	}

	public ComputerBuilder setMB(String mbString) {
		motherboard = motherboardFactory.addMB(mbString);
		return this;
	}

	public ComputerBuilder addOptionals (List<String> optional) {
		this.optionals = optional;
		return this;
	}
        
        public ComputerBuilder setCpus(String cpusString) {
		cpus = cpusFactory.addCPU(cpusString);
		return this;
	}
        
        public ComputerBuilder setPowersupplies(String PsString) {
		powersupplies = powersuppliesFactory.addPS(PsString);
		return this;
	}
        
        public ComputerBuilder setRams(String ramString) {
		Rams = ramFactory.addRam(ramString);
		return this;
	}
        
        public ComputerBuilder setStorage(String storageString) {
		storage = storageFactory.addStorage(storageString);
		return this;
	}
    
    
    
}
