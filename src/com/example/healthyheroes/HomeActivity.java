package com.example.healthyheroes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {	
	public static final int IngredientActivity_ID = 1;
	
	// private instance of session object
	private static Session session;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] participants = {"Rob", "Bob"};
        session = new Session(participants, 100.0);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
    public void onCreateSessionButton(View v) {
    	Intent i = new Intent(this, IngredientActivity.class);
    	startActivityForResult(i, IngredientActivity_ID);
    }
    
    
    public void onViewSessionButton(View v) {
    	//Intent i = new Intent(this, ViewSessionActivity.class);
    	//startActivityForResult(i, ViewSessionActivity_ID);
    }
    
    // STATIC METHODS TO BE USED TO UPDATE THE SESSION INFO
    
    /**
     * Adds the ingredient to the session
     * @param name
     * @param price
     * @param quantity
     */
    public static void addIngredient(String name, double price, int quantity){
    	session.addIngredient(name, price, quantity); 
    }
    
    /**
     * Adds the product to the session
     * @param name
     * @param price
     * @param quantity
     */
    public static void addProduct(String name, double price, int quantity){
    	session.addProduct(name, price, quantity); 
    }
    
    /**
     * Adjusts the cashbox, and items sold in the session
     * @param item_name
     */
    public void purchaseProduct(String item_name){
    	session.purchaseProduct(item_name);
    }    
}
