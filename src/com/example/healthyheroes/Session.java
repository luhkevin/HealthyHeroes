package com.example.healthyheroes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import android.util.Log;

/**
 * The session class stores information about a given session.
 */
public class Session {
	private Date 	 					date;
	private String						filename;	
	
	private ArrayList<String> 			participants;
//	private ArrayList<Customer>			customers; //TODO: implement customers 
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
	//TODO: implement addCustomer()
	/** Adds a customer to the session */
	
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
	
	/** Returns the filename of the session*/
	public String getFilename() {
		return filename;
	}
	
	public void writeSessionToFile(){
		Log.v("Session", "writeSessionToFile() -- writting session to file.");
		File files_directory = HomeActivity.getFilesDirectory();
		
		try {
			BufferedWriter bw = 
					new BufferedWriter(new FileWriter(files_directory + filename));
			
			bw.write(this.getFileString());
			
			bw.close();
		} catch (IOException e){
			Log.e("Session", "writeSessionToFile() -- " + e.getMessage());
		}
	}
	
	/** HELPER METHOD: returns a string to be written to a file */
	private String getFileString(){
		Log.v("Session","getFileStringBytes() -- creating content for file " + filename);
		
		// Initializing string
		String 				output_string = "";
		
		// Building File String
		output_string += "Date," 			+ this.getDateFileString() 	   	+ "\n";
		output_string += "Initial Cash," 	+ String.valueOf(initial_cash) 	+ "\n";
		output_string += "Final Cash," 		+ String.valueOf(cashbox) 		+ "\n";
		for (String participant : participants){
			output_string += "Participant," + participant + "\n";
		}
		for (FoodItem ingredient : ingredients.values()){
			output_string += ingredient.getFileString();
		} 
		for (FoodItem product : products.values()){
			output_string += product.getFileString();
		}
		
		return output_string;
	}
	
	/** Returns a string of the date DAY, MONTH, YEAR */
	private String getDateFileString(){
		GregorianCalendar cal 	= new GregorianCalendar();
		cal.setTime(date);
		
		// generating date string
		String date_string = 	Integer.valueOf(cal.get(Calendar.YEAR))  + "," + 
								Integer.valueOf(cal.get(Calendar.DATE))  + "," + 
								Integer.valueOf(cal.get(Calendar.MONTH)) + "\n";
		
		return date_string;
	}

	

}
