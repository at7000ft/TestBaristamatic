package com.baristamatic.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.baristamatic.domain.Ingredient;
import com.baristamatic.domain.InventoryEntry;
import com.baristamatic.exceptions.InventoryException;

/**
 * <p>
 * Title: InventoryManager.java
 * </p>
 * <p>
 * Description: Memory based. Assumes single thread access so no synchronization is necessary
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public class MemoryInventoryManagerDao implements IInventoryManagerDao {

	private Map<String, InventoryEntry> ingredientMap;

	/**
	 * Instantiates a new memory inventory manager dao.
	 */
	public MemoryInventoryManagerDao() {
		super();
	}

	/**
	 * The Constructor.
	 * 
	 * @param ingredientMap the ingredient map
	 */
	public MemoryInventoryManagerDao(Map<String, InventoryEntry> ingredientMap) {
		super();
		this.ingredientMap = ingredientMap;
	}

	/* 
	 * @see com.baristamatic.dao.IInventoryManagerDao#getIngredientCount(java.lang.String)
	 */
	public int getIngredientCount(String name) {
		return ingredientMap.get(name).currentInventoryCount();
	}

	/* 
	 * @see com.baristamatic.dao.IInventoryManagerDao#getIngredient(java.lang.String)
	 */
	public Ingredient getIngredient(String name) {
		InventoryEntry entry = ingredientMap.get(name);
		if (entry == null) {
			return null;
		}
		return entry.getIngredient();

	}

	/* 
	 * @see com.baristamatic.dao.IInventoryManagerDao#hasIngredientCount(java.lang.String, int)
	 */
	public boolean hasIngredientCount(String name, int requiredCount) {
		return (ingredientMap.get(name).currentInventoryCount() >= requiredCount);
	}

	/*
	 * @see com.baristamatic.model.IInventoryManager#hasIngredientCount(java.util.Map)
	 */
	/* 
	 * @see com.baristamatic.dao.IInventoryManagerDao#hasIngredientCount(java.util.Map)
	 */
	public boolean hasIngredientCount(Map<String, Integer> ingredientMap) {
		for (Map.Entry<String, Integer> ent : ingredientMap.entrySet()) {
			int count = ent.getValue();
			Ingredient ingredient = getIngredient(ent.getKey());
			if (!hasIngredientCount(ingredient.getName(), count)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * @see com.baristamatic.model.IInventoryManager#getInventoryEntries()
	 */
	/* 
	 * @see com.baristamatic.dao.IInventoryManagerDao#getInventoryEntries()
	 */
	public List<InventoryEntry> getInventoryEntries() {
		List<InventoryEntry> list = new ArrayList<InventoryEntry>(ingredientMap.values());
		Collections.sort(list);

		return list;
	}

	/*
	 * @see com.baristamatic.model.IInventoryManager#consumeIngredient(java.lang.String)
	 */
	/* 
	 * @see com.baristamatic.dao.IInventoryManagerDao#consumeIngredient(java.lang.String, int)
	 */
	public Ingredient consumeIngredient(String name, int count) {
		return ingredientMap.get(name).consumeIngredient(name, count);
	}

	/*
	 * @see com.baristamatic.dao.IInventoryManagerDao#createDrink(java.util.Map)
	 * Would want to declare this method synchronized concurrent access by multiple threads is required.
	 */
	public void createDrink(Map<String, Integer> ingredientMap) throws InventoryException {
		if (!hasIngredientCount(ingredientMap)) {
			throw new InventoryException("Insufficient Ingredients");
		}
		for (Map.Entry<String, Integer> ent : ingredientMap.entrySet()) {
			consumeIngredient(ent.getKey(), ent.getValue());
		}
	}
}
