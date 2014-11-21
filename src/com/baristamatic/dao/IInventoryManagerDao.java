package com.baristamatic.dao;

import java.util.List;
import java.util.Map;

import com.baristamatic.domain.Ingredient;
import com.baristamatic.domain.InventoryEntry;
import com.baristamatic.exceptions.InventoryException;

/**
 * <p> Title: IInventoryManager.java </p>
 * <p> Description: Abstract inventory management from implementation
 * 
 * </p>
 * <p> 2008</p>
 * 
 * @author RHolland
 */ 
public interface IInventoryManagerDao {
	
	/**
	 * Gets the ingredient.
	 * 
	 * @param name the name
	 * 
	 * @return the ingredient
	 */
	public Ingredient getIngredient(String name);
	
	/**
	 * Consume ingredient.
	 * 
	 * @param name the name
	 * @param count the count
	 * 
	 * @return the ingredient
	 */
	public Ingredient consumeIngredient(String name, int count);
	
	/**
	 * Creates the drink.
	 * 
	 * @param ingredientMap the ingredient map
	 * 
	 * @throws InventoryException the inventory exception
	 */
	public void createDrink(Map<String, Integer> ingredientMap) throws InventoryException;
	
	/**
	 * Checks for ingredient count.
	 * 
	 * @param name the name
	 * @param requiredCount the required count
	 * 
	 * @return true, if successful
	 */
	public boolean hasIngredientCount(String name, int requiredCount);
	
	/**
	 * Checks for ingredient count.
	 * 
	 * @param ingredientMap the ingredient map
	 * 
	 * @return true, if successful
	 */
	public boolean hasIngredientCount(Map<String, Integer> ingredientMap);
	
	/**
	 * Gets the ingredient count.
	 * 
	 * @param name the name
	 * 
	 * @return the ingredient count
	 */
	public int getIngredientCount(String name);
	
	/**
	 * Gets the inventory entries.
	 * 
	 * @return the inventory entries
	 */
	public List<InventoryEntry> getInventoryEntries();

}
