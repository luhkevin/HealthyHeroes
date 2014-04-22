package com.example.healthyheroes;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ViewSessionActivity extends Activity {
	private TextView revenueInfo;
	private TextView soldProducts;
	private TextView ingredientCost;
	private TextView profit;

	/** Called when Application is started */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_session);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selling, menu);
		return true;
	}
	
	private void init() {
		Bundle revenueExtras = getIntent().getExtras();
		String revenue = "";
		if(revenueExtras != null) {
			revenue = revenueExtras.getString("REVENUE_ID");
		}
    	revenueInfo = (TextView) this.findViewById(R.id.revenue);
    	revenueInfo.setText(revenue);
    	
    	Session currentSession = HomeActivity.getCurrentSession();
    	
    	soldProducts = (TextView) this.findViewById(R.id.soldproducts);
    	int soldProductsNumber = 0;
    	for(FoodItem food : currentSession.getProducts().values())
    	{
    		soldProductsNumber += food.getTotalSold();
    	}
    	soldProducts.setText(String.valueOf(soldProductsNumber));
    	
    	ingredientCost = (TextView) this.findViewById(R.id.ingredientcost);
    	double ingredientCostNumber = 0.0;
    	for(FoodItem food : currentSession.getIngredients().values())
    	{
    		ingredientCostNumber += (food.getPrice() * food.getQuantity());
    	}
    	ingredientCost.setText(String.valueOf(ingredientCostNumber));
    	
    	profit = (TextView) this.findViewById(R.id.profit);
    	profit.setText(String.valueOf(
    			(Double.parseDouble(revenue) - ingredientCostNumber)));
	}
	
	public void onBackButton(View v) {
		//TODO write me
		
		//this.finish(); //TODO uncomment when method written
	}
	
	public void onFinishButton(View v) {
		//TODO write me
		
		//this.finish(); //TODO uncomment when method written
	}
}
