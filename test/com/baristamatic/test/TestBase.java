package com.baristamatic.test;

import java.util.Collection;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.baristamatic.dao.IInventoryManagerDao;
import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;

/**
 * <p> Title: TestBase.java </p>
 * <p> Description:
 * 
 * </p>
 * <p> 2008</p>
 * 
 * @author RHolland
 */ 
public abstract class TestBase extends TestCase {
	
	/**
	 * Dump inventory.
	 * 
	 * @param inventoryManager the inventory manager
	 * @param log the log
	 */
	public void dumpInventory(IInventoryManagerDao inventoryManager, Logger log) {
		for (InventoryEntry entry : inventoryManager.getInventoryEntries()) {
			log.debug("dumpInventory: inventory - " + entry.toString());
		}
	}
	
	/**
	 * Dump drinks.
	 * 
	 * @param drinkList the drink list
	 * @param log the log
	 */
	public void dumpDrinks(Collection<Drink> drinkList, Logger log) {
		for (Drink drink : drinkList) {
			log.debug("testInventoryManager: drink - " + drink.toString());
		}
	}

}
