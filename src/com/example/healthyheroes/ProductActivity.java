package com.example.healthyheroes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ProductActivity extends Activity {
	public static final int SellingActivity_ID = 1;
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

    public void onAddButton(View v) {
    	// Grabbing the View elements
    	EditText name_view 		= (EditText) findViewById(R.id.name_field);
		EditText price_view 	= (EditText) findViewById(R.id.price_field);
		EditText quantity_view 	= (EditText) findViewById(R.id.quantity_field); 
		
		// Grabbing the values
		//TODO: Some VALIDATION for the fields
		String 	name 	 = name_view.getText().toString();
		double 	price 	 = Double.parseDouble(price_view.getText().toString());	
		int 	quantity = Integer.parseInt(quantity_view.getText().toString());
		
		// Storing the values
		HomeActivity.addIngredient(name, price, quantity);
		
		// Clearing the fields
		name_view.setText("");
		price_view.setText("");
		quantity_view.setText("");
    }

    public void onFinishedButton(View v) {
    	Intent i = new Intent(this, ProductActivity.class); //goto SellingActivity
    	startActivityForResult(i, SellingActivity_ID);
    }
}
