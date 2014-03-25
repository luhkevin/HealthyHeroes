/**
 * 
 */
package com.example.healthyheroes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The session class stores information about a given session.
 */
public class Session {
	private final String	filename;		
	private final String[] 	participants;
	private final Date 	 	date;
	private final double 	initial_cash;
	private double 	  		cashbox;
	
	private HashMap<String, FoodItem> ingredients;
	private HashMap<String, FoodItem> products;
	
	/**
	 * Constructor to be used for new sessions 
	 * @param participants
	 * @param cashbox
	 */
	public Session(String[] participants, double initial_cash){
		this.participants = participants;
		this.date 		  = new Date();
		this.initial_cash = initial_cash;
		this.cashbox 	  = initial_cash;
		this.filename 	  = "CurrentSession.session";
		
		this.ingredients = new HashMap<String, FoodItem>();
		this.products 	 = new HashMap<String, FoodItem>();
	}
	
	/**
	 * Constructor for loading from a csv file
	 * @param filename
	 */
	public Session(String filename){
		// TODO: IMPLEMENT READING -- all of these need to be set appropriately 
		this.participants = null;
		this.date 		  = new Date();
		this.initial_cash = 0;
		this.cashbox 	  = 0;
		this.filename 	  = filename;
		
		this.ingredients = null;
		this.products 	 = null;
	}
	
	public void addIngredient(String name, double price, int quantity){
		ingredients.put(name,new FoodItem("Ingredient", name, price, quantity));
	}
	
	public void addProduct(String name, double price, int quantity){
		products.put(name, new FoodItem("Product", name, price, quantity));
	}
	
	/**
	 * Increments the number sold of the item item_name and increments the cashbox
	 * @param item_name
	 */
	public void purchaseProduct(String item_name){
		FoodItem item = products.get(item_name);
		
		item.incrementNumberSold();
		cashbox += item.getPrice();
	}
	
	/**
	 * Writes all the information to files
	 */
	public void writeToFile(){
		// TODO: IMPLEMENT WRITING
		
		// DATE and CASHBOX
		// Date, 26, 3, 2014
		// Cash, initial, 10.00
		// Cash, final, 20.00
		
		// PARTICIPANTS
		for (String name : this.participants){
			// Participant, Alex

		}
		
		// INGREDIENTS
		for (FoodItem i : this.ingredients.values()){
			// Ingredient, apples, 1.00, 3, 0 \n 
			// call some getFileString() method in FoodItem class
		}
		
		// PRODUCTS
		for (FoodItem p : this.products.values()){
			// Product, apple juice, 4.00, 5, 1 \n
			// call some getFileString() method in FoodItem class
		}
		
		
	}
	
	
}
