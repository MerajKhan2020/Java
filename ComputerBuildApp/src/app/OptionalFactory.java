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
public class OptionalFactory {
        private Map<String, Double> optional;
	

	
	public OptionalFactory() {
		optional = new HashMap<String, Double>();
	}
    
        public void SelectOptional (String optionatType, double cost) {
		optional.put(optionatType, cost);
		
		
	}
        
        public IComputer addOptional (String optionalType, IComputer computer) {
            if (!optional.containsKey(optionalType)) throw new RuntimeException("No Optional device was chosen");
            return new Optional(optionalType, optional.get(optionalType), computer);
	}
     
        
}
