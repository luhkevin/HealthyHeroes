package com.example.healthyheroes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
	
	/** Called when [Add] button is clicked */
	public void onAddButton(View v){
		Log.v("LoginActivity", "onAddButton() -- Add seller button pressed.");
		
		EditText name_view = (EditText) findViewById(R.id.participant_name);
		
		if (name_view.getText().toString().equals("")){
			Toast.makeText(this, "Don't forget to type in who is selling!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		String name = name_view.getText().toString();
		HomeActivity.addParticipant(name);
		
		Toast.makeText(this, "Seller added!", Toast.LENGTH_SHORT).show();
		
		name_view.setText("");		
		name_view.setHint("Who else is selling?");
	}
	
    /** Called when [Finish] button is clicked */
    public void onFinishButton(View v) {
    	Log.v("LoginActivity", "onFinishedButton() -- Finished button pressed.");
    	
    	// Grabbing the View elements
    	EditText name_view 		= (EditText) findViewById(R.id.participant_name);
		EditText cashbox_view 	= (EditText) findViewById(R.id.cashbox_value);
		
		if(cashbox_view.getText().toString().equals("")){
			Toast.makeText(this, "Check the cashbox!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// if there is a name currently in the name view then add the name first
		if(!name_view.getText().toString().equals("")){
			onAddButton(v);
		}
		
		double 	cashbox_value 	= Double.parseDouble(cashbox_view.getText().toString());
	
		// Storing the values
		HomeActivity.setInitialCashBalance(cashbox_value);
    	
    	// Saving the currentSession
    	HomeActivity.saveSession();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, IngredientActivity.class);
    	startActivity(i);
    }
}
