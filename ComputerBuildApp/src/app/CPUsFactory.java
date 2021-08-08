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
public class CPUsFactory {
        private Map<String, Double> CPUs;
	

	
	public CPUsFactory() {
		CPUs = new HashMap<String, Double>();
        }
    
        public void SelectCPUs (String CPUstypes, double cost) {
		CPUs.put(CPUstypes, cost);
		
		
	}
         public CPUs addCPU (String cpusType) {
		
		if (!CPUs.containsKey(cpusType)) throw new RuntimeException("CPU undefined");

		
		return new CPUs(cpusType, CPUs.get(cpusType));
	}
}
