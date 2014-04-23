package com.example.healthyheroes;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {	
	public static final int LoginActivity_ID = 1; // I don't think we have to use startActivityforResult()
	
	// private instance of session object
	private static Session current_session;
	
	private static File files_directory;
	
	
	/** Called when Application is started */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        files_directory = getFilesDir();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    /** Called when [Create Session] button is clicked */
    public void onCreateSessionButton(View v) {
    	Log.v("HomeActivity", "onCreateSessionButton() -- [New Session] button pressed");
    	// Create a new session and set current_session
    	current_session = new Session();
    	
    	Session.getAllSessions();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, LoginActivity.class);
    	startActivityForResult(i, LoginActivity_ID);
    }
    
    /** Called when [View Sessions] button is clicked */
    public void onViewSessionButton(View v) {
    	Log.v("HomeActivity", "onViewSessionButton() -- [View Sessions] button pressed");
    	//Intent i = new Intent(this, ViewSessionActivity.class);
    	//startActivityForResult(i, ViewSessionActivity_ID);
    }
    
//	STATIC METHODS TO BE USED TO PASS INFORMATION TO SESSION CLASS
    public static File getFilesDirectory(){
    	return files_directory;
    }
    
// STATIC METHODS TO BE USED TO UPDATE THE SESSION INFO
    
    /** Sets the initial cash balance of the session */
    public static void setInitialCashBalance(double initial_cash){
    	Log.v("HomeActivity", "setInitialCashBalance() -- "+initial_cash+" was set as initial cash balance.");
    	current_session.setInitialCash(initial_cash);
    }
    
    /** Gets the current cash balance of the session */
    public static double getCurrentCashBalance(){
    	return current_session.getCurrentCash();
    }
    
    /** Adds a new participant to the session */
    public static void addParticipant(String participant_name){
    	Log.v("HomeActivity", "addParticipant() -- "+participant_name+" was added.");
    	current_session.addParticipant(participant_name);
    }
    
    /** Adds the ingredient to the session */
    public static void addIngredient(String item_name, double price, int quantity){
    	Log.v("HomeActivity", "addIngredient() -- "+item_name+" was added.");
    	current_session.addIngredient(item_name, price, quantity); 
    }
    
    /** Adds the product to the session */
    public static void addProduct(String item_name, double price, int quantity){
    	Log.v("HomeActivity", "addProduct() -- "+item_name+" was added.");
    	current_session.addProduct(item_name, price, quantity); 
    }
    
    /** Adjusts the cashbox, and items sold in the session */
    public static void purchaseProduct(String item_name){
    	Log.v("HomeActivity", "purchaseProduct() -- "+item_name+" was purchased.");
    	current_session.purchaseProduct(item_name);
    }
    
    public static Session getCurrentSession(){
    	return current_session;
    }
    
    /** Saves the current session to a file */
    public static void saveSession(Context ctx) {
    	Log.v("HomeActivity", "saveSession() -- session is being saved.");
    	current_session.writeSessionToFile(ctx);
    	
    	Log.v("HomeActivity", "saveSession() -- DONE saving session.");
    }
    
    /** Loads a session from a file */
    public static void loadSession(String filename){
    	Log.v("HomeActivity", "loadSession() -- session is being loaded.");
    	//TODO: Implement
    }
}
