package com.baristamatic.dao;

import java.util.Collection;
import java.util.List;

import com.baristamatic.domain.Drink;

/**
 * <p>Title: Interface IDrinkManager. </p>
 * 
 * <p> Description: Abstract Drink management from implementation
 * 
 * </p>
 * <p> 2008</p>
 * 
 * @author RHolland
 */
public interface IDrinkManager {
	
	/**
	 * Gets the all drinks.
	 * 
	 * @return the all drinks
	 */
	public List<Drink> getAllDrinks();
	
	/**
	 * Gets the drink.
	 * 
	 * @param drinkName the drink name
	 * 
	 * @return the drink
	 */
	public Drink getDrink(String drinkName);
	
	/**
	 * Gets the drink.
	 * 
	 * @param drinkNumber the drink number
	 * 
	 * @return the drink
	 */
	public Drink getDrink(Integer drinkNumber);

}
