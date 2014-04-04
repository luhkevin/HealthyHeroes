package com.example.healthyheroes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	/** Called when Application is started */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	/** Called when [Back] button is clicked */
	public void onBackButton(View v){
		Log.v("LoginActivity","onBackButton() -- Back button pressed.");
		
		// Saving the currentSession
    	HomeActivity.saveSession();
		
		// Starting the new Activity
    	Intent i = new Intent(this, HomeActivity.class);
    	startActivity(i);
	}

    /** Called when [Finish] button is clicked */
    public void onFinishButton(View v) {
    	Log.v("LoginActivity", "onFinishedButton() -- Finished button pressed.");
    	
    	// Grabbing the View elements
    	EditText name_view 		= (EditText) findViewById(R.id.participant_name);
		EditText cashbox_view 	= (EditText) findViewById(R.id.cashbox_value);
		
		// Grabbing the values
		//TODO: Some VALIDATION for the fields
		String 	name 	 		= name_view.getText().toString();
		if(cashbox_view.getText().toString() == null) {
			//TODO add dialog box asking for cashbox to have a value
			return; //avoids crash at parseDouble
		}
		double 	cashbox_value 	= Double.parseDouble(cashbox_view.getText().toString());
		
		Log.v("LoginActivity", "onFinishedButton() -- name and cashbox value gotten from view.");
		Log.v("LoginActivity", "onFinishedButton() -- name = " + name);
		Log.v("LoginActivity", "onFinishedButton() -- cashbox_value = " + String.valueOf(cashbox_value));
		
		// Storing the values
		HomeActivity.addParticipant(name);
		HomeActivity.setInitialCashBalance(cashbox_value);
		Log.v("LoginActivity", "onFinishedButton() -- participant added to currentSession.");
		Log.v("LoginActivity", "onFinishedButton() -- cashbox value set in currentSession.");
    	
    	// Saving the currentSession
    	HomeActivity.saveSession();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, IngredientActivity.class);
    	startActivity(i);
    }
}
