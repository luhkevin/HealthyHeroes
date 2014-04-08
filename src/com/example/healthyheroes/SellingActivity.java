package com.example.healthyheroes;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SellingActivity extends Activity {
	public static final int ViewSessionActivity_ID = 1;

	private TextView cashBox;
	private TextView customerTotal;
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
    	
    	customerTotal = (TextView) this.findViewById(R.id.customerTotal);
    	customerTotal.setText("$" + String.format("%.2f", 0.0));
    	
		list = (ListView) this.findViewById(R.id.listOfProducts);
		
		
		ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
		for(FoodItem product : HomeActivity.getCurrentSession().getProducts().values()) {
			   foods.add(product);
			}
		list.setAdapter(new FoodItemAdapter(SellingActivity.this, R.layout.selling_list_item, foods, this)); // create itemAdapater
		

	}
    
    public void onFinishButton(View v) {
    	finishSession();
    }
    
    public void onBackButton(View v) {
		Log.v("SellingActivity","onBackButton() -- Back button pressed.");
		
		//pop-up warning
		new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(R.string.more_products_title)
        .setMessage(R.string.more_products_message)
        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Stop the activity
                SellingActivity.this.finishSession();    
            }

        })
        .setNegativeButton(R.string.no, null)
        .show();
		
    }
    
    private void finishSession() {
    	
		// Saving the currentSession
    	HomeActivity.saveSession();
		
		// Starting the new Activity
    	Intent i = new Intent(this, ViewSessionActivity.class);
    	i.putExtra("REVENUE_ID", String.valueOf(this.getCustomerTotal()));
    	startActivityForResult(i, ViewSessionActivity_ID);
    	this.finish(); //do we need this?
    }
    
    public void onNewCustomerButton(View v) {
    	//TODO write me
    	
    }
    
    public void onFinishCustomerButton(View v) {
    	//TODO write me
    	double custTotal = this.getCustomerTotal();
    	double cashBox = this.getCashBox();
    	this.setCashBox(cashBox + custTotal);
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
    
    public void setCustomerTotal(double cash) {
    	customerTotal.setText("$" + String.format("%.2f", cash));
    }
    
    public double getCustomerTotal() {
    	return Double.parseDouble(customerTotal.getText().toString().substring(1));
    }
    
    
}
