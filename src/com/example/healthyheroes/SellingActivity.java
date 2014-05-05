package com.example.healthyheroes;

import java.util.ArrayList;

import com.example.healthyheroes.FoodItemAdapter.FoodItemHolder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SellingActivity extends Activity {
	public static final int ViewSessionActivity_ID = 1;

	//In case we are returning from product screen
	private double tempCashBox;

	private TextView cashBox;
	private TextView customerTotal;
	private ListView list;
	private ArrayList<Double> customerTotals = new ArrayList<Double>();
	private FoodItemAdapter foodAdapt;
	private double totalRevenue;
	private boolean finished = true;
	private Button newCustomer, finishCustomer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = this.getIntent();
        totalRevenue = i.getDoubleExtra("REVENUE", 0);
        tempCashBox = i.getDoubleExtra("cashbox", -1);
        Log.v("CASHBOX FROM PRODUCT ACTIVITY", String.valueOf(tempCashBox));
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
    	if(tempCashBox == -1) {
    		cashBox.setText("$" + String.format("%.2f", HomeActivity.getCurrentCashBalance()));
    	} else {
    		cashBox.setText("$" + String.format("%.2f", tempCashBox));
    	}
    	
    	customerTotal = (TextView) this.findViewById(R.id.customerTotal);
    	customerTotal.setText("$" + String.format("%.2f", 0.0));
    	
		list = (ListView) this.findViewById(R.id.listOfProducts);
		
		ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
		for(FoodItem product : HomeActivity.getCurrentSession().getProducts().values()) {
			   foods.add(product);
		}
		foodAdapt = new FoodItemAdapter(SellingActivity.this, R.layout.selling_list_item, foods, this); // create itemAdapater
		list.setAdapter(foodAdapt); // create itemAdapater
		
		newCustomer = (Button) this.findViewById(R.id.newCustomerButton);
		finishCustomer = (Button) this.findViewById(R.id.finishButton);
		
		//TODO initalize colors
		newCustomer.setBackgroundColor(Color.GREEN);
		finishCustomer.setBackgroundColor(Color.RED);
		
//    	for (int i = 0; i < list.getChildCount(); i++) {
//    		View rowView = list.getChildAt(i);
//    		FoodItemHolder holder = (FoodItemHolder) rowView.getTag();
//    		holder.numberSold.setText("0");
//    		holder.enabled = false;
//    		rowView.setBackgroundColor(Color.GRAY);
//    	} 
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
                SellingActivity.this.backToProducts();    
            }

        })
        .setNegativeButton(R.string.no, null)
        .show();
    }
    
    private void backToProducts() {
		// Saving the currentSession
    	// HomeActivity.saveSession();

		// Starting the new Activity
    	Intent i = new Intent(this, ProductActivity.class);
    	i.putExtra("cashbox", Double.parseDouble(cashBox.getText().toString()));
    	Log.v("CASHBOX SENT TO PRODUCTS:", cashBox.getText().toString());
    	startActivity(i);
    	this.finish();
    }
    
    private void finishSession() {
		// Saving the currentSession
    	// HomeActivity.saveSession();
		
		// Starting the new Activity
    	Intent i = new Intent(this, ViewSessionActivity.class);
    	i.putExtra("REVENUE_ID", String.valueOf(totalRevenue));
    	startActivityForResult(i, ViewSessionActivity_ID);
    	this.finish(); 
    }
    
    public void onNewCustomerButton(View v) {
    	if(finished) {
    		this.setCustomerTotal(0.0);
//    		this.foodAdapt.resetFoodValues();
    		finished = false;
        	for (int i = 0; i < list.getChildCount(); i++) {
        		View rowView = list.getChildAt(i);
        		FoodItemHolder holder = (FoodItemHolder) rowView.getTag();
        		holder.enabled = true;
        		rowView.setBackgroundColor(Color.WHITE);
        	}
        	newCustomer.setBackgroundColor(Color.RED);
        	finishCustomer.setBackgroundColor(Color.GREEN);
    	}
    }
    
    public void onFinishCustomerButton(View v) {
    	//set cashbox
    	double custTotal = this.getCustomerTotal();
    	double cashBox = this.getCashBox();
    	this.totalRevenue += custTotal;
    	this.setCashBox(cashBox + custTotal);
    	this.setCustomerTotal(0.0);
    	this.foodAdapt.resetFoodValues();
    	
    	for (int i = 0; i < list.getChildCount(); i++) {
    		View rowView = list.getChildAt(i);
    		FoodItemHolder holder = (FoodItemHolder) rowView.getTag();
    		holder.numberSold.setText("0");
    		holder.enabled = false;
    		rowView.setBackgroundColor(Color.GRAY);
    	}
    	newCustomer.setBackgroundColor(Color.GREEN);
    	finishCustomer.setBackgroundColor(Color.RED);
    	
    	this.customerTotals.add(Double.valueOf((cashBox + custTotal)));
    	finished = true;
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
