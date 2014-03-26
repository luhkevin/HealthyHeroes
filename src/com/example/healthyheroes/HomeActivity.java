package com.example.healthyheroes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {	
	public static final int IngredientActivity_ID = 1;
	
	// private instance of session object
	private static Session current_session;
	
	/** Called when Application is started */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    /** Called when [Create Session] button is clicked */
    public void onCreateSessionButton(View v) {
    	Log.v("HomeActivity", "onCreateSessionButton() -- [Create Session] button pressed");
    	
    	// Create a new session and set current_session
    	current_session = new Session();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, IngredientActivity.class);
    	startActivityForResult(i, IngredientActivity_ID);
    }
    
    /** Called when [View Sessions] button is clicked */
    public void onViewSessionButton(View v) {
    	//Intent i = new Intent(this, ViewSessionActivity.class);
    	//startActivityForResult(i, ViewSessionActivity_ID);
    }
    
    // STATIC METHODS TO BE USED TO UPDATE THE SESSION INFO
    /** Sets the initial cash balance of the session */
    public static void setInitialCashBalance(double initial_cash){
    	//TODO: implement setInitialCashBalance()
    }
    
    /** Adds a new participant to the session */
    public static void addParticipant(String participant_name){
    	//TODO: implement addParticipant()
    }
    
    /** Adds the ingredient to the session */
    public static void addIngredient(String item_name, double price, int quantity){
    	current_session.addIngredient(item_name, price, quantity); 
    }
    
    /** Adds the product to the session */
    public static void addProduct(String item_name, double price, int quantity){
    	current_session.addProduct(item_name, price, quantity); 
    }
    
    /** Adjusts the cashbox, and items sold in the session */
    public void purchaseProduct(String item_name){
    	current_session.purchaseProduct(item_name);
    }    
}
