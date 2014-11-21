package com.baristamatic.test;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baristamatic.dao.Constants;
import com.baristamatic.dao.IDrinkManager;
import com.baristamatic.dao.IInventoryManagerDao;
import com.baristamatic.domain.Drink;
import com.baristamatic.exceptions.InventoryException;

/**
 * <p>Title: Class TestDrink. </p>
 * 
 * <p> Description:
 * 
 * </p>
 * <p> 2008</p>
 * 
 * @author RHolland
 */
public class TestDrink extends TestBase {
	private static Logger log = Logger.getLogger(TestDrink.class);
	private ApplicationContext context;
	private IInventoryManagerDao inventoryManager;
	private IDrinkManager drinkManager;

	/* 
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() {
		context = new ClassPathXmlApplicationContext("InventoryManagerContext.xml");
		inventoryManager = (IInventoryManagerDao) context.getBean("inventoryManager");
		drinkManager = (IDrinkManager) context.getBean("drinkManager");
	}

	/**
	 * Test drink creation.
	 */
	public void testDrinkCreation() {
		Drink coffeeDrink = drinkManager.getDrink(1);
		log.debug("coffeeDrink - " + coffeeDrink.toString());
		try {
			coffeeDrink.createDrink();
		} catch (InventoryException e) {
			fail("Drink create error - " + e);
		}

		dumpInventory(inventoryManager, log);
		assertEquals(7, inventoryManager.getIngredientCount(Constants.COFFEE_INGREDIENT));
	}
	
	/**
	 * Test drink cost.
	 */
	public void testDrinkCost() {
		assertEquals(new BigDecimal("3.30"), drinkManager.getDrink(4).getCost());
		assertEquals(new BigDecimal("2.55"), drinkManager.getDrink(3).getCost());
		assertEquals(new BigDecimal("3.35"), drinkManager.getDrink(5).getCost());
		assertEquals(new BigDecimal("2.90"), drinkManager.getDrink(6).getCost());
		assertEquals(new BigDecimal("2.75"), drinkManager.getDrink(1).getCost());
		assertEquals(new BigDecimal("2.75"), drinkManager.getDrink(2).getCost());
	}
}
