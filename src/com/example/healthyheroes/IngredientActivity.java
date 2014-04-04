package com.example.healthyheroes;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngredientActivity extends Activity {
	private static final int ProductActivity_ID = 0;

	/** Called when Application is started */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingredient);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ingredient, menu);
		return true;
	}
	
	/** Called when [Back] button is clicked */
	public void onBackButton(View v){
		Log.v("IngredientActivity","onBackButton() -- Back button pressed.");
		
		// Saving the currentSession
    	HomeActivity.saveSession();
		
		// Starting the new Activity
    	Intent i = new Intent(this, LoginActivity.class);
    	startActivity(i);
	}

	/** Called when [Add] button is clicked */
    public void onAddButton(View v) {
    	Log.v("IngredientActivity", "onAddButton() -- Add button pressed");
    	// Grabbing the View elements
    	EditText name_view 		= (EditText) findViewById(R.id.ingredient_name);
		EditText price_view 	= (EditText) findViewById(R.id.ingredient_price);
		EditText quantity_view 	= (EditText) findViewById(R.id.ingredient_quantity); 
		
		// Grabbing the values
		//TODO: Some VALIDATION for the fields
		if(isInvalidView(name_view) || isInvalidView(price_view) || isInvalidView(quantity_view)) {
			Toast.makeText(this, "Name, price, or quantity not entered", Toast.LENGTH_SHORT).show();
			return;
		}

		String 	name 	 = name_view.getText().toString();
		double 	price 	 = Double.parseDouble(price_view.getText().toString());	
		int 	quantity = Integer.parseInt(quantity_view.getText().toString());
		
		Log.v("IngredientActivity", "onAddButton() -- name, price and quantity gotten from view.");
		Log.v("IngredientActivity", "onAddButton() -- name = " + name);
		Log.v("IngredientActivity", "onAddButton() -- price = " + String.valueOf(price));
		Log.v("IngredientActivity", "onAddButton() -- quantity = " + String.valueOf(quantity));
		
		// Storing the values
		HomeActivity.addIngredient(name, price, quantity);
		Log.v("IngredientActivity", "onAddButton() -- ingredient added to currentSession.");
		
		// Clearing the fields
		name_view.setText("");
		price_view.setText("");
		quantity_view.setText("");
    }

    /** Called when [Finish] button is clicked */
    public void onFinishButton(View v) {
    	Log.v("IngredientActivity", "onFinishedButton() --Finished button pressed.");
    	
    	// Saving the currentSession
    	HomeActivity.saveSession();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, ProductActivity.class);
    	startActivityForResult(i, ProductActivity_ID);
    }
    
    private boolean isInvalidView(EditText view) {
    	return view.getText().toString().equals("");
    }
}
