package com.example.healthyheroes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class IngredientActivity extends Activity {

	private static final int ProductActivity_ID = 0;

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

    public void onAddButton(View v) {
    	/* Add to data structure */
    }

    public void onFinishedButton(View v) {
    	Intent i = new Intent(this, ProductActivity.class);
    	startActivityForResult(i, ProductActivity_ID);
    }
}
