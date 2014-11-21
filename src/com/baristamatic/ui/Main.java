package com.baristamatic.ui;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baristamatic.dao.IDrinkManager;
import com.baristamatic.dao.IInventoryManagerDao;
import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;
import com.baristamatic.exceptions.InventoryException;

/**
 * <p>
 * Title: Main.java
 * </p>
 * <p>
 * Description: Main console interface
 * 
 * </p>
 * <p>
 * 2008
 * </p>
 * 
 * @author RHolland
 */
public class Main {
	private ApplicationContext context;
	private IInventoryManagerDao inventoryManager;
	private IDrinkManager drinkManager;
	// private static final char restockCommandChar = 'r';
	// private static final char quitCommandChar = 'q';
	private static final String restockCommandString = "r";
	private static final String quitCommandString = "q";
	private List<String> validDrinkNumbers;
	private static final List<String> validCommands = Arrays.asList(restockCommandString, quitCommandString);

	/**
	 * Inits.
	 */
	private void init() {
		// Suppress Spring log messages on console
		DOMConfigurator.configure("config/log4j.xml");

		// Spring configure a IInventoryManagerDao and IDrinkManager
		context = new ClassPathXmlApplicationContext("InventoryManagerContext.xml");
		inventoryManager = (IInventoryManagerDao) context.getBean("inventoryManager");
		drinkManager = (IDrinkManager) context.getBean("drinkManager");
		initValidDrinkNumbers(drinkManager.getAllDrinks());
	}

	/**
	 * Main loop.
	 */
	private void mainLoop() {
		String commandString = "";
	 
		// ... Initialize Scanner to read from console.
		Scanner input = new Scanner(System.in);

		while (!commandString.equals(quitCommandString)) {
			showInventory();
			showMenu();
			out.println("\n");

			try {
				// Get input
				commandString = input.nextLine().trim();

				//Test for valid entry
				if (!isValidEntry(commandString)) {
					out.println("Invalid selection: " + commandString + "\n");
					continue;
				}

				// Test for quit command
				if (commandString.equals(quitCommandString)) {
					continue;
				}

				// Test for restock command
				if (commandString.equals(restockCommandString)) {
					init();
					continue;
				}

				// If a valid drink number was entered attempt to dispense a drink
				Drink requestedDrink = drinkManager.getDrink(new Integer(commandString));
				if (requestedDrink == null) {
					out.println("Out of stock: " + commandString + "\n");
					continue;
				}
				try {
					requestedDrink.createDrink();
					out.println("Dispensing: " + requestedDrink.getDisplayName() + "\n");
				} catch (InventoryException e) {
					out.println("Out of stock: " + requestedDrink.getDisplayName() + "\n");
					continue;
				}
			} catch (StringIndexOutOfBoundsException e) {
				out.println("Invalid selection: " + commandString + "\n");
				continue;
			} catch (NoSuchElementException e) {
				out.println("Invalid selection: " + commandString + "\n");
				continue;
			}
		}

		System.exit(0);
	}

	/**
	 * Show inventory.
	 */
	private void showInventory() {
		out.println("Inventory:");
		List<InventoryEntry> list = inventoryManager.getInventoryEntries();
		for (InventoryEntry entry : list) {
			out.println(entry.toString());
		}
	}

	/**
	 * Show menu.
	 */
	private void showMenu() {
		out.println("\nMenu:");
		for (Drink drink : drinkManager.getAllDrinks()) {
			out.println(drink.toString());
		}
	}

	private void initValidDrinkNumbers(List<Drink> drinks) {
		validDrinkNumbers = new ArrayList<String>();
		for (Drink drink : drinks) {
			String number = drink.getDrinkNumber().toString();
			validDrinkNumbers.add(number);
		}
	}

	private boolean isValidEntry(String entry) {
		if (entry.length() == 1 && validCommands.contains(entry)) {
			return true;
		} else if (testForDigits(entry) && validDrinkNumbers.contains(entry)) {
			return true;
		} else {
			return false;
		}
	}

	private boolean testForDigits(String testStr) {
		boolean stat = true;
		for (int i = 0; i < testStr.length(); i++) {
			if (!Character.isDigit(testStr.charAt(i))) {
				stat = false;
				break;
			}
		}
		//System.out.println("testForDigits: isDigits - " + stat);
		return stat;
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		main.mainLoop();
	}
}
