package com.baristamatic.domain;

/**
 * <p>
 * Title: InventoryEntry.java
 * </p>
 * <p>
 * Description: Entity container for Inventory Ingredients, maintains unit counts.
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public class InventoryEntry implements Comparable<InventoryEntry> {
	private String displayName;
	private String name;
	private Ingredient ingredient;
	private int count;

	/**
	 * Instantiates a new inventory entry.
	 */
	public InventoryEntry() {
		super();
	}

	/**
	 * The Constructor.
	 * 
	 * @param name the name
	 * @param displayName the display name
	 * @param ingredient the ingredient
	 * @param count the count
	 */
	public InventoryEntry(String displayName, String name, Ingredient ingredient, int count) {
		super();
		this.displayName = displayName;
		this.name = name;
		this.ingredient = ingredient;
		this.count = count;

	}

	/**
	 * Gets the ingredient count.
	 * 
	 * @return the ingredient count
	 */
	public int getIngredientCount() {
		return count;
	}

	/**
	 * Consume ingredient.
	 * 
	 * @param name the name
	 * @param consumeCount the consume count
	 * 
	 * @return the ingredient
	 */
	public Ingredient consumeIngredient(String name, int consumeCount) {
		if (count > 0) {
			count = count - consumeCount;
			return ingredient;
		}
		return null;
	}

	/**
	 * Checks for ingredient count.
	 * 
	 * @param requiredCount the required count
	 * 
	 * @return true, if successful
	 */
	public boolean hasIngredientCount(int requiredCount) {
		return (count >= requiredCount);
	}

	/**
	 * Current inventory count.
	 * 
	 * @return the int
	 */
	public int currentInventoryCount() {
		return count;
	}

	/**
	 * Gets the count.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 * 
	 * @param count the new count
	 */
	public void setCount(int count) {
		this.count = count;
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
	 * Sets the ingredient.
	 * 
	 * @param ingredient the new ingredient
	 */
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	/**
	 * Gets the ingredient.
	 * 
	 * @return the ingredient
	 */
	public Ingredient getIngredient() {
		return ingredient;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return displayName + "," + count;
	}

	/*
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(InventoryEntry other) {
		return name.compareTo(other.getName());
	}

}
