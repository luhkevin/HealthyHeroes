package com.example.healthyheroes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductActivity extends Activity {
	public static final int SellingActivity_ID = 1;
	public static final int IngredientActivity_ID = 1;
	
	/** Called when Application is started */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product, menu);
		return true;
	}

	/** Called when [Back] button is clicked */
    public void onBackButton(View v) {
    	Log.v("ProductActivity","onBackButton() -- Back button pressed.");
    	
    	// Saving the currentSession
    	HomeActivity.saveSession();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, IngredientActivity.class);
    	startActivity(i);
    }
	
	/** Called when [Add] button is clicked */
    public void onAddButton(View v) {
    	Log.v("ProductActivity", "onAddButton() -- Add button pressed");
    	// Grabbing the View elements
    	EditText name_view 		= (EditText) findViewById(R.id.product_name);
		EditText price_view 	= (EditText) findViewById(R.id.product_price);
		EditText quantity_view 	= (EditText) findViewById(R.id.product_quantity); 
		
		//More duplicate code...
		if(isInvalidView(name_view) || isInvalidView(price_view) || isInvalidView(quantity_view)) {
			Toast.makeText(this, "Name, price, or quantity not entered", Toast.LENGTH_SHORT).show();
			return;
		}
		// Grabbing the values
		//TODO: Some VALIDATION for the fields
		String 	name 	 = name_view.getText().toString();
		double 	price 	 = Double.parseDouble(price_view.getText().toString());	
		int 	quantity = Integer.parseInt(quantity_view.getText().toString());
		
		Log.v("ProductActivity", "onAddButton() -- name, price and quantity gotten from view.");
		Log.v("ProductActivity", "onAddButton() -- name = " + name);
		Log.v("ProductActivity", "onAddButton() -- price = " + String.valueOf(price));
		Log.v("ProductActivity", "onAddButton() -- quantity = " + String.valueOf(quantity));
		
		// Storing the values
		HomeActivity.addProduct(name, price, quantity);
		Log.v("ProductActivity", "onAddButton() -- product added to currentSession.");
		
		// Clearing the fields
		name_view.setText("");
		price_view.setText("");
		quantity_view.setText("");
    }

    /** Called when [Finish] button is clicked */
    public void onFinishButton(View v) {
    	Intent i = new Intent(this, SellingActivity.class); //goto SellingActivity
    	startActivityForResult(i, SellingActivity_ID);
    }

    //duplicate code ... will want to refactor product/ingredient into same interface later if have the time
    private boolean isInvalidView(EditText view) {
    	return view.getText().toString().equals("");
    }
}
