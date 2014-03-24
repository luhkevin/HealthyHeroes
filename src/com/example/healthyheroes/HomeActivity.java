package com.example.healthyheroes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity {
	public static final int IngredientActivity_ID = 1;
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
    
    public void onCreateSessionButton(View v) {
    	Intent i = new Intent(this, IngredientActivity.class);
    	startActivityForResult(i, IngredientActivity_ID);
    }
    
    
    public void onViewSessionButton(View v) {
    	//Intent i = new Intent(this, ViewSessionActivity.class);
    	//startActivityForResult(i, ViewSessionActivity_ID);
    }
    
}
