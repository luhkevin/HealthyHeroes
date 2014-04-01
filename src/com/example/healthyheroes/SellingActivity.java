package com.example.healthyheroes;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class SellingActivity extends Activity {
	
	TextView cashBox;
	ListView list;
	
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
		list = (ListView) this.findViewById(R.id.listOfProducts);
		
		ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
		list.setAdapter(new FoodItemAdapter(SellingActivity.this, R.layout.selling_list_item, foods)); // create itemAdapater
		list.setOnClickListener(null); //TODO set as appropriate?
		//TODO figure out how to update cashbox onClick
	}
    
    public void onFinishButton(View v) {
    	//TODO write me
    }
    
    public void onBackButton(View v) {
    	//TODO write me
    }
    
    public void setCashBox(double cash) {
    	cashBox.setText(Double.toString(cash));
    }
}
