/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Meraj
 */
public class ComputerStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          
        BaseComputerFactory G = new BaseComputerFactory();
        G.SelectBaseComputer("Base 1", 0.0);
        G.SelectBaseComputer("a", 0.0);
        
        CasesFactory B = new CasesFactory();
        B.SelectCases("DeadCool Tesseract Mid-Tower", 45.00);
        B.SelectCases("Antek VSK4000 Black Mid-Tower", 55.00);
        B.SelectCases("b", 55.00);
        
        
        MotherboardFactory A = new MotherboardFactory();
        A.SelectMotherboard("AsRock Z370 Pro", 165.00);
        A.SelectMotherboard("MSI Z370 Gaming Plus", 200.00);
        A.SelectMotherboard("c", 200.00);
        
        
        
        CPUsFactory C = new CPUsFactory();
        C.SelectCPUs("Intel i3 8100", 155);
        C.SelectCPUs("Intel i5 8400", 250);
        C.SelectCPUs("d", 250);
        
        PowerSuppliesFactory D = new PowerSuppliesFactory();
        D.SelectCPUs("Antec VP-600(600W)", 80);
        D.SelectCPUs("Gigabyte B700H (700W)", 100);
        D.SelectCPUs("e", 100);
        
        RamFactory E = new RamFactory();
        E.SelectRam("8GB (2X4) Kinstone HyperX Fury DDR4 2666Mhz", 150);
        E.SelectRam("8GB (2X4) Kinstone Corsair DDR4 2666Mhz", 0);
        E.SelectRam("f", 0);
        
        StorageFactory F = new StorageFactory();
        F.SelectStorage("Seagate ST10000DM010 1TB Barracuda SATA3", 64);
        F.SelectStorage("Crucial Mx500 500GB M.2", 175);
        F.SelectStorage("g", 175);
        
        
       
        
        OptionalFactory H = new OptionalFactory();
        H.SelectOptional("Gigabyte GeForce GTX 1050 2GB", 190);
        H.SelectOptional("Sapphire Radeon 560 2GB", 225);
        H.SelectOptional("h", 190);
        H.SelectOptional("i", 225);
        
        
        ComputerBuilder computerBuilder = new ComputerBuilder(G,B,A,C,D,E,F,H);

	Scanner scanner = new Scanner(System.in);
		
	while (true) {
            
                    try {
                         //basecomputer,cases,motherboard,cpus,powersupplies,Rams,storage
                                System.out.println("Enter Base: ");
				String basecomputer = scanner.nextLine();
				
				System.out.println("Enter Case: ");
				String cases = scanner.nextLine();
				
				System.out.println("Enter MotherBoard: ");
				String motherboard = scanner.nextLine();
                                
                                System.out.println("Enter CPUs: ");
				String cpus = scanner.nextLine();
                                
                                System.out.println("Enter Power Supply: ");
				String powersupplies = scanner.nextLine();
                                
                                System.out.println("Enter RAM: ");
				String Rams = scanner.nextLine();
                                
                                System.out.println("Enter Storage: ");
				String storage = scanner.nextLine();
                                
				
				List<String> optionals = new ArrayList<>();
				while (true) {
					System.out.println("Enter Optional Choice: ");
					String optional = scanner.nextLine();
					optionals.add(optional);
					
					System.out.println("Another? ");
					String ans = scanner.nextLine();
					if (!ans.toLowerCase().equals("y")) break;

                                       }
                        
                    
				
			IComputer computer = computerBuilder.setCases(cases).setMB(storage).setCpus(cpus).setPowersupplies(storage).setRams(Rams).addOptionals(optionals).setBaseComputer(cases).build();
			
			System.out.println(computer.getDescription());
			System.out.println(computer.getReceipt());
			System.out.println(String.format("%-30s%5.2f", "Total", computer.getCost()));
				
			System.out.println("Continue? ");
			String ans = scanner.nextLine();
			if (!ans.toLowerCase().equals("y")) break;
				
			}
                    catch (RuntimeException rte) {
				System.err.println(rte);
                        }
		
			
			
	}
        
       
    }
    
}
