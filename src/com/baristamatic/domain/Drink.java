package com.baristamatic.domain;

import java.math.BigDecimal;
import java.util.Map;

import com.baristamatic.dao.IInventoryManagerDao;
import com.baristamatic.exceptions.InventoryException;

/**
 * <p>
 * Title: Drink.java
 * </p>
 * <p>
 * Description: Entity representing a drink including it's ingredientMap recipe, name and number.
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public class Drink implements Comparable<Drink>{
	protected Integer drinkNumber;
	protected String name;
	protected String displayName;
	protected IInventoryManagerDao inventoryManager;
	protected Map<String, Integer> ingredientMap;
	

	/**
	 * Instantiates a new drink.
	 */
	public Drink() {
		super();
	}

	/**
	 * The Constructor.
	 * 
	 * @param name the name
	 * @param displayName the display name
	 * @param inventoryManager the inventory manager
	 * @param ingredientMap the ingredient map
	 * @param drinkNumber the drink number
	 */
	public Drink(Integer drinkNumber, String name, String displayName, IInventoryManagerDao inventoryManager,Map<String, Integer> ingredientMap) {
		this.drinkNumber = drinkNumber;
		this.name = name;
		this.displayName = displayName;
		this.inventoryManager = inventoryManager;
		this.ingredientMap = ingredientMap;
	}

	/**
	 * Creates the drink.
	 * 
	 * @throws InventoryException the inventory exception
	 */
	public void createDrink() throws InventoryException {
		inventoryManager.createDrink(ingredientMap);
	}

	/**
	 * Gets the cost.
	 * 
	 * @return the cost
	 */
	public BigDecimal getCost() {
		BigDecimal totalCost = new BigDecimal(0);
		for (Map.Entry<String, Integer> ent : ingredientMap.entrySet()) {
			int count = ent.getValue();
			Ingredient ingredient = inventoryManager.getIngredient(ent.getKey());
			BigDecimal ingredientCost = ingredient.getUnitCost().multiply(new BigDecimal(count));
			totalCost = totalCost.add(ingredientCost);
		}
		return totalCost;
	}
	
	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return drinkNumber.toString() + "," + displayName + ",$" + getCost() + ","
					+ inventoryManager.hasIngredientCount(ingredientMap);

	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the display name.
	 * 
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 * 
	 * @param displayName the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the drink number.
	 * 
	 * @return the drink number
	 */
	public Integer getDrinkNumber() {
		return drinkNumber;
	}

	/**
	 * Sets the drink number.
	 * 
	 * @param drinkNumber the new drink number
	 */
	public void setDrinkNumber(Integer drinkNumber) {
		this.drinkNumber = drinkNumber;
	}

	/* 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Drink other) {
		return drinkNumber.compareTo(other.getDrinkNumber());
	}

}
