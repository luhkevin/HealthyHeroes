package com.example.healthyheroes;

public class FoodItem {
	private final String 	name;
	private final double 	price;
	private final int 		quantity;
	
	private int		number_sold;	// only used for products
	
	public FoodItem(String name, double price, int quantity){
		this.name 		= name;
		this.price 		= price;
		this.quantity 	= quantity;
		this.number_sold= 0;
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
	
	public int getNumberSold(){
		return number_sold;
	}
	
	public void incrementNumberSold(){
		number_sold++;
	}	
}
