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
	private String nameField = null;
	private boolean added = false;
	private double cashbox_value;
	private String school;
	
	/** Called when Application is started */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Intent i = this.getIntent();
		added = i.getBooleanExtra("sellersAdded", false);
		cashbox_value = i.getDoubleExtra("cashBox", -1);
		school = i.getStringExtra("school");
		
		if (cashbox_value != -1) {
			EditText cashview = (EditText) findViewById(R.id.cashbox_value);
			cashview.setText(String.valueOf(cashbox_value));
		}
		
		if (school != null){
			EditText schoolview = (EditText) findViewById(R.id.school);
			schoolview.setText(school);
		}

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
    	// HomeActivity.saveSession();
		
		// Starting the new Activity
    	Intent i = new Intent(this, HomeActivity.class);
    	startActivity(i);
    	this.finish();
	}
	
	/** Called when [Add] button is clicked */
	public void onAddButton(View v){
		Log.v("LoginActivity", "onAddButton() -- Add seller button pressed.");
		
		EditText name_view = (EditText) findViewById(R.id.participant_name);
		
		if (name_view.getText().toString().equals("")){
			Toast.makeText(this, "Don't forget to type in who is selling!", Toast.LENGTH_SHORT).show();
			return;
		} else {
			nameField = name_view.getText().toString();
		}
		
		String name = name_view.getText().toString();
		HomeActivity.addParticipant(name);
		
		Toast.makeText(this, "Seller added!", Toast.LENGTH_SHORT).show();
		
		name_view.setText("");		
		name_view.requestFocus(); //put cursor on name_view
	}
	
    /** Called when [Finish] button is clicked */
    public void onFinishButton(View v) {
    	Log.v("LoginActivity", "onFinishedButton() -- Finished button pressed.");
    	
    	// Grabbing the View elements
    	EditText name_view 		= (EditText) findViewById(R.id.participant_name);
		EditText cashbox_view 	= (EditText) findViewById(R.id.cashbox_value);
		EditText school_view 	= (EditText) findViewById(R.id.school);
		
		if(cashbox_view.getText().toString().equals("")){
			Toast.makeText(this, "Check the cashbox!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(school_view.getText().toString().equals("")){
			Toast.makeText(this, "Don't forget to put in your school.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// if there is a name currently in the name view then add the name first
		if(!name_view.getText().toString().equals("")){
			onAddButton(v);
		} else if (nameField == null && !added) {
			Toast.makeText(this, "Don't forget to type in who is selling!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		cashbox_value 	= Double.parseDouble(cashbox_view.getText().toString());
		school			= school_view.getText().toString();
		
		// Storing the values
		HomeActivity.setInitialCashBalance(cashbox_value);
		HomeActivity.addSchool(school);
    	
    	// Saving the currentSession
    	// HomeActivity.saveSession();
    	
    	// Starting the new Activity
    	Intent i = new Intent(this, IngredientActivity.class);
    	i.putExtra("cashBox", cashbox_value);
    	i.putExtra("school", school);
    	i.putExtra("sellersAdded", true);
    	startActivity(i);
    	this.finish();
    }
}
