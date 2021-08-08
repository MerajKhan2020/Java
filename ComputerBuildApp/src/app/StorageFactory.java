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
public class StorageFactory {
    private Map<String, Double> Storage;
	

	
	public StorageFactory() {
		Storage = new HashMap<String, Double>();
        }
        
        public void SelectStorage (String StorageType, double cost) {
		Storage.put(StorageType, cost);
		
		
	}
    
        public Storage addStorage (String storageType) {
           if (!Storage.containsKey(storageType)) throw new RuntimeException("Storage Undefined");
	
                double cost = Storage.get(storageType);
		return new Storage(storageType, Storage.get(storageType));
	}

}
