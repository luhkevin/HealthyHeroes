package com.example.healthyheroes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimeZone;

import android.content.Context;
import android.util.Log;

/**
 * The session class stores information about a given session.
 */
public class Session {
	private Date 	 					date;
	private String						filename;	
	private String 						finalfilename;
	
	private String 						school;
	private ArrayList<String> 			participants;
//	private ArrayList<Customer>			customers; //TODO: implement customers 
	private double 						initial_cash;
	private double 	  					cashbox;
	private HashMap<String, FoodItem> 	ingredients;
	private HashMap<String, FoodItem> 	products;
	
	// Static constants for parsing datafile
	public final static int DATE 			= 0;
	public final static int INITIAL_CASH 	= 1;
	public final static int FINAL_CASH 		= 2;
	public final static int PARTICIPANT 	= 3;
	public final static int INGREDIENT 		= 4;
	public final static int PRODUCT 		= 5;
	public final static int CUSTOMER 		= 6;
	
	/** Constructor */
	public Session(){
		Log.v("Session", "new Session created with empty constructor.");
		this.date 		  = new Date();
		this.filename 	  = "healthyheroes";	//TODO: File format
		
		this.participants = new ArrayList<String>();
		this.initial_cash = -1;
		this.cashbox 	  = -1;
		this.ingredients  = new HashMap<String, FoodItem>();
		this.products 	  = new HashMap<String, FoodItem>();
	}
	
	/** Constructor for loading from file */
	public Session(String filename){
		//TODO: Finish Implementing
		
		Log.v("Session", "new Session created with filename constructor.");
			
		this.filename 		= filename;	// need to set this before reading 
		this.participants 	= new ArrayList<String>();
		this.ingredients	= new HashMap<String, FoodItem>();
		this.products 		= new HashMap<String, FoodItem>();
		
		Iterator<String> file_lines = this.readSessionFromFile().iterator();
		String[]		 line		= null; 
		
		while(file_lines.hasNext()){
			line = file_lines.next().split(",");
			switch(Integer.parseInt(line[0])){
			case DATE:
				date = this.parseDate(line);
				break;
			case INITIAL_CASH:
				initial_cash = Double.parseDouble(line[1]);
				break;
			case FINAL_CASH:
				cashbox = Double.parseDouble(line[1]);
				break;
			case PARTICIPANT:
				this.addParticipant(line[1]);
				break;
			case INGREDIENT:
				this.addIngredient(
						line[1], 						// name
						Double.parseDouble(line[2]), 	// price
						Integer.parseInt(line[3]));		// number_sold
				break;
			case PRODUCT:
				this.addProduct(
						line[1], 						// name
						Double.parseDouble(line[2]), 	// price
						Integer.parseInt(line[3]),		// quantity
						Integer.parseInt(line[4]));		// number_sold
				break;
			case CUSTOMER:
				// TODO: IMPLEMENT CUSTOMER
			default:
				Log.e("Session", line + " could not be parsed.");
			}
		}
	}
	
	//TODO: implement addCustomer()
	/** Adds a customer to the session */
	
	
	/** Adds the school to the session */
	public void addSchool(String school_name){
		school = school_name;
	}
	
	/** Adds a participant to the session */
	public void addParticipant(String name_participant){
		participants.add(name_participant);
	}
	
	/** Returns the list of participants */
	public ArrayList<String> getParticpant() {
		return participants;
	}
	
	/** Sets the initial cash balance of the session */
	public void setInitialCash(double cash){
		initial_cash = cash;
		cashbox = initial_cash;
	}
	
	public double getCurrentCash(){
		return cashbox;
	}
	
	/** Adds an ingredient to the session */
	public void addIngredient(String item_name, double price, int quantity){
		ingredients.put(item_name,new FoodItem(item_name, price, quantity));
		Log.v("Session", "addIngredient() -- " + item_name + " was added.");
		for (String ingredient: ingredients.keySet()){
			Log.v("Session", "INGREDIENT: " + ingredient);
		}
	}
	
	/** Adds a product to the session */
	public void addProduct(String item_name, double price, int quantity){
		products.put(item_name, new FoodItem(item_name, price, quantity));
		
		Log.v("Session", "addProduct() -- " + item_name + " was added.");
		for (String product: products.keySet()){
			Log.v("Session", "PRODUCT: " + product);
		}
	}
	
