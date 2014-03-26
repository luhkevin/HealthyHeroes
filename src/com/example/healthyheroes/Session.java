package com.example.healthyheroes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.util.Log;

/**
 * The session class stores information about a given session.
 */
public class Session {
	private Date 	 					date;
	private String						filename;	
	
	private ArrayList<String> 			participants;
	private double 						initial_cash;
	private double 	  					cashbox;
	private HashMap<String, FoodItem> 	ingredients;
	private HashMap<String, FoodItem> 	products;
	
	/** Constructor */
	public Session(){
		Log.v("Session", "new Session created with empty constructor.");
		this.date 		  = new Date();
		this.filename 	  = "CurrentSession.session";	//TODO: File format
		
		this.participants = new ArrayList<String>();
		this.initial_cash = -1;
		this.cashbox 	  = -1;
		this.ingredients  = new HashMap<String, FoodItem>();
		this.products 	  = new HashMap<String, FoodItem>();
	}
	
	/** Constructor for loading from a csv file */
	public Session(String filename){
		Log.v("Session", "new Session created with filename constructor.");
		// TODO: IMPLEMENT READING -- all of these need to be set appropriately 
		this.participants = null;
		this.date 		  = new Date();
		this.initial_cash = -1;
		this.cashbox 	  = -1;
		this.filename 	  = filename;
		
		this.ingredients = null;
		this.products 	 = null;
	}
	
	//TODO: implement addParticipant()
	/** Adds a participant to the session */
	
	//TODO: implement setInitialCash()
	/** Sets the initial cash balance of the session */
	
	/** Adds an ingredient to the session */
	public void addIngredient(String item_name, double price, int quantity){
		ingredients.put(item_name,new FoodItem("Ingredient", item_name, price, quantity));
		Log.v("Session", "addIngredient() -- " + item_name + " was added.");
		for (String ingredient: ingredients.keySet()){
			Log.v("Session", "INGREDIENT: " + ingredient);
		}
	}
	
	/** Adds a product to the session */
	public void addProduct(String item_name, double price, int quantity){
		products.put(item_name, new FoodItem("Product", item_name, price, quantity));
		Log.v("Session", "addProduct() -- " + item_name + " was added.");
		
		for (String product: products.keySet()){
			Log.v("Session", "PRODUCT: " + product);
		}
	}
	
	/** Increments the number sold of the item item_name and increments the cashbox */
	public void purchaseProduct(String item_name){
		if (initial_cash < 0){
			Log.e("Session", "purchaseProduct() - initial cash was never set.");
		} else {
			FoodItem item = products.get(item_name);
			item.incrementNumberSold();
			cashbox += item.getPrice();
		}
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
