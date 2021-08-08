/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Meraj
 */
public class MotherboardFactory {
    private Map<String, Double> MotherBoard;
	

	
	public MotherboardFactory() {
		MotherBoard = new HashMap<String, Double>();
	}
    
        public void SelectMotherboard (String MotherboardType, double cost) {
		MotherBoard.put(MotherboardType, cost);
		
		
	}
        public MotherBoard addMB (String MBType) {
            if (!MotherBoard.containsKey(MBType)) throw new RuntimeException("Mother Board undefined");
	
		
		return new MotherBoard(MBType, MotherBoard.get(MBType));
	}
     
}
