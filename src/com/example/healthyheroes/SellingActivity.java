package com.example.healthyheroes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SellingActivity extends Activity {
	
	private TextView cashBox;
	private ListView list;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
        init();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selling, menu);
        return true;
    }
	
    private void init() {
    	cashBox = (TextView) this.findViewById(R.id.moneyTotal);
    	cashBox.setText("$" + String.format("%.2f", HomeActivity.getCurrentCashBalance()));
		list = (ListView) this.findViewById(R.id.listOfProducts);
		
		ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
		for(FoodItem product : HomeActivity.getCurrentSession().getProducts().values()) {
			   foods.add(product);
			}
		list.setAdapter(new FoodItemAdapter(SellingActivity.this, R.layout.selling_list_item, foods, this)); // create itemAdapater
		

	}
    
    public void onFinishButton(View v) {
    	//TODO write me
    }
    
    public void onBackButton(View v) {
		Log.v("SellingActivity","onBackButton() -- Back button pressed.");
		
		//TODO pop-up warning
		// Saving the currentSession
    	HomeActivity.saveSession();
		
		// Starting the new Activity
    	Intent i = new Intent(this, ProductActivity.class);
    	startActivity(i);
    }
    
    public void onNewCustomerButton(View v) {
    	//TODO write me
    	
    }
    
    public void onFinishCustomerButton(View v) {
    	//TODO write me
    	
    }
    
    // This helper method rounds a double to a given number of decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value *= factor;
        long tempval = Math.round(value);
        return (double) tempval / factor;
    }
    
    public void setCashBox(double cash) {
    	cashBox.setText("$" + String.format("%.2f", cash));
    }
    
    public double getCashBox() {
    	return Double.parseDouble(cashBox.getText().toString().substring(1));
    }
    
}
