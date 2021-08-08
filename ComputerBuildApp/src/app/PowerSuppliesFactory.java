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
public class PowerSuppliesFactory {
      private Map<String, Double> PowerSupplies;
	

	
	public PowerSuppliesFactory() {
		PowerSupplies = new HashMap<String, Double>();
        }
    
        public void SelectCPUs (String PowerSuppliesTypes, double cost) {
		PowerSupplies.put(PowerSuppliesTypes, cost);
		
		
	}
        
        public PowerSupplies addPS (String powersupplyType) {
            if (!PowerSupplies.containsKey(powersupplyType)) throw new RuntimeException("Power Supply Undefined");
	
		
		return new PowerSupplies(powersupplyType, PowerSupplies.get(powersupplyType));
	}
     
}