	/** Adds a product to the session with set number_sold */
	private void addProduct(String item_name, double price, int quantity, int number_sold){
		products.put(item_name,  new FoodItem(item_name, price, quantity, number_sold));
		
		Log.v("Session", "addProduct() -- " + item_name + " was added.");
		for (String product: products.keySet()) {
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
	
	public HashMap<String, FoodItem> getProducts(){
		return products;
	}
	
	public HashMap<String, FoodItem> getIngredients(){
		return ingredients;
	}
	
	/** Writes the contents of the session into the file with filename */
	public void writeSessionToFile(Context ctx){
		Log.v("Session", "writeSessionToFile() -- writting session to file.");
		FileOutputStream outputStream;
		String timestamp = this.getTime(); 
		//finalfilename = filename + "_" + timestamp + ".log";
		finalfilename = school + "_" + timestamp + ".log";
		try {
		  outputStream = ctx.openFileOutput(finalfilename, Context.MODE_PRIVATE);
		  Log.v("saving to file", finalfilename);
		  Log.v("file content", this.getFileString());
		  outputStream.write(this.getFileString().getBytes());
		  outputStream.close();
		  Log.v("SESSION SAVED", "saved");
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
	
	private String getTime() {
		Date dt = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss-a");
		sd.setTimeZone(TimeZone.getTimeZone("EST"));
		String formDate = sd.format(dt);
		System.out.println("formated: " + formDate);
		return formDate;
	}
	
	/** HELPER METHOD: Returns a string to be written to a file */
	private String getFileString() {
		Log.v("Session","getFileStringBytes() -- creating content for file " + filename);
		
		// Initializing string
		StringBuilder output_string = new StringBuilder();
		
		// Building File String
		output_string.append("Date of Session: " + this.getDateFileString() + "\n");
		output_string.append("Initial Cash: " + String.valueOf(initial_cash) + "\n");
		output_string.append("Final Cash: " + String.valueOf(cashbox) + "\n");
		output_string.append("Participants: ");
		
		for(int i = 0; i < participants.size(); i++) {
			if(i != (participants.size() - 1)) {
				output_string.append(participants.get(i) + ", ");
			} else {
				output_string.append(participants.get(i));
			}
		}
		output_string.append("\n");
		
		output_string.append("Ingredients: ");
		for (FoodItem ingredient : ingredients.values()) {
			output_string.append(ingredient.getFileString() + "\n");
		}
		
		output_string.append("Products: ");
		for (FoodItem product : products.values()) {
			output_string.append(product.getFileString() + "\n");
		}

		//TODO: UPDATE when customer list is implemented
		
		return output_string.toString();
	}
	
	/** HELPER METHOD: Returns a string of the date DAY, MONTH, YEAR */
	private String getDateFileString() {
		GregorianCalendar cal 	= new GregorianCalendar();
		cal.setTime(date);

		// generating date string
		String date_string = 	Integer.valueOf(cal.get(Calendar.YEAR))  + "-" + 
								Integer.valueOf(cal.get(Calendar.MONTH)) + "-" + 
								Integer.valueOf(cal.get(Calendar.DATE))+ "\n";
		
		return date_string;
	}
	
	/** HELPER METHOD: Reads the file and returns a String array for each line */
	private ArrayList<String> readSessionFromFile() {
		Log.v("Session", "readSessionFromFile() -- reading session from file.");
		
		File files_directory 	= HomeActivity.getFilesDirectory();
		ArrayList<String> lines = new ArrayList<String>();
		
		try{
			BufferedReader br = 
					new BufferedReader(new FileReader(files_directory + filename));
			
			while(br.ready()){
				lines.add(br.readLine());
			}
			
			br.close();
		}catch(IOException e){
			Log.e("Session", "readSessionFromFile() -- " + e.getMessage());
		}
		
		return lines;
	}
	
	/** HELPER METHOD: Parses the date line of the file */
	private Date parseDate(String[] date_string){
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Integer.parseInt(date_string[1]),
				Integer.parseInt(date_string[2]),
				Integer.parseInt(date_string[3]));
		return cal.getTime();
	}
	
	/** STATIC METHOD: Prints all sessions that are saved in the file system */
	public static String[] getAllSessions(){
		Log.v("Session", "getAllSessions() -- getting all the sessions in the files directory.");
		File files_directory = HomeActivity.getFilesDirectory();
		
		File[] files = files_directory.listFiles();
		String[] filenames = files_directory.list();
		
		Log.d("Session", "path of directory: " + files_directory.getPath());
		Log.d("Session", "number of files: " + String.valueOf(files.length));
		Log.d("Session", "number of files: " + String.valueOf(filenames.length));
		
		for (int i = 0; i<filenames.length; i++){
			Log.v("Session", "getAllSessions() -- File: " + filenames[i]);
			filenames[i] = files[i].getName();
		}
		return filenames;
	}
}
