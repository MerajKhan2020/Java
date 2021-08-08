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
public class BaseComputerFactory {
    
       private Map<String, Double> basecomputer;
	

	
	public BaseComputerFactory() {
		basecomputer = new HashMap<String, Double>();
        }
    
        public void SelectBaseComputer (String BaseComputerType, double cost) {
		basecomputer.put(BaseComputerType, cost);
		
		
	}
        
        
        public BaseComputer addBase (String BaseComputerType) {
		
		if (!basecomputer.containsKey(BaseComputerType)) throw new RuntimeException("Base undefined");

		
		
		return new BaseComputer(BaseComputerType, basecomputer.get(BaseComputerType));
	}
}
