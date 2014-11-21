package com.baristamatic.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.baristamatic.domain.Drink;

/**
 * <p>
 * Title: MemoryDrinkManagerDao.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public class MemoryDrinkManagerDao implements IDrinkManager {
	public Map<String, Drink> drinkMap;

	/**
	 * Instantiates a new memory drink manager dao.
	 */
	public MemoryDrinkManagerDao() {
		super();
	}

	/**
	 * Instantiates a new memory drink manager dao.
	 * 
	 * @param drinkMap the drink map
	 */
	public MemoryDrinkManagerDao(Map<String, Drink> drinkMap) {
		super();
		this.drinkMap = drinkMap;
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getAllDrinks()
	 */
	public List<Drink> getAllDrinks() {
		List<Drink> list = new ArrayList<Drink>(drinkMap.values());
		Collections.sort(list);
		return list;
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getDrink(java.lang.String)
	 */
	public Drink getDrink(String drinkName) {
		return drinkMap.get(drinkName);
	}

	/*
	 * @see com.baristamatic.dao.IDrinkManager#getDrink(java.lang.Integer)
	 */
	public Drink getDrink(Integer drinkNumber) {
		for (Map.Entry<String, Drink> ent : drinkMap.entrySet()) {
			Drink drink = ent.getValue();
			if (drink.getDrinkNumber().equals(drinkNumber)) {
				return drink;
			}
		}
		return null;
	}
}
