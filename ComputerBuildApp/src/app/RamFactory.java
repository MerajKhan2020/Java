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
public class RamFactory {
      private Map<String, Double> Rams;
	

	
	public RamFactory() {
		Rams = new HashMap<String, Double>();
        }
    
        public void SelectRam (String RamType, double cost) {
		Rams.put(RamType, cost);
		
		
	}
        
        public Rams addRam (String ramType) {
           if (!Rams.containsKey(ramType)) throw new RuntimeException("Ram is Undefined");
	
                
		return new Rams(ramType, Rams.get(ramType));
	}

}
