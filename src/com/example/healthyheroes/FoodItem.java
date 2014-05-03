package com.example.healthyheroes;

import android.util.Log;

/**
 * The FoodItem class is a class which stores information about a given ingredient or 
 * product.
 */
public class FoodItem {
	private final String 	name;
	private final double 	price;
	private final int 		quantity;
	
	private int	number_sold;	// only used for products
	private int total_sold;
	
	public FoodItem(String name, double price, int quantity){
		Log.v("FoodItem", "new FoodItem created - "+name);
		
		this.name 		= name;
		this.price 		= price;
		this.quantity 	= quantity;
		this.number_sold= 0;
		this.total_sold = 0;
	}
	
	/** Constructor used when you want to set the number_sold */
	public FoodItem(String name, double price, int quantity, int number_sold){
		Log.v("FoodItem", "new FoodItem created - "+name);
		
		this.name 		= name;
		this.price 		= price;
		this.quantity 	= quantity;
		this.number_sold= number_sold;
	}
	
	public String getName(){
		return name;
	}
	
	public double getPrice(){
		return price;
	}
	
	public double getQuantity(){
		return quantity;
	}
	
	/**
	 * returns a string containing all the information about the item so that it can be
	 * written to a file.
	 * The string will be of the form:
	 * 		type, name, price, quantity, [number_sold]
	 */
	public String getFileString(){
		return	name + "," + 
				"Price: " + "," + 
				"Quantity: " + "," + 
				"Number Sold: " + String.valueOf(number_sold);
//		return	name + "," + 
//				String.valueOf(price) + "," + 
//				String.valueOf(quantity) + "," + 
//				String.valueOf(number_sold);
	}
	
	// METHODS ONLY USED FOR PRODUCTS
	
 	public int getNumberSold(){
		return number_sold;	
	}
 	
 	public int getTotalSold(){
 		return total_sold;
 	}

	public void incrementNumberSold(){
		number_sold = Math.min(this.quantity, number_sold + 1);
	}	
	
	public void decrementNumberSold(){
		number_sold = Math.max(0, number_sold - 1);
	}
	
	public boolean upperLimitReached() {
		return ((number_sold + total_sold) == this.quantity);
	}

	public boolean lowerLimitReached() {
		return (number_sold == 0);
	}
	
	public void reset() {
		total_sold += number_sold;
		this.number_sold = 0;
	}
}
