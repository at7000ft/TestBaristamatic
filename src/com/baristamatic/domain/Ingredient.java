package com.baristamatic.domain;

import java.math.BigDecimal;

/**
 * <p> Title: Ingredient.java </p>
 * <p> Description: Entity representing a drink ingredient and its cost.
 * 
 * </p>
 * <p> 2008</p>
 * 
 * @author RHolland
 */ 
public class Ingredient {
	private String name;
	private BigDecimal unitCost;

	/**
	 * Instantiates a new ingredient.
	 */
	public Ingredient() {
		super();
	}
	
	/**
	 * The Constructor.
	 * 
	 * @param name the name
	 * @param unitCost the unit cost
	 */
	public Ingredient(String name, BigDecimal unitCost) {
		super();
		this.name = name;
		this.unitCost = unitCost;
	}
	

	/**
	 * Gets the unit cost.
	 * 
	 * @return the unit cost
	 */
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	
	/**
	 * Sets the unit cost.
	 * 
	 * @param unitCost the new unit cost
	 */
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
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

}
