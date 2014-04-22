package com.example.healthyheroes;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ViewSessionActivity extends Activity {
	private TextView revenueInfo;

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
    	
    	//TODO set soldproducts, ingredientcost and profit.
	}
	
	public void onBackButton(View v) {
		//TODO write me
	}
	
	public void onFinishButton(View v) {
		//TODO write me
	}
}
