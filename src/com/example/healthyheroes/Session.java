/**
 * 
 */
package com.example.healthyheroes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 *
 */
public class Session {
	private final String[] 	participants;
	private final Date 	 	date;
	private double 	  		cashbox;
	
	private HashMap<String, FoodItem> ingredients;
	private HashMap<String, FoodItem> products;
	
	/**
	 * Constructor for inputing data from form
	 * @param participants
	 * @param cashbox
	 */
	public Session(String[] participants, double cashbox){
		this.participants = participants;
		this.date 		  = new Date();
		this.cashbox 	  = cashbox;
		
		this.ingredients = new HashMap<String, FoodItem>();
		this.products 	 = new HashMap<String, FoodItem>();
	}
	
	/**
	 * Constructor for importing from a csv file
	 * @param filename
	 */
	public Session(String filename){
		//TODO: implement
		
		this.participants = null;
		this.date 		  = new Date();
		this.cashbox 	  = 0;
		
		this.ingredients = null;
		this.products 	 = null;
	}
	
	public void addIngredient(String name, double price, int quantity){
		ingredients.put(name,new FoodItem(name, price, quantity));
	}
	
	public void addProduct(String name, double price, int quantity){
		products.put(name, new FoodItem(name, price, quantity));
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
	
	//TODO: Implement some kind of csv writing method
	
	
}
