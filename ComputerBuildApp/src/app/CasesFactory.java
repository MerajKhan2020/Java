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
public class CasesFactory {
       private Map<String, Double> Cases;
	

	
	public CasesFactory() {
		Cases = new HashMap<String, Double>();
        }
    
        public void SelectCases (String CasesType, double cost) {
		Cases.put(CasesType, cost);
		
		
	}
        
        public Cases addCase (String caseType) {
            if (!Cases.containsKey(caseType)) throw new RuntimeException("Case undefined");
	
		
		return new Cases(caseType, Cases.get(caseType));
	}
     
}
